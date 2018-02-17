package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.business.CandidatBusiness;

@RestController
@EnableAutoConfiguration
@RequestMapping("/candidats")
public class CandidatController {

	private CandidatBusiness candidatBusiness;

	@Autowired
	public CandidatController(CandidatBusiness candidatBusiness) {
		super();
		this.candidatBusiness = candidatBusiness;
	}
	@RequestMapping(method = RequestMethod.POST)
	public Candidat creerCandidat(@RequestBody Candidat CandidatACreer) {
		return candidatBusiness.creerCandidat(CandidatACreer);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Candidat> rechercherTousLesCandidats(){
		return candidatBusiness.recupererTousLesCandidats();	
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Candidat rechercherCandidatParId(@PathVariable String id) {
		return candidatBusiness.rechercherCandidatParId(id);	
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/universiteOrigine/{universiteOrigine}")
	public List<Candidat> rechercherCandidatParUniversiteOrigine(@PathVariable String universiteOrigine) {
		return candidatBusiness.rechercherCandidatParuniversiteOrigine(universiteOrigine);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void supprimerCandidatParId(@PathVariable String id) {
		candidatBusiness.supprimerCandidatParId(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void supprimerCandidat(@RequestBody Candidat candidatASupprimer) {
		candidatBusiness.supprimerCandidat(candidatASupprimer);
	}
	
	
	
}
