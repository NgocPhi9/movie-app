package group9.movie_app.controller;

import group9.movie_app.dto.WishlistDTO;
import group9.movie_app.entity.Wishlist;
import group9.movie_app.repository.WishlistRepository;
import group9.movie_app.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies-app")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/user/wishlist")
    public List<WishlistDTO> getAllWishlists() {
        return wishlistService.getWishlist();
    }

    @PostMapping("/user/wishlist/{moivieId}")
    public ResponseEntity<Wishlist> addWishlist(@PathVariable("moivieId") int moivieId) {
        return ResponseEntity.ok(wishlistService.addWishlist(moivieId));
    }

    @DeleteMapping("/user/wishlist/{wishlistId}")
    public ResponseEntity<String> deleteWishlist(@PathVariable("wishlistId") String wishlistId) {
        return ResponseEntity.ok("Delete wishlist ");
    }
}
