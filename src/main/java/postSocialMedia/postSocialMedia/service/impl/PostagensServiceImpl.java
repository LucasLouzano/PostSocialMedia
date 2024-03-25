package postSocialMedia.postSocialMedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
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

    @Override
    public List<Posts> findAll() {
        return repository.findAll();
    }

    @Override
    public Posts findById(Long id) {
        Optional<Posts> postagensOptional = repository.findById(id);
        return postagensOptional.orElse(null);
    }

    @Override
    public Posts save(PostRequestDTO postagens) {
        Posts posts = new Posts();
        posts.setCreateDateTime(LocalDateTime.now());
        posts.setTexto(postagens.text());
        return repository.save(posts);
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
    public Posts delete(Long id) {
        Optional<Posts> optionalPostagens = repository.findById(id);
        if (optionalPostagens.isPresent()) {
            Posts postsDelete = optionalPostagens.get();
            repository.delete(postsDelete);
            return postsDelete;
        } else {
            return null;
        }
    }
}

