package ch.zhaw.gpi.veka.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity-Klasse für Versicherer
 * 
 * @author scep
 */
@Entity(name = "Insurer")
public class InsurerEntity implements Serializable {
    // BAG-Nummer des Versicherers als Id
    @Id
    private Long id;
    
    // Name des Versicherers
    private String name;
    
    // Referenz auf eine Adresse
    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;
    
    // Telefonnummer
    private String phoneNumber;

    // E-Mail-Adresse
    private String eMail;

    // Homepage-URL
    private String websiteUrl;

    

    // GETTER und SETTER
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {    
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public InsurerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public InsurerEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
