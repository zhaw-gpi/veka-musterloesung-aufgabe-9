package ch.zhaw.gpi.veka.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity-Klasse für Adressen
 * 
 * @author scep
 */
@Entity (name = "Address")
public class AddressEntity implements Serializable {
    // Automatisch generierte Adress-Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Strasse
    private String street;
    
    // Hausnummer
    private String houseNumber;
    
    // PLZ
    private int plz;
    
    // Stadt/Ort
    private String town;
    
    // GETTER und SETTER
    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public AddressEntity setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public int getPlz() {
        return plz;
    }

    public AddressEntity setPlz(int plz) {
        this.plz = plz;
        return this;
    }

    public String getTown() {
        return town;
    }

    public AddressEntity setTown(String town) {
        this.town = town;
        return this;
    }    
}
