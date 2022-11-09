package cl.nessfit.web.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "installation")
public class Installation implements Serializable {

    @Serial
    private static final long serialVersionUID = -8255784159283163739L;
    @Id
    @Size(min = 1, max = 200, message = "Largo inválido.")
    private String name;
    @Size(min = 1, max = 200, message = "Largo inválido.")
    private String address;
    // Hablar con rodrigo.
    private String type;
    @Column(name = "rental_cost")
    @Min(value = 1000, message = "El costo mínimo de arriendo debe ser $1000.")
    private int rentalCost;
    private int status;

    /**
     * Constructor for the Installation class.
     */
    public Installation() {
        this.name = "";
        this.address = "";
        this.type = "";
        this.rentalCost = 0;
        this.status = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(int rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}