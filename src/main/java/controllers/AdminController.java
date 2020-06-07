package controllers;

import domain.Game.Notification;
import domain.Game.Root;
import domain.Game.RootLib.Location;
import domain.Game.RootLib.NPC;
import domain.Game.RootLib.Quest;
import domain.RealPerson.Administrator;
import domain.RealPerson.Player;
import filters.customAnnotations.OnlyAdmin;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.entities.*;
import servises.AdminServices;

@OnlyAdmin
@Consumes(MediaType.APPLICATION_JSON)
@Path("admin")
public class AdminController {
    private final AdminServices adminServices = new AdminServices();
    private final PlayerRepository playerRepo = new PlayerRepository();
    private final RootRepository rootRepo = new RootRepository();
    private final NPCRepository npcRepo = new NPCRepository();
    private final QuestRepository questRepos = new QuestRepository();
    private final LocationRepository locationRepo = new LocationRepository();

    //admin
    @POST
    @Path("/add")
    public Response createNewPlayer(Administrator admin) {
        try {
            adminServices.add(admin);
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
        adminServices.delete(id);
    }

    @PUT
    @Path("/update")
    public Response updatePlayer(Administrator admin){
        try {
            adminServices.update(admin);
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


    //make Announcement
    @POST
    @Path("/addNotification")
    public Response makeAnnouncement(Notification note) {
        try {
            //adminServices.addNote(note);
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
                .entity("Notification created successfully!")
                .build();
    }

    //Player
    @DELETE
    @Path("/deleteRoot")
    public Response removePlayer(Player player) {
        try {
            playerRepo.remove(player);
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
                status(Response.Status.OK)
                .entity("Player remove successfully!")
                .build();
    }

    //Root
    @POST
    @Path("/addRoot")
    public Response createRoot(Root root) {
        try {
            rootRepo.add(root);
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
                .entity("Root created successfully!")
                .build();
    }

    @PUT
    @Path("/updateRoot")
    public Response updateRoot(Root root) {
        try {
            rootRepo.update(root);
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
                status(Response.Status.ACCEPTED)
                .entity("Root updated successfully!")
                .build();
    }

    @DELETE
    @Path("/deleteRoot")
    public Response removeRoot(Root root) {
        try {
            rootRepo.remove(root);
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
                status(Response.Status.OK)
                .entity("Root remove successfully!")
                .build();
    }


    //NPC
    @POST
    @Path("/addNPC")
    public Response createNPC(NPC npc) {
        try {
            npcRepo.add(npc);
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
                .entity("NPC created successfully!")
                .build();
    }

    @PUT
    @Path("/updateNPC")
    public Response updateNPC(NPC npc) {
        try {
            npcRepo.update(npc);
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
                status(Response.Status.ACCEPTED)
                .entity("NPC updated successfully!")
                .build();
    }

    @DELETE
    @Path("/deleteNPC")
    public Response removeNPC(NPC npc) {
        try {
            npcRepo.remove(npc);
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
                status(Response.Status.OK)
                .entity("NPC remove successfully!")
                .build();
    }


    //Quest
    @POST
    @Path("/addQuest")
    public Response createQuest(Quest quest) {
        try {
            questRepos.add(quest);
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
                .entity("Quest created successfully!")
                .build();
    }

    @PUT
    @Path("/updateQuest")
    public Response updateQuest(Quest quest) {
        try {
            questRepos.update(quest);
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
                status(Response.Status.ACCEPTED)
                .entity("Quest updated successfully!")
                .build();
    }

    @DELETE
    @Path("/deleteQuest")
    public Response removeQuest(Quest quest) {
        try {
            questRepos.remove(quest);
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
                status(Response.Status.OK)
                .entity("Quest removed successfully!")
                .build();
    }


    //Location
    @POST
    @Path("/addLocation")
    public Response createLocation(Location location) {
        try {
            locationRepo.add(location);
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
                .entity("Location created successfully!")
                .build();
    }

    @PUT
    @Path("/updateLocation")
    public Response updateLocation(Location location) {
        try {
            locationRepo.update(location);
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
                status(Response.Status.ACCEPTED)
                .entity("Location update successfully!")
                .build();
    }

    @DELETE
    @Path("/deleteLocation")
    public Response removeLocation(Location location) {
        try {
            locationRepo.remove(location);
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
                status(Response.Status.OK)
                .entity("Location remove successfully!")
                .build();
    }
}
