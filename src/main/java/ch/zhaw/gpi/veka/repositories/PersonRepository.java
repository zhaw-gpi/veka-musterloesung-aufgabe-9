package ch.zhaw.gpi.veka.repositories;

import ch.zhaw.gpi.veka.entities.AddressEntity;
import ch.zhaw.gpi.veka.entities.PersonEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository-Klasse für Person-Entität, welche CRUD-Operationen auf die dahinterliegende Datenbank kapselt
 * 
 * @author scep
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long>{
    
    // Gibt alle Personen zurück, welche an einer übergebenen Adresse wohnen
    public List<PersonEntity> findAllByAddressPostal(AddressEntity addressEntity);
}
