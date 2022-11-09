package cl.nessfit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.nessfit.web.model.Installation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstallationRepositoryInterface extends JpaRepository<Installation, String> {

    /**
     * Select * from installations u where u.name = name.
     * @param name name from installation.
     * @return Installation.
     */
    Installation findByName(String name);

    /**
     * Select *;
     * @return All instalaltion from the database.
     */
    List<Installation> findAll();


}