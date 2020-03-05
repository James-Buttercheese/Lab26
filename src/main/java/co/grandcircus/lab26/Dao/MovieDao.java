package co.grandcircus.lab26.Dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.lab26.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, Long>{

	List<Movie> findByGenreContainingIgnoreCase(String category);
	List<Movie> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT DISTINCT genre FROM Movie")
	Set<String> findAllGenre();
}
