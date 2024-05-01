package postSocialMedia.postSocialMedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.repository.PostsRepository;
import postSocialMedia.postSocialMedia.service.PostsService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsRepository repository;
    @Autowired
    private PostsMapper postsMapper;

    @Override
    public List<PostsDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(postsMapper::postsToPostsDTO).toList();
    }
    @Override
    public PostsDTO findById(Long id) {
        Posts posts = repository.findById(id).get();
        return postsMapper.postsToPostsDTO(posts);
    }
    @Override
    public PostsDTO save(Posts posts) {
        posts.setCreateDateTime(LocalDateTime.now());
        Posts post = repository.save(posts);
        return postsMapper.postsToPostsDTO(post);
    }
    @Override
    public PostsDTO update(Posts post) {
        Posts posts = repository.save(post);
        return postsMapper.postsToPostsDTO(posts);
    }
    @Override
    public PostsDTO delete(Long id) {
        Posts postagens = repository.findById(id).get();
        repository.delete(postagens);
        return postsMapper.postsToPostsDTO(postagens);
    }
}
