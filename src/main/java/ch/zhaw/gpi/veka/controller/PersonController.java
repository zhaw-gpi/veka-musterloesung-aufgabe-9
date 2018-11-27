package ch.zhaw.gpi.veka.controller;

import ch.zhaw.gpi.veka.entities.AddressEntity;
import ch.zhaw.gpi.veka.entities.PersonEntity;
import ch.zhaw.gpi.veka.repositories.AddressRepository;
import ch.zhaw.gpi.veka.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Geschäftslogik und REST-Schnittstelle im Zusammenhang mit versicherten Personen
 * 
 * @author scep
 */
@RestController
public class PersonController {
    
    // Verdrahten benötigter JPA-Repositories
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    
    /**
     * Einer Person mit einer übergebenen PersonenId die im JSON-Format übergebene Adresse zuweisen
     * 
     * @param newAddress        Die neue Adresse
     * @param id                Id der Person
     * @return ResponseEntity   Nicht-Gefunden-Status (404), falls Person nicht vorhanden, ansonsten OK-Status (200)
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/vekaapi/v1/persons/{id}/address")
    public ResponseEntity updateAddress(@RequestBody AddressEntity newAddress, @PathVariable Long id){
        // Person über die Id im Repository suchen
        Optional<PersonEntity> person = personRepository.findById(id);
        
        // Falls Person mit dieser Id nicht vorhanden, ...
        if(!person.isPresent()){
            // ... dann 404 (Nicht gefunden)-Status zurückgeben
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        // Prüfen, ob die übergebene Adresse gar nicht neu ist, also mit der bestehenden übereinstimmt
        // Hinweis: Umständlich gelöst hier im Controller. Sauberer wäre natürlich, wenn man in AddressEntity einen Override der equals- und hashCode-Methoden macht, wie z.B. beschrieben in https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/. Dann würde man sinnvollerweise aber eine andere Id wählen, die wirklich eindeutig ist, in der Schweiz z.B. die Id für Hauseingänge. Auch eine einigermassen sauberere Lösung wäre, eine compareTo-Methode in der Entity hinzuzufügen. Eine weitere Variante ist, über eine neue findByPlzAndStreetAndHouseNumber-Methodendeklaration im AddressRepository nach der neuen Adresse zu suchen und dann zu schauen, ob sie mit der alten Adresse übereinstimmt.
        AddressEntity oldAddress = person.get().getAddressPostal();
        if(oldAddress.getPlz() == newAddress.getPlz() && oldAddress.getStreet().equals(newAddress.getStreet()) && oldAddress.getHouseNumber().equals(newAddress.getHouseNumber())){
            // Falls ja, dann OK-Status zurückgeben
            return new ResponseEntity(HttpStatus.OK);
        }

        // Im Repository nach der neuen Adresse suchen
        Optional<AddressEntity> searchedAddress = addressRepository.findByPlzAndStreetAndHouseNumber(newAddress.getPlz(), newAddress.getStreet(), newAddress.getHouseNumber());
        
        // Falls die neue Adresse noch nicht erfasst ist, ...
        if(!searchedAddress.isPresent()){
            // ... dann diese zum Speichern vormerken
            addressRepository.save(newAddress);
        } else {
            // ... ansonsten die bereits vorhandene als neue Adresse festlegen
            newAddress = searchedAddress.get();
        }
        
        // Die neue Adresse der Person zuweisen
        person.get().setAddressPostal(newAddress);
        
        // Person mit neuer Adresse zum Speichern vormerken
        personRepository.save(person.get());
        
        // Falls die bisherige Adresse keiner Person mehr zugewiesen ist, also die Liste der Personen an einer Adresse leer ist, ...
        if(personRepository.findAllByAddressPostal(oldAddress).isEmpty()){
            // ... dann die Adresse zum Löschen vormerken
            addressRepository.delete(oldAddress);
        }
        
        // OK-Status zurück geben
        return new ResponseEntity(HttpStatus.OK);
    }
}
