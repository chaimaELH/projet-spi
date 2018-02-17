package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Enseignant;

public interface EnseignantBusiness {

	Enseignant creerEnseignant(Enseignant enseignantACreer);
	Enseignant rechercherEnseignantParId(long noEnseignant);
	void supprimerEnseignantParId(long noEnseignant);
	List<Enseignant> recupererTousLesEnseignants();
	void supprimerEnseignant(Enseignant enseignantASupprimer);
	Enseignant rechercherEnseignantParEmailUbo(String emailUbo);
	List<Enseignant> rechercherEnseignantParVille(String ville);

}
