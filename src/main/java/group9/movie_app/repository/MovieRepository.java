package group9.movie_app.repository;

import group9.movie_app.entity.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByGenresContainingIgnoreCase(String genre);
    List<Movie> findByActorsContainingIgnoreCase(String actor);
    List<Movie> findByDirectorContainingIgnoreCase(String director);
    List<Movie> findByTypeContainingIgnoreCase(String type);

}
