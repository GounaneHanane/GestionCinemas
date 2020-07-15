package org.sid.cinema.dao;

import java.util.List;

import org.sid.cinema.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;;
@RepositoryRestResource
@CrossOrigin("*")
public interface FilmRepository extends JpaRepository<Film,Long> {
	public Page<Film> findBytitreContains(String mc, Pageable pageable);

	@Query(value = "SELECT * FROM film c, categorie o WHERE c.categorie_fk = o.id", nativeQuery = true)
	List<Film> findAllFilm();
}
