package servises;

import domain.Game.Notification;
import domain.RealPerson.Administrator;
import repository.entities.AdminRepository;
import repository.entities.NotificationRepository;
import servises.interfaces.IPersonServices;

public class AdminServices implements IPersonServices<Administrator> {
    private final AdminRepository adminRepo = new AdminRepository();
    private final NotificationRepository noteRepo = new NotificationRepository();

    @Override
    public Administrator getById(long id) {
        return adminRepo.getAdminById(id);
    }

    @Override
    public Administrator getByUsername(String id_name) {
        return adminRepo.getUserByIdentityName(id_name);
    }

    @Override
    public Administrator findByLogin(Administrator person) {
        return adminRepo.findUserByLogin(person);
    }

    @Override
    public void add(Administrator person) {
        adminRepo.add(person);
    }

    @Override
    public void delete(long id) {
        adminRepo.remove(getById(id));
    }

    @Override
    public void update(Administrator entity) {
        adminRepo.update(entity);
    }

    public void addNote(long id, String message) {
        noteRepo.add(id, message);
    }

    public void announcment() {
    }
}
