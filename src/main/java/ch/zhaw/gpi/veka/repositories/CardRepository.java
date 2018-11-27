package ch.zhaw.gpi.veka.repositories;

import ch.zhaw.gpi.veka.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository-Klasse für Versicherten-Karten-Entität, welche CRUD-Operationen auf die dahinterliegende Datenbank kapselt
 * 
 * @author scep
 */
public interface CardRepository extends JpaRepository<CardEntity, Long>{
    
}
