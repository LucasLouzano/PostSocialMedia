package postSocialMedia.postSocialMedia.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.dto.PostsByIdResponse;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.impl.PostsServiceImpl;

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
        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");
        when(service.findById(1L)).thenReturn(postsDTO);

        var postsByIdResponse = new PostsByIdResponse();
        postsByIdResponse.setTexto("Texto");
        when(mapper.mapToDto(postsDTO)).thenReturn(postsByIdResponse);

        ResponseEntity<PostsByIdResponse> responseSuccess = controller.getPostagensById(1L);
        assertNotNull(responseSuccess);
        assertEquals(HttpStatus.OK,responseSuccess.getStatusCode());
        assertEquals(postsByIdResponse,responseSuccess.getBody());
        assertNotNull(responseSuccess);

        when(mapper.mapToDto(postsDTO)).thenReturn(null);

        ResponseEntity<PostsByIdResponse> responseNotFound = controller.getPostagensById(1L);
        assertNotNull(responseNotFound);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
    }

    @Test
    void savPost() {
        var posts = new Posts();
        posts.setTexto("Texto");

        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");
        when(service.save(any())).thenReturn(postsDTO);

        var postRequestDTO = new PostRequestDTO();
        postRequestDTO.setTexto("Texto");
        when(mapper.postsDtoPostsDto(postsDTO)).thenReturn(postRequestDTO);

       ResponseEntity<PostRequestDTO> resposta  = controller.postsSave(posts);

        assertNotNull(resposta);
        assertEquals(HttpStatus.OK,resposta.getStatusCode());
        assertEquals(postRequestDTO,resposta.getBody());



    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}