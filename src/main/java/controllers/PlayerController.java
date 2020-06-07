package controllers;

import domain.RealPerson.Player;
import filters.customAnnotations.JWTTokenNeeded;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servises.PlayerServices;


@Path("player")
public class PlayerController {

    private final PlayerServices playerServices = new PlayerServices();


    //not mine

    @JWTTokenNeeded
    @GET
    @Path("/{param}")
    public Response getPlayerById(@PathParam("id") long id) {
        Player player;
        try {
            player = playerServices.getById(id);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError()
                    .build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.ACCEPTED)
                    .allow("This player can't be created").build();
        }

        if (player == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Player doesn't exist!")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(player)
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response createNewPlayer(Player player) {
        try {
            playerServices.add(player);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError()
                    .entity(ex.getMessage())
                    .build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response.
                status(Response.Status.CREATED)
                .entity("User created successfully!")
                .build();
    }

    @DELETE
    @Path("/remove")
    public void  removePlayer(long id){
        playerServices.delete(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updatePlayer(Player player){
        try {
            playerServices.update(player);
        }catch (ServerErrorException ex){
            return Response
                    .serverError()
                    .entity(ex.getMessage())
                    .build();
        }catch (BadRequestException ex){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.ACCEPTED)
                .entity("Player updated successfully!")
                .build();
    }


    //friend
    @GET
    @Path("/playerFriends")
    public Response getPlayerFriendsById(long id) {
        Iterable<Player> friends;

        try {
            friends = playerServices.getFriendsByPlayerId(id);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError()
                    .build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.ACCEPTED)
                    .allow("Friends can't be found").build();
        }

        if (friends == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Friends doesn't exist!")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(friends)
                    .build();
        }
    }

    @POST
    @Path("/makeNewFriend")
    private String makeFriend(long id, Player anotherPlayer) {
        if (sendFriendRequest(id,anotherPlayer.getUsername())){
            playerServices.addFriend(id, anotherPlayer.getId());
            return "You got new friend " + anotherPlayer.getUsername();
        }
        return anotherPlayer.getUsername() + " don't agree";

    }

    public boolean sendFriendRequest(@PathParam("id") long id, String usernameAnother) {
        String message = playerServices.getById(id).getUsername() + " want to be your friend. (Yes/No)";
        playerServices.addMessageToPLayerNotifications(playerServices.getByUsername(usernameAnother), message);

        if(playerServices.getFirstNotificationFromAnotherPlayer(playerServices.getByUsername(usernameAnother).getId()).getMessage().equals("Yes")){
            return true;
        }
        return false;
    }

}
