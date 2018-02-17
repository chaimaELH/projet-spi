package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.business.EnseignantBusiness;

@RestController
@EnableAutoConfiguration
@RequestMapping("/enseignants")
public class EnseignantController {
	
	private EnseignantBusiness business;
	
	@Autowired
	public EnseignantController(EnseignantBusiness business) {
		this.business = business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Enseignant creerEnseignant(@RequestBody Enseignant enseignantACreer){
		return business.creerEnseignant(enseignantACreer);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Enseignant> recupererTousLesEnseignants(){
		return business.recupererTousLesEnseignants();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Enseignant recupererLEnseignantAvecLId(@PathVariable Long id) {
		return business.rechercherEnseignantParId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/email/{email}")
	public Enseignant recupererLEnseignantAvecLEmail(@PathVariable String email) {
		return business.rechercherEnseignantParEmailUbo(email) ;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/ville/{ville}")
	public List<Enseignant> recupererLEnseignantQuiVitA(@PathVariable String ville) {
		return business.rechercherEnseignantParVille(ville) ;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public void supprimerEnseignantParId(@PathVariable long id) {
		business.supprimerEnseignantParId(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void supprimerEnseignant(@RequestBody Enseignant enseignantASupprimer) {
		business.supprimerEnseignant(enseignantASupprimer);
	}
	
	

}
