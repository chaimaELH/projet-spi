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
import fr.univbrest.dosi.bean.Candidat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTestConfig.class)
public class CandidatRepositoryTest {
	
	@Autowired
	CandidatRepository candidatRepo;
	
	@Before
	public void setup() {
		Candidat candidat1 = new Candidat("C1", "EL HADIG", "MAROC", "Chaima", "UIZ","AGADIR");
		Candidat candidat2 = new Candidat("C2","TIZI", "MAROC", "Tizara", "LILLE3","AGADIR" );
	
		candidatRepo.save(candidat1);
		candidatRepo.save(candidat2);
	}
	
	@Test
	public void doitCompterLesCandidats() {
		long resultat = candidatRepo.count();
		
		assertThat(resultat).isEqualTo(2);
	}
	

	@Test
	public void doitRechercherParUniversite() {
		List<Candidat> resultat = candidatRepo.findByuniversiteOrigine("UIZ");
		
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getUniversiteOrigine()).isEqualTo("UIZ");
		assertThat(resultat.get(0).getNoCandidat()).isEqualTo("C1");
	}
}