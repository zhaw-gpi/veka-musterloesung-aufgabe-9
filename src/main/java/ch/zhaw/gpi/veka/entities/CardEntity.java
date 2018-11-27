package ch.zhaw.gpi.veka.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity-Klasse für Versichertenkarte
 * 
 * Attribute angelehnt an https://de.wikipedia.org/wiki/Versichertenkarte_(Schweiz)
 * 
 * @author scep
 */
@Entity(name = "Card")
public class CardEntity implements Serializable {
    // Manuell gesetzte Versichertenkarte-Id
    @Id
    private Long cardNumber;
    
    // Ablaufdatum der Karte
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    
    // Referenz auf einen Versicherer
    @ManyToOne
    @JoinColumn(name = "INSURER_ID")
    private InsurerEntity insurer;
    
    // Referenz auf eine versicherte Person
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity insuredPerson;
    
    // Beinhaltet die Karte eine Grundversicherung (false, falls nur Zusatzversicherungen)
    private Boolean baseInsured;

    
    // GETTER und SETTER
    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public InsurerEntity getInsurer() {
        return insurer;
    }

    public void setInsurer(InsurerEntity insurer) {
        this.insurer = insurer;
    }

    public PersonEntity getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(PersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public Boolean isBaseInsured() {
        return baseInsured;
    }

    public void setBaseInsured(Boolean baseInsured) {
        this.baseInsured = baseInsured;
    }
}
