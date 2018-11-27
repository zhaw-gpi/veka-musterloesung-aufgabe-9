package ch.zhaw.gpi.veka.controller;

import ch.zhaw.gpi.veka.entities.CardEntity;
import ch.zhaw.gpi.veka.repositories.CardRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Geschäftslogik und REST-Schnittstelle im Zusammenhang mit Versichertenkarten
 * 
 * @author scep
 */
@RestController
public class CardController {
    
    // Verdrahten der CardRepository
    @Autowired
    private CardRepository cardRepository;
    
    /**
     * Zu einer Kartennummer eine Karte oder NotFoundException zurück geben
     * 
     * @param cardNumber    Kartennummer
     * @return              CardEntity oder null
     */
    @RequestMapping(method = RequestMethod.GET, value = "/vekaapi/v1/cards/{cardNumber}")
    public ResponseEntity<CardEntity> getCard(@PathVariable Long cardNumber){
        // Zur Kartennummer passende Karte suchen
        Optional<CardEntity> card = cardRepository.findById(cardNumber);
        
        // Falls Karte gefunden wurde, ...
        if(card.isPresent()) {
            // ... dann card zurück geben inkl. Http-Status 200
            return new ResponseEntity(card.get(), HttpStatus.OK);
        } else {
            // ansonsten ResourceNotFoundException (404) zurück geben
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }        
    }
}
