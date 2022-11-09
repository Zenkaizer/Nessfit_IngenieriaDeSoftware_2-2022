package cl.nessfit.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.User;
import cl.nessfit.web.repository.UserRepositoryInterface;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepositoryInterface userRepository;

    /**
     * Saves the user in the database.
     * @param user User to save.
     */
    @Override
    public void save(User user) { userRepository.save(user); }
    /**
     * Given a rut, returns a user with that rut data.
     * @param rut Rut to search for a user.
     */
    @Override
    public User searchByRut(String rut) { return userRepository.findByRut(rut); }

    /**
     * Return a list with all users with administrative role from the database.
     */
    @Override
    public List<User> getAdministrative() { return userRepository.findByRoleId(2); }

    /**
     * Return a list gith all users from the database.
     */
    @Override
    public List<User> getUsers() { return userRepository.findAll(); }

    /**
     * Return a list with administrative and clients.
     */
    @Override
    public List<User> getAdministrativeAndClients() { return userRepository.findUsersByRoleIdOrRoleId(2,3); }

}