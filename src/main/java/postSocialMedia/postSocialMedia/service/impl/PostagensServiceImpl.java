package postSocialMedia.postSocialMedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.repository.PostagensRepository;
import postSocialMedia.postSocialMedia.service.PostagensService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostagensServiceImpl implements PostagensService {
    @Autowired
    private PostagensRepository repository;
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
        Posts postagensOptional = repository.findById(id).get();
        return postsMapper.postsToPostsDTO(postagensOptional);
    }

    @Override
    public PostsDTO save(Posts posts) {
        posts.setCreateDateTime(LocalDateTime.now());
        Posts posts1 = repository.save(posts);
        return postsMapper.postsToPostsDTO(posts1);
    }

    @Override
    public Posts update(Posts postagens) {
        postagens.setId(postagens.getId());
        postagens.setAuthor(postagens.getAuthor());
        postagens.setCreateDateTime(postagens.getCreateDateTime());
        repository.save(postagens);
        return postagens;
    }

    @Override
    public PostsDTO delete(Long id) {
        Posts postagens = repository.findById(id).get();
        repository.delete(postagens);
        return postsMapper.postsToPostsDTO(postagens);
    }
}
