package cl.nessfit.web.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = -1279141245302698608L;
    @Id
    private int id;
    private String name;

    /**
     * Method that gets the id of a role.
     * @return Id of a role.
     */
    public int getId() {
        return id;
    }
    /**
     * Method that sets the id for a role.
     * @param id New id for the role.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Method that gets the name of a role.
     * @return Name of a role.
     */
    public String getName() {
        return name;
    }
    /**
     * Method that sets the name for a role.
     * @param name New name for the role.
     */
    public void setName(String name) {
        this.name = name;
    }
}
