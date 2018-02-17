package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.repositories.CandidatRepository;

public class CandidatBusinessJPATest {

	CandidatBusinessJPA business;

	@Test
	public void doitCreerUnCandidat() {
		CandidatRepository repos = new CandidatRepositoryList();
		business = new CandidatBusinessJPA(repos);
		Candidat candidatACreer = new Candidat("C1", "EL HADIG", "MAROC", "Chaima", "UIZ", "AGADIR");

		business.creerCandidat(candidatACreer);
		assertThat(repos.count()).isEqualTo(1);
	}

	@Test
	public void doitSupprimerUnCandidat() {
		Candidat candidat1 = new Candidat("C1", "EL HADIG", "MAROC", "Chaima", "UIZ", "AGADIR");
		Candidat candidat2 = new Candidat("C2", "AQERMIM", "MAROC", "Rakia", "UCAM", "MARRAKECH");
		Candidat candidat3 = new Candidat("C3", "TIZI", "MAROC", "Wizi", "LILLE1", "LILLE");
		CandidatRepository repos = new CandidatRepositoryList(candidat1, candidat2, candidat3);
		business = new CandidatBusinessJPA(repos);
		business.supprimerCandidat(candidat1);
		assertThat(repos.count()).isEqualTo(2);

	}


	@Test
	public void doitSupprimerUnCandidatParId() {
		Candidat candidat1 = new Candidat("C1", "EL HADIG", "MAROC", "Chaima", "UIZ", "AGADIR");
		Candidat candidat2 = new Candidat("C2", "AQERMIM", "MAROC", "Rakia", "UCAM", "MARRAKECH");
		Candidat candidat3 = new Candidat("C3", "TIZI", "MAROC", "Wizi", "LILLE1", "LILLE");
		CandidatRepository repos = new CandidatRepositoryList(candidat1, candidat2, candidat3);
		business = new CandidatBusinessJPA(repos);
		business.supprimerCandidatParId("C1");
		assertThat(repos.count()).isEqualTo(2);

	}

	// Test recherche Candidat par Id et UniversiteOrigine

	@Test
	public void doitRechercherUnCandidat() {
		Candidat candidat1 = new Candidat("C1", "EL HADIG", "MAROC", "Chaima", "UIZ", "AGADIR");
		Candidat candidat2 = new Candidat("C2", "AQERMIM", "MAROC", "Rakia", "LILLE1", "MARRAKECH");
		Candidat candidat3 = new Candidat("C3", "TIZI", "MAROC", "Wizi", "LILLE1", "LILLE");
		CandidatRepository repos = new CandidatRepositoryList(candidat1, candidat2, candidat3);
		business = new CandidatBusinessJPA(repos);

		List<Candidat> resultatUniversiteOrigine = business.rechercherCandidatParuniversiteOrigine("LILLE1");
		Candidat resultatId = business.rechercherCandidatParId("C1");

		assertThat(repos.count()).isEqualTo(3);
		assertThat(resultatUniversiteOrigine.size()).isEqualTo(2);
		assertThat(resultatUniversiteOrigine).contains(candidat3);
		assertThat(resultatId.getNom()).isEqualTo("EL HADIG");
	}
}

class CandidatRepositoryList implements CandidatRepository {
	private List<Candidat> listCandidat;

	public CandidatRepositoryList(Candidat... candidats) {
		super();
		listCandidat = Lists.newArrayList(candidats);

	}

	public CandidatRepositoryList(List<Candidat> listCandidatDonnee) {
		super();
		listCandidat = listCandidatDonnee;
	}

	@Override
	public <S extends Candidat> S save(S entity) {
		listCandidat.add(entity);
		return entity;
	}

	@Override
	public void delete(Candidat entity) {
		listCandidat.remove(entity);

	}

	@Override
	public <S extends Candidat> Iterable<S> save(Iterable<S> entities) {

		return null;
	}

	@Override
	public Candidat findOne(String id) {
		List<Candidat> liste2 = listCandidat.stream().filter(element -> element.getNoCandidat() == id)
				.collect(Collectors.toList());
		return liste2.get(0);
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Candidat> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Candidat> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return listCandidat.size();
	}

	@Override
	public void delete(String id) {
		listCandidat= listCandidat.stream()
				.filter(element -> !(element.getNoCandidat() == id))
				.collect(Collectors.toList());

	}

	@Override
	public void delete(Iterable<? extends Candidat> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Candidat> findByuniversiteOrigine(String universiteOrigine) {
		return listCandidat.stream().filter(element -> element.getUniversiteOrigine().matches(universiteOrigine))
				.collect(Collectors.toList());
	}
}
