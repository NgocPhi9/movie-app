package group9.movie_app.repository;

import group9.movie_app.entity.User;
import group9.movie_app.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, String> {
    List<Wishlist> findByUser(User user);
}
