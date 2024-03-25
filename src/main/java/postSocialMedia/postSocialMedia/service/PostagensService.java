package postSocialMedia.postSocialMedia.service;

import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.model.Posts;

import java.util.List;

public interface PostagensService {
    List<Posts> findAll();

    Posts findById(Long id);

    Posts save(PostRequestDTO postagens);

    Posts update(Posts postagens);

    Posts delete(Long id);
}
