package fr.univbrest.dosi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univbrest.dosi.AppTestConfig;
import fr.univbrest.dosi.bean.Enseignant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTestConfig.class)
public class EnseignantRepositoryTest {
	
	@Autowired
	EnseignantRepository enseignantRepo;
	
	@Before
	public void setup() {
		Enseignant enseignant1 = new Enseignant((long) 1,"tizi.tizara@univ-brest.fr","TIZI","France","TIZARA","Lille");
		Enseignant enseignant2 = new Enseignant((long) 2,"chaima.elhadig@univ-brest.fr","EL HADIG","Maroc","Chaima","Brest");
		
		enseignantRepo.save(enseignant1);
		enseignantRepo.save(enseignant2);
	}
	
	@Test
	public void doitCompterLesEnseignants() {
		long resultat = enseignantRepo.count();
		
		assertThat(resultat).isEqualTo(2);
	}
	
	@Test
	public void doitRechercherParVilleEnseignant() {
		List<Enseignant> resultat = enseignantRepo.findByVille("Brest");
		
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getNom()).isEqualTo("EL HADIG");
		assertThat(resultat.get(0).getNoEnseignant()).isEqualTo(2);
	}
	
	@Test
	public void doitRechercherParEmailUbo() {
		Enseignant resultat = enseignantRepo.findByEmailUbo("chaima.elhadig@univ-brest.fr") ;
		assertThat(resultat.getPays()).isEqualTo("Maroc");
		assertThat(resultat.getVille()).isEqualTo("Brest");
	}
	
	

}
