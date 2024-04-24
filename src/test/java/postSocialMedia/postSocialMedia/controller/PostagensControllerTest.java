package postSocialMedia.postSocialMedia.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.impl.PostsServiceImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostagensControllerTest {
    @InjectMocks
    private PostsController controller;
    @Mock
    private PostsServiceImpl service;
    @Mock
    private PostsMapper mapper;

    @Test
    void getPostagens() {

    }

    @Test
    void getPostagensById() {
    }

//    @Test
//    void savePost() {
//        var postsDTO = new PostsDTO();
//        postsDTO.setTexto("Texto");
//        postsDTO.setCreateDateTime(LocalDateTime.now());
//        when(service.save(any())).thenReturn(postsDTO);
//
//        var postRequestDTO = new PostRequestDTO();
//        postRequestDTO.setTexto(postsDTO.getTexto());
//
//        when(mapper.postsToPostsDTO(postRequestDTO)).thenReturn(postsDTO);
//
//       ResponseEntity <PostRequestDTO> responseEntity  = controller.savePost(postRequestDTO);
//
//        assertNotNull(responseEntity);
//        assertEquals(postsDTO.getTexto(),responseEntity.getTexto());
//        assertEquals(postsDTO.getCreateDateTime(),dto.getCreateDateTime());
//
//
//    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}