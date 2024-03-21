package postSocialMedia.postSocialMedia.service;

import postSocialMedia.postSocialMedia.model.Postagens;

import java.util.List;

public interface PostagensService {
    List<Postagens> findAll();

    Postagens findById(Long id);

    Postagens save(Postagens postagens);

    Postagens update(Postagens postagens);

    Postagens delete(Long id);
}
