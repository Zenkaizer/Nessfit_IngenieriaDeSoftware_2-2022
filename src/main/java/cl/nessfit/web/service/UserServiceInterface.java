package cl.nessfit.web.service;

import cl.nessfit.web.model.User;

import java.util.List;

public interface UserServiceInterface {

    /**
     * Saves the user in the database.
     * @param user User to save.
     */
    void save(User user);
    /**
     * Given a rut, returns a user with that rut data.
     * @param rut Rut to search for a user.
     */
    User searchByRut(String rut);
    /**
     * Return a list with all users with administrative role from the database.
     */
    List<User> getAdministrative();
    /**
     * Return a list with all users from the database.
     */
    List<User> getUsers();
    /**
     * Return a list with administrative and clients.
     */
    List<User> getAdministrativeAndClients();
}