package postSocialMedia.postSocialMedia.service;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.model.Posts;

import java.util.List;

public interface PostsService {
    List<PostsDTO> findAll();

    PostsDTO findById(Long id);

    PostsDTO save(Posts posts);

    Posts update(Posts postagens);

    PostsDTO delete(Long id);
}
