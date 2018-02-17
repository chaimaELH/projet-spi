package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.repositories.PromotionRepository;

public class PromotionBusinessJPATest {

	PromotionBusinessJPA business;
	@Test
	public void doitCreerUnePromotion() {
		PromotionRepository repos = new PromotionRepositoryList();
		business = new PromotionBusinessJPA(repos);
		PromotionPK id = new PromotionPK("2013-2014","M2DOSI");
		Promotion promotionACreer = new Promotion(id,"LC117B","EC","DOSI4") ;
		
		business.creerPromotion(promotionACreer);
		assertThat(repos.count()).isEqualTo(1);		
	}
	
	//Test pour recherche par ID, par sigle promotion et par lieu rentree
	
	@Test
	public void doitRechercherPromotion() {
		PromotionPK id1 = new PromotionPK("2013-2014","M2DOSI");
		PromotionPK id2 = new PromotionPK("2014-2015","M2DOSI");
		PromotionPK id3 = new PromotionPK("2014-2015","M1TIIL");
		Promotion promotion1 = new Promotion(id1,"LC117B","EC","DOSI4");
		Promotion promotion2 = new Promotion(id2,"LC117B","RECH","DOSI5");
		Promotion promotion3 = new Promotion(id3,"LC117A","EC","M1TIIL4");
		PromotionRepository repos = new PromotionRepositoryList(promotion1,promotion2,promotion3);
		
		business = new PromotionBusinessJPA(repos);
		
		Promotion resultatId = business.rechercherPromotionParId(id1);
		Promotion resultatSiglePromotion = business.rechercherPromotionParSiglePromotion("DOSI5");
		List<Promotion> resultatLieuRentree = business.rechercherPromotionParLieuRentree("LC117B");
		
		assertThat(repos.count()).isEqualTo(3);
		assertThat(resultatId.getSiglePromotion()).isEqualTo("DOSI4");
		assertThat(resultatSiglePromotion.getProcessusStage()).isEqualTo("RECH");
		assertThat(resultatLieuRentree.size()).isEqualTo(2);
		assertThat(resultatLieuRentree).contains(promotion2);
	}
	
	@Test
	public void doitSupprimerUnePromotionParId() {
		PromotionPK id1 = new PromotionPK("2013-2014","M2DOSI");
		PromotionPK id2 = new PromotionPK("2014-2015","M2DOSI");
		PromotionPK id3 = new PromotionPK("2014-2015","M1TIIL");
		Promotion promotion1 = new Promotion(id1,"LC117B","EC","DOSI4");
		Promotion promotion2 = new Promotion(id2,"LC117B","RECH","DOSI5");
		Promotion promotion3 = new Promotion(id3,"LC117A","EC","M1TIIL4");
		PromotionRepository repos = new PromotionRepositoryList(promotion1,promotion2,promotion3);
		
		business = new PromotionBusinessJPA(repos);
		
		business.supprimerPromotionParId(id1);
		assertThat(repos.count()).isEqualTo(2);
		
	}
	
	@Test
	public void doitSupprimerUnePromotion() {
		PromotionPK id1 = new PromotionPK("2013-2014","M2DOSI");
		PromotionPK id2 = new PromotionPK("2014-2015","M2DOSI");
		PromotionPK id3 = new PromotionPK("2014-2015","M1TIIL");
		Promotion promotion1 = new Promotion(id1,"LC117B","EC","DOSI4");
		Promotion promotion2 = new Promotion(id2,"LC117B","RECH","DOSI5");
		Promotion promotion3 = new Promotion(id3,"LC117A","EC","M1TIIL4");
		PromotionRepository repos = new PromotionRepositoryList(promotion1,promotion2,promotion3);
		
		business = new PromotionBusinessJPA(repos);
		
		business.supprimerPromotion(promotion1);
		assertThat(repos.count()).isEqualTo(2);
		
	
}
}
 class PromotionRepositoryList implements PromotionRepository{

		private List<Promotion> listPromotion;
		
		
		public PromotionRepositoryList(List<Promotion> listPromotion) {
			super();
			this.listPromotion = listPromotion;
		}

		public PromotionRepositoryList(Promotion... listPromotion) {
			this.listPromotion = Lists.newArrayList(listPromotion);
		}
		
	@Override
	public <S extends Promotion> S save(S entity) {
		listPromotion.add(entity);
		return entity;
	}

	@Override
	public <S extends Promotion> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promotion findOne(PromotionPK id) {
		List<Promotion> liste2 = listPromotion.stream()
				.filter(element -> element.getId() == id)
				.collect(Collectors.toList());
		return liste2.get(0);
	}

	@Override
	public boolean exists(PromotionPK id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Promotion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Promotion> findAll(Iterable<PromotionPK> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return listPromotion.size() ;
	}

	@Override
	public void delete(PromotionPK id) {
		listPromotion = listPromotion.stream()
				.filter(element -> !(element.getId() == id))
				.collect(Collectors.toList());
		
	}

	@Override
	public void delete(Promotion entity) {
		listPromotion = listPromotion.stream()
				.filter(element -> !element.equals(entity))
				.collect(Collectors.toList());
		
	}

	@Override
	public void delete(Iterable<? extends Promotion> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Promotion findBySiglePromotion(String siglePromotion) {
		List<Promotion> liste2 = listPromotion.stream()
				.filter(element -> element.getSiglePromotion() == siglePromotion)
				.collect(Collectors.toList());
		return liste2.get(0);
	}

	@Override
	public List<Promotion> findByLieuRentree(String lieuRentree) {
		return listPromotion.stream()
				.filter(element -> element.getLieuRentree().matches(lieuRentree))
				.collect(Collectors.toList());
	}
 }
 
	 


