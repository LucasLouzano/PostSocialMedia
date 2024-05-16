package postSocialMedia.postSocialMedia.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import postSocialMedia.postSocialMedia.dto.*;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.impl.PostsServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");

        List<PostsDTO> postsDTOList = new ArrayList<>();
        postsDTOList.add(postsDTO);

        when(service.findAll()).thenReturn(postsDTOList);

        var listPostsDTO = new ListPostsDTO();
        listPostsDTO.setTexto("Texto");

        List<ListPostsDTO> dtoList = new ArrayList<>();
        dtoList.add(listPostsDTO);
        when(mapper.postsDtoToListPostsDto(postsDTOList)).thenReturn(dtoList);


        ResponseEntity<List<ListPostsDTO>> respostas = controller.getPostagens();

        assertNotNull(respostas);
        assertEquals(HttpStatus.OK, respostas.getStatusCode());
        assertEquals(1, respostas.getBody().size());
        assertEquals("Texto", respostas.getBody().get(0).getTexto());
    }

    @Test
    void getPostagensNotFound() {
        List<PostsDTO> emptyPostsDTOList = new ArrayList<>();
        when(service.findAll()).thenReturn(emptyPostsDTOList);


        List<ListPostsDTO> emptyListPostsDTO = new ArrayList<>();
        when(mapper.postsDtoToListPostsDto(emptyPostsDTOList)).thenReturn(emptyListPostsDTO);


        ResponseEntity<List<ListPostsDTO>> response = controller.getPostagens();
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);

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
        assertEquals(HttpStatus.OK, responseSuccess.getStatusCode());
        assertEquals(postsByIdResponse, responseSuccess.getBody());
        assertNotNull(responseSuccess);

        when(mapper.mapToDto(postsDTO)).thenReturn(null);

        ResponseEntity<PostsByIdResponse> responseNotFound = controller.getPostagensById(1L);
        assertNotNull(responseNotFound);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
    }

    @Test
    void savPostNotFound() {
        var posts = new Posts();
        posts.setTexto("Texto");
        when(service.save(any())).thenThrow(new EntityNotFoundException("Post not found"));

        ResponseEntity<PostRequestDTO> postsNotFound = controller.postsSave(posts);
        assertNotNull(postsNotFound);
        assertEquals(HttpStatus.NOT_FOUND, postsNotFound.getStatusCode());

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

        ResponseEntity<PostRequestDTO> resposta = controller.postsSave(posts);

        assertNotNull(resposta);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(postRequestDTO, resposta.getBody());

    }

    @Test
    void updatePost() {
        var posts = new Posts();
        posts.setTexto("Texto");

        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");
        when(service.update(any())).thenReturn(postsDTO);

        var postsDTOInfo = new PostsDTOInfo();
        postsDTOInfo.setTexto("Texto");
        when(mapper.mapPostToDto(postsDTO)).thenReturn(postsDTOInfo);

        ResponseEntity<PostsDTOInfo> infoResponseEntity = controller.updatePost(1L, posts);

        assertNotNull(infoResponseEntity);
        assertEquals(HttpStatus.OK, infoResponseEntity.getStatusCode());
        assertEquals(postsDTOInfo, infoResponseEntity.getBody());
    }

    @Test
    void updatePostNotFound() {
        var posts = new Posts();
        posts.setTexto("Texto");

        when(service.update(any())).thenReturn(null);
        when(mapper.mapPostToDto(any())).thenReturn(null);

        ResponseEntity<PostsDTOInfo> infoResponseEntity = controller.updatePost(1L, posts);
        assertNotNull(infoResponseEntity);
        assertEquals(HttpStatus.NOT_FOUND, infoResponseEntity.getStatusCode());
    }

    @Test
    void deletePost() {
        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");
        when(service.delete(anyLong())).thenReturn(postsDTO);

        ResponseEntity<PostsDTO> postDelete = controller.deletePost(1L);
        assertNotNull(postDelete);
        assertEquals(HttpStatus.OK, postDelete.getStatusCode());
        assertEquals(postsDTO, postDelete.getBody());
    }

    @Test
    void deletePostNotFound() {
        var postsDTO = new PostsDTO();
        postsDTO.setTexto("Texto");
        when(service.delete(anyLong())).thenReturn(null);

        ResponseEntity<PostsDTO> deleteNotFound = controller.deletePost(1L);
        assertNotNull(deleteNotFound);
        assertEquals(HttpStatus.NOT_FOUND, deleteNotFound.getStatusCode());

    }
}