package fr.univbrest.dosi.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.repositories.EnseignantRepository;

@Service
public class EnseignantBusinessJPA implements EnseignantBusiness {
	
	private EnseignantRepository repos;

	@Autowired
	public EnseignantBusinessJPA(EnseignantRepository repos) {
		super();
		this.repos = repos;
	}
	
	@Override
	public Enseignant creerEnseignant(Enseignant enseignantACreer) {
		return repos.save(enseignantACreer);
		
	}
	
	@Override
	public Enseignant rechercherEnseignantParId(long noEnseignant){
		return (Enseignant) repos.findOne(noEnseignant);
	}
	
	@Override
	public List<Enseignant> rechercherEnseignantParVille(String ville){
		return (List<Enseignant>) repos.findByVille(ville);
	}
	
	@Override
	public Enseignant rechercherEnseignantParEmailUbo(String emailUbo) {
		return (Enseignant) repos.findByEmailUbo(emailUbo);
	}
	
	@Override
	public void supprimerEnseignantParId(long noEnseignant) {
		repos.delete(noEnseignant);
	}
	
	@Override
	public void supprimerEnseignant(Enseignant enseignantASupprimer) {
		repos.delete(enseignantASupprimer);
	}
	
	@Override
	public List<Enseignant> recupererTousLesEnseignants(){
		return (List<Enseignant>) repos.findAll();
	}

	
	
	

	
}

