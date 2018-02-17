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
import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTestConfig.class)
public class PromotionRepositoryTest {
	
	@Autowired
	PromotionRepository promotionRepo;
	
	@Before
	public void setup() {
		PromotionPK id1 = new PromotionPK("2013-2014","M2DOSI");
		PromotionPK id2 = new PromotionPK("2014-2015","M2DOSI");
		Promotion promotion1 = new Promotion(id1,"LC117B","EC","DOSI4");
		Promotion promotion2 = new Promotion(id2,"LC117B","RECH","DOSI5");
		
		promotionRepo.save(promotion1);
		promotionRepo.save(promotion2);
	}
	
	@Test
	public void doitCompterLesPromotions() {
		long resultat = promotionRepo.count();
		
		assertThat(resultat).isEqualTo(2);
	}
	
	@Test
	public void doitRechercherParLieuRentree() {
		List<Promotion> resultat = promotionRepo.findByLieuRentree("LC117B");
		
		assertThat(resultat).hasSize(2);
		assertThat(resultat.get(0).getProcessusStage()).isEqualTo("EC");
		assertThat(resultat.get(1).getSiglePromotion()).isEqualTo("DOSI5");
	}
	
	@Test
	public void doitRechercherParSiglePromotion() {
		Promotion resultat = promotionRepo.findBySiglePromotion("DOSI5");
		assertThat(resultat.getProcessusStage()).isEqualTo("RECH");
	}
	

}
