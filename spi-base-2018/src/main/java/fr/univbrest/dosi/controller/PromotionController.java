package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.business.PromotionBusiness;

@RestController
@RequestMapping(value="/promotions")
public class PromotionController {
	@Autowired
	PromotionBusiness promotionBusiness;

	@RequestMapping(method=RequestMethod.POST)
	public Promotion creerPromotion(@RequestBody Promotion promotionACreer) {
		return promotionBusiness.creerPromotion(promotionACreer);
	}

	@RequestMapping(method=RequestMethod.GET, value="/{codeF}/{anneePro}")
	public Promotion rechercherPromotionParId(@PathVariable String codeF, @PathVariable String anneePro) {
		return promotionBusiness.rechercherPromotionParId(new PromotionPK(anneePro, codeF));
	}

	@RequestMapping(method=RequestMethod.GET, value="/sigle/{siglePromotion}")
	public Promotion rechercherPromotionParSiglePromotion(@PathVariable String siglePromotion) {
		return promotionBusiness.rechercherPromotionParSiglePromotion(siglePromotion);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{codeF}/{anneePro}")
	public void supprimerPromotionParId(@PathVariable String codeF, @PathVariable String anneePro) {
		promotionBusiness.supprimerPromotionParId(new PromotionPK(anneePro, codeF));
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public void supprimerPromotion(@RequestBody Promotion promotionASupprimer) {
		promotionBusiness.supprimerPromotion(promotionASupprimer);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Promotion> recupererToutesLesPromotions() {
		return promotionBusiness.recupererToutesLesPromotions();
	}

	@RequestMapping(method=RequestMethod.GET, value="/lieuRentree/{lieuRentree}")
	public List<Promotion> rechercherPromotionParLieuRentree(@PathVariable String lieuRentree) {
		return promotionBusiness.rechercherPromotionParLieuRentree(lieuRentree);
	}
	
	
}
