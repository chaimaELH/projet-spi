package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.repositories.EnseignantRepository;

public class EnseignantBusinessJPATest {
	
	EnseignantBusinessJPA business;
	
	@Test
	public void doitCreerUnEnseignant() {
		EnseignantRepository repos = new EnseignantRepositoryList();
		business = new EnseignantBusinessJPA(repos);
		Enseignant enseignantACreer = new Enseignant(1,"chaima.elhadig@univ-brest.fr","EL HADIG","Maroc","Chaima","Brest");
		
		business.creerEnseignant(enseignantACreer);
		assertThat(repos.count()).isEqualTo(1);		
	}
	
	//test pour recherche par Id, par Ville et par EmailUbo
	@Test
	public void doitRechercherUnEnseignant(){
		Enseignant enseignant1 = new Enseignant(1,"chaima.elhadig@univ-brest.fr","EL HADIG","Maroc","Chaima","Brest");
		Enseignant enseignant2 = new Enseignant(2,"fz.hadig@univ-brest.fr","HADIG","Maroc","Fz","Lille");
		Enseignant enseignant3 = new Enseignant(3,"rakia.aqer@univ-brest.fr","AQER","France","Rakia","Brest");
		EnseignantRepository repos = new EnseignantRepositoryList(enseignant1,enseignant2,enseignant3);
		
		business = new EnseignantBusinessJPA(repos);
		
		Enseignant resultatId = business.rechercherEnseignantParId(1);
		Enseignant resultatEmailUbo = business.rechercherEnseignantParEmailUbo("chaima.elhadig@univ-brest.fr");
		List<Enseignant> resultatVille = business.rechercherEnseignantParVille("Brest");
		
		assertThat(repos.count()).isEqualTo(3);
		assertThat(resultatId.getNoEnseignant()).isEqualTo(1);
		assertThat(resultatEmailUbo.getNoEnseignant()).isEqualTo(1);
		assertThat(resultatVille.size()).isEqualTo(2);
		assertThat(resultatVille).contains(enseignant1);
	}
	

	@Test
	public void doitSupprimerUnEnseignantParId() {
		Enseignant enseignant1 = new Enseignant(1,"chaima.elhadig@univ-brest.fr","EL HADIG","Maroc","Chaima","Brest");
		Enseignant enseignant2 = new Enseignant(2,"fz.hadig@univ-brest.fr","HADIG","Maroc","Fz","Lille");
		Enseignant enseignant3 = new Enseignant(3,"rakia.aqer@univ-brest.fr","AQER","France","Rakia","Brest");
		EnseignantRepository repos = new EnseignantRepositoryList(enseignant1,enseignant2,enseignant3);
		
		business = new EnseignantBusinessJPA(repos);
		
		business.supprimerEnseignantParId(1);
		assertThat(repos.count()).isEqualTo(2);
		
		
	}
	
	@Test
	public void doitSupprimerUnEnseignant() {
		Enseignant enseignant1 = new Enseignant(1,"chaima.elhadig@univ-brest.fr","EL HADIG","Maroc","Chaima","Brest");
		Enseignant enseignant2 = new Enseignant(2,"fz.hadig@univ-brest.fr","HADIG","Maroc","Fz","Lille");
		Enseignant enseignant3 = new Enseignant(3,"rakia.aqer@univ-brest.fr","AQER","France","Rakia","Brest");
		EnseignantRepository repos = new EnseignantRepositoryList(enseignant1,enseignant2,enseignant3);
		
		business = new EnseignantBusinessJPA(repos);
		
		business.supprimerEnseignant(enseignant1);
		assertThat(repos.count()).isEqualTo(2);
		
		
	}
	
	

}

class EnseignantRepositoryList implements EnseignantRepository {

	private List<Enseignant> listEnseignant;
	
	
	public EnseignantRepositoryList(List<Enseignant> listEnseignant) {
		super();
		this.listEnseignant = listEnseignant;
	}

	public EnseignantRepositoryList(Enseignant... listEnseignant) {
		this.listEnseignant = Lists.newArrayList(listEnseignant);
	}
	@Override
	public <S extends Enseignant> S save(S entity) {
		listEnseignant.add(entity);
		return entity;
	}

	@Override
	public <S extends Enseignant> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enseignant findOne(Long id) {
		List<Enseignant> liste2 = listEnseignant.stream()
				.filter(element -> element.getNoEnseignant() == id)
				.collect(Collectors.toList());
		return liste2.get(0);
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Enseignant> findAll() {
		// TODO Auto-generated method stub
		return listEnseignant;
	}

	@Override
	public Iterable<Enseignant> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return listEnseignant.size();
	}

	@Override
	public void delete(Long id) {
		listEnseignant = listEnseignant.stream()
				.filter(element -> !(element.getNoEnseignant() == id))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Enseignant entity) {
		listEnseignant = listEnseignant.stream()
				.filter(element -> !element.equals(entity))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Iterable<? extends Enseignant> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enseignant> findByVille(String ville) {
		return listEnseignant.stream()
				.filter(element -> element.getVille().matches(ville))
				.collect(Collectors.toList());
	}

	@Override
	public Enseignant findByEmailUbo(String emailUbo) {
		List<Enseignant> liste2 = listEnseignant.stream()
				.filter(element -> element.getEmailUbo() == emailUbo)
				.collect(Collectors.toList());
		return liste2.get(0);
	}
	
	
	
}
