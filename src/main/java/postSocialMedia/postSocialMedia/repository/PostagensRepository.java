package postSocialMedia.postSocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postSocialMedia.postSocialMedia.model.Posts;
@Repository
public interface PostagensRepository extends JpaRepository<Posts, Long> {
}
