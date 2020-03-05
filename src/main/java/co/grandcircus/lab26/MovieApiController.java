package co.grandcircus.lab26;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.lab26.Dao.MovieDao;
import co.grandcircus.lab26.entity.Movie;

@RestController
public class MovieApiController {

	@Autowired
	MovieDao movieDao;

	@GetMapping("/movies")
	public List<Movie> listProduct(@RequestParam(name = "genre", required = false) String genre,
			@RequestParam(name = "random", required = false) Integer random,
			@RequestParam(name = "name", required = false) String name) {

		List<Movie> movies = new ArrayList<>();

		if ((genre == null || genre.isEmpty()) && (random == null || random == 0) && (name == null || name.isEmpty())) {
			return movieDao.findAll();
		}

		else if ((random == null || random == 0) && (name == null || name.isEmpty())) {
			return movieDao.findByGenreContainingIgnoreCase(genre);
		}

		else if ((random == null || random == 0)) {
			return movieDao.findByNameContainingIgnoreCase(name);
		}

		else
			for (int i = 0; i < random; i++) {
				Random rand = new Random();
				List<Movie> movies2 = movieDao.findAll();
				int o = rand.nextInt(movies2.size() - 1);
				movies.add(movies2.get(o));
			}
		return movies;

	}

	@GetMapping("/movies/random")
	public Movie randomGenres(@RequestParam(name = "genre", required = false) String genre) {

		Random rand = new Random();

		if (genre == null || genre.isEmpty()) {

			List<Movie> movies = movieDao.findAll();

			int i = rand.nextInt(movies.size() - 1);

			return movies.get(i);
		}

		List<Movie> movies = movieDao.findByGenreContainingIgnoreCase(genre);

		if (movies.size() < 1) {
			Movie movie = new Movie();
			return movie;
		}
			
		
		int i = rand.nextInt(movies.size() - 1);
		Movie movie = movies.get(i);
		
		

		return movie;
	}

	@GetMapping("/genres")
	public Set<String> genres() {
		return movieDao.findAllGenre();
	}

}
