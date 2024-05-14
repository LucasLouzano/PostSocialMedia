package postSocialMedia.postSocialMedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postSocialMedia.postSocialMedia.dto.*;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.PostsService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostsController {
    @Autowired
    private PostsService service;
    @Autowired
    private PostsMapper postsMapper;
    @Operation(summary = "Obter todos os posts", description = "Retorna uma lista de todos os posts.")
    @GetMapping
    public ResponseEntity<List<ListPostsDTO>> getPostagens() {
        List<PostsDTO> postsDTO = service.findAll();
        List<ListPostsDTO> listPostsDTO = postsMapper.postsDtoToListPostsDto(postsDTO);
        if (listPostsDTO == null || listPostsDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listPostsDTO);
    }

    @Operation(summary = "Obter um post por ID", description = "Retorna um post específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post encontrado"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<PostsByIdResponse> getPostagensById(@PathVariable Long id) {
        PostsDTO postagens = service.findById(id);
        PostsByIdResponse postsByIdResponse = postsMapper.mapToDto(postagens);
        if (postsByIdResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postsByIdResponse);
    }

    @Operation(summary = "Salvar um novo post", description = "Salva um novo post.")
    @PostMapping
    public ResponseEntity<PostRequestDTO> postsSave(@RequestBody @Valid Posts posts){
        PostsDTO postsDTO = service.save(posts);
        PostRequestDTO postRequestDTO = postsMapper.postsDtoPostsDto(postsDTO);
        if (postRequestDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postRequestDTO);
    }

    @Operation(summary = "Atualizar um post existente", description = "Atualiza um post existente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<PostsDTOInfo> updatePost(@PathVariable Long id, @RequestBody @Valid Posts posts){
        posts.setId(id);
        posts.setCreateDateTime(LocalDateTime.now());
        PostsDTO postsDTO = service.update(posts);
        PostsDTOInfo postsDTOInfo = postsMapper.mapPostToDto(postsDTO);
        if (postsDTOInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(postsDTOInfo);
    }
    @Operation(summary = "Deletar um post", description = "Deleta um post com base no ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostsDTO> deletePost(@PathVariable Long id){
        PostsDTO postagens = service.delete(id);
        if(postagens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);


    }
}

