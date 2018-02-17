package fr.univbrest.dosi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.univbrest.dosi.bean.Enseignant;

@Repository
public interface EnseignantRepository extends CrudRepository<Enseignant, Long>{

	List<Enseignant> findByVille(String ville);
	Enseignant findByEmailUbo(String emailUbo);
}
