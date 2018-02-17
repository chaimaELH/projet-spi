package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Candidat;

public interface CandidatBusiness {
	
	Candidat creerCandidat(Candidat candidatACreer);
	Candidat rechercherCandidatParId(String id);
	List<Candidat> rechercherCandidatParuniversiteOrigine(String universiteOrigine);
	void supprimerCandidat(Candidat candidatASupprimer);
	void supprimerCandidatParId(String id);
	List<Candidat> recupererTousLesCandidats();


}