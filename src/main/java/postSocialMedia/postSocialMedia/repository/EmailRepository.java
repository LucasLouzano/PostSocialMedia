package postSocialMedia.postSocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postSocialMedia.postSocialMedia.model.Email;

import java.util.UUID;
@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
}
