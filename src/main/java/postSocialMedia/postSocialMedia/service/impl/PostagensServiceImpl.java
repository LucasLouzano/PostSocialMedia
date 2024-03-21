package postSocialMedia.postSocialMedia.service.impl;

import org.springframework.stereotype.Service;
import postSocialMedia.postSocialMedia.model.Postagens;
import postSocialMedia.postSocialMedia.repository.PostagensRepository;
import postSocialMedia.postSocialMedia.service.PostagensService;

import java.util.List;
import java.util.Optional;

@Service
public class PostagensServiceImpl implements PostagensService {
    private PostagensRepository repository;

    @Override
    public List<Postagens> findAll() {
        return repository.findAll();
    }

    @Override
    public Postagens findById(Long id) {
        Optional<Postagens> postagensOptional = repository.findById(id);
        return postagensOptional.orElse(null);
    }

    @Override
    public Postagens save(Postagens postagens) {
        Postagens postagens1 = repository.save(postagens);
        return postagens1;
    }

    @Override
    public Postagens update(Postagens postagens) {
        postagens.setId(postagens.getId());
        postagens.setAuthor(postagens.getAuthor());
        postagens.setCreateDateTime(postagens.getCreateDateTime());
        repository.save(postagens);
        return postagens;
    }

    @Override
    public Postagens delete(Long id) {
        Optional<Postagens> optionalPostagens = repository.findById(id);
        if (optionalPostagens.isPresent()) {
            Postagens postsDelete = optionalPostagens.get();
            repository.delete(postsDelete);
            return postsDelete;
        } else {
            return null;
        }
    }
}

