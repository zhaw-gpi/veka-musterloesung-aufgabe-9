package ch.zhaw.gpi.veka.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity-Klasse für (versicherte) Person
 * 
 * @author scep
 */
@Entity(name = "Person")
public class PersonEntity implements Serializable {
    // Automatisch generierte Personen-Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    // Vorname
    private String firstName;
    
    // Nachname
    private String officialName;
    
    // Geburtsdatum
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    // Referenz auf eine Adresse
    @ManyToOne
    private AddressEntity addressPostal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AddressEntity getAddressPostal() {
        return addressPostal;
    }

    public void setAddressPostal(AddressEntity addressPostal) {
        this.addressPostal = addressPostal;
    }   
}
