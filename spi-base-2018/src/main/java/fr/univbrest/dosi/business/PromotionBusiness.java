package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;

public interface PromotionBusiness {

	Promotion creerPromotion(Promotion promotionACreer);

	Promotion rechercherPromotionParId(PromotionPK id);

	Promotion rechercherPromotionParSiglePromotion(String siglePromotion);

	List<Promotion> rechercherPromotionParLieuRentree(String lieuRentree);
	
	void supprimerPromotionParId(PromotionPK id);

	void supprimerPromotion(Promotion promotionASupprimer);

	List<Promotion> recupererToutesLesPromotions();

}
