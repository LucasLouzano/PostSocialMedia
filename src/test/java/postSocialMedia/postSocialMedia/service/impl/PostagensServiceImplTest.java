package postSocialMedia.postSocialMedia.service.impl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.repository.PostsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class PostagensServiceImplTest {

    @InjectMocks
    private PostsServiceImpl service;
    @Mock
    private PostsRepository repository;
    @Mock
    private PostsMapper mapper;
    @Test
    void findAll() {
        Posts post = new Posts();
        post.setId(1L);
        post.setTexto("Texto");
        post.setAuthor("author");
        post.setCreateDateTime(LocalDateTime.now());

        List<Posts> postsList = repository.findAll();
        postsList.add(post);
        when(repository.findAll()).thenReturn(postsList);

        PostsDTO postsDTO = new PostsDTO(post.getTexto());
        when(mapper.postsToPostsDTO(post)).thenReturn(postsDTO);

        List<PostsDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        PostsDTO postsDTO1 = response.get(0);
        assertEquals(post.getTexto(), postsDTO1.getTexto());

    }

    @Test
    void findById() {
        var posts = new Posts();
        posts.setId(1L);
        posts.setTexto("Texto");
        posts.setAuthor("author");
        posts.setCreateDateTime(LocalDateTime.now());
        when(repository.findById(1L)).thenReturn(Optional.of(posts));

        var postsDTO = new PostsDTO();
        postsDTO.setTexto(posts.getTexto());
        when(mapper.postsToPostsDTO(posts)).thenReturn(postsDTO);


        PostsDTO response = service.findById(1L);

        assertNotNull(response);
        assertEquals(posts.getTexto(), postsDTO.getTexto());
    }

    @Test
    void save() {
        var posts = new Posts();
        posts.setId(1L);
        posts.setTexto("Texto");
        posts.setAuthor("author");
        posts.setCreateDateTime(LocalDateTime.now());
        when(repository.save(any())).thenReturn(posts);

        var postsDTO = new PostsDTO();
        postsDTO.setTexto(postsDTO.getTexto());
        when(mapper.postsToPostsDTO(posts)).thenReturn(postsDTO);

        PostsDTO dto = service.save(posts);

        assertNotNull(dto);
        assertEquals(postsDTO.getTexto(),dto.getTexto());


    }

    @Test
    void update() {
        var posts = new Posts();
        posts.setId(1L);
        posts.setTexto("Texto");
        posts.setAuthor("author");
        posts.setCreateDateTime(LocalDateTime.now());
        when(repository.save(any())).thenReturn(posts);

        var postDTO = new PostsDTO();
        postDTO.setTexto(posts.getTexto());
        when(mapper.postsToPostsDTO(posts)).thenReturn(postDTO);
        PostsDTO postsDTO = service.update(posts);

        assertNotNull(postsDTO);
        assertEquals(postsDTO.getTexto(), postsDTO.getTexto());
        assertNotNull(postsDTO.getTexto());


    }

    @Test
    void delete() {
        var posts = new Posts();
        posts.setId(1L);
        posts.setTexto("Texto");
        posts.setAuthor("author");
        posts.setCreateDateTime(LocalDateTime.now());

        when(repository.findById(1L)).thenReturn(Optional.of(posts));

        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setTexto(posts.getTexto());
        when(mapper.postsToPostsDTO(posts)).thenReturn(postsDTO);

        PostsDTO deletedPostDTO = service.delete(1L);
        assertNotNull(deletedPostDTO);
        assertEquals(postsDTO.getTexto(), deletedPostDTO.getTexto());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(posts);

    }
}