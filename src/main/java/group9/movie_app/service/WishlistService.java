package group9.movie_app.service;

import group9.movie_app.dto.WishlistDTO;
import group9.movie_app.entity.Movie;
import group9.movie_app.entity.Wishlist;
import group9.movie_app.repository.MovieRepository;
import group9.movie_app.repository.UserRepository;
import group9.movie_app.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<WishlistDTO> getWishlist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        return wishlistRepository.findByUser(user).stream()
                .map(wishlist -> new WishlistDTO(
                        wishlist.getWishlistId(),
                        wishlist.getMovie() //
                ))
                .collect(Collectors.toList());
    }

    public Wishlist addWishlist(int movieId) {
        Wishlist wishlist = new Wishlist();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        wishlist.setUser(user);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        wishlist.setMovie(movie);
        return wishlistRepository.save(wishlist);
    }

    public void removeWishlist(String wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
