package group9.movie_app.service;

import group9.movie_app.dto.MovieRequest;
import group9.movie_app.entity.Movie;
import group9.movie_app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setViews(movie.getViews() + 1);
        movieRepository.save(movie);
        return movie;
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        return movieRepository.findByGenresContainingIgnoreCase(genre);
    }

    public List<Movie> searchMoviesByActor(String actor) {
        return movieRepository.findByActorsContainingIgnoreCase(actor);
    }

    public List<Movie> searchMoviesByDirector(String director) {
        return movieRepository.findByDirectorContainingIgnoreCase(director);
    }

    public List<Movie> searchMoviesByType(String type) {
        return movieRepository.findByTypeContainingIgnoreCase(type);
    }

    public Movie updateMovie(int movieId, MovieRequest request) {
        Movie movie = getMovieById(movieId);
        movie.setTitle(request.getTitle());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setTrailerLink(request.getTrailerLink());
        movie.setPosterLink(request.getPosterLink());
        movie.setActors(request.getActors());
        movie.setDirector(request.getDirector());
        movie.setDescription(request.getDescription());
        movie.setGenres(request.getGenres());
        movie.setType(request.getType());
        return movieRepository.save(movie);
    }

    public Movie createMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setTrailerLink(request.getTrailerLink());
        movie.setPosterLink(request.getPosterLink());
        movie.setActors(request.getActors());
        movie.setDirector(request.getDirector());
        movie.setDescription(request.getDescription());
        movie.setGenres(request.getGenres());
        movie.setType(request.getType());
        return movieRepository.save(movie);
    }

    public void deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
    }

}
