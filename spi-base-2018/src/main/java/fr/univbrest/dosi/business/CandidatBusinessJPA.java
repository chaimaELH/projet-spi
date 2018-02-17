package fr.univbrest.dosi.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.repositories.CandidatRepository;

@Service
public class CandidatBusinessJPA implements CandidatBusiness {

	private CandidatRepository repos;

	@Autowired
	public CandidatBusinessJPA(CandidatRepository repos) {
		super();
		this.repos = repos;
	}
	
	@Override
	public Candidat creerCandidat(Candidat candidatACreer) {
		return repos.save(candidatACreer);
		
	}

	@Override
	public List<Candidat> rechercherCandidatParuniversiteOrigine(String universiteOrigine){
		return repos.findByuniversiteOrigine(universiteOrigine);
		}

	
	@Override
	public Candidat rechercherCandidatParId(String id) {
		return repos.findOne(id);
	}
	
	@Override
	public void supprimerCandidat(Candidat candidatASupprimer) {
		repos.delete(candidatASupprimer);
	}
	
	@Override
	public void supprimerCandidatParId(String id) {
		repos.delete(id);
	}
	
	@Override
	public List<Candidat> recupererTousLesCandidats() {
		return (List<Candidat>) repos.findAll();
		
	}
	

}