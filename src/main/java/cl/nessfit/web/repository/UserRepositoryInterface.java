package cl.nessfit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.nessfit.web.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, String> {

    /**
     * Select * from users u where u.rut = rut.
     * @param rut RUT from user.
     * @return User.
     */
    User findByRut(String rut);
    /**
     * Select * form users u where u.id_role = id.
     * @param id ID Role.
     * @return All user with the same id.
     */
    List<User> findByRoleId(int id);
    /**
     * Select *;
     * @return All users from the database.
     */
    List<User> findAll();
    /**
     * Select * from users u where u.id_role = id1 or u.id_role = id2.
     * @param id1 ID Role1.
     * @param id2 ID Role2.
     * @return All user with the same id.
     */
    List<User> findUsersByRoleIdOrRoleId(int id1, int id2);

}