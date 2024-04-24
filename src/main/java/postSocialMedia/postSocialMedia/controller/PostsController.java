package postSocialMedia.postSocialMedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.PostsService;

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
    public ResponseEntity<List<PostsDTO>> getPostagens() {
        List<PostsDTO> posts = service.findAll();
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Obter um post por ID", description = "Retorna um post específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post encontrado"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<PostsDTO> getPostagensById(@PathVariable Long id) {
        PostsDTO postagens = service.findById(id);
        if (postagens == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @Operation(summary = "Salvar um novo post", description = "Salva um novo post.")
    @PostMapping
    public ResponseEntity<PostRequestDTO> savePost(@RequestBody @Valid Posts posts){
        PostsDTO postsDTO = service.save(posts);
        if (postsDTO == null){
            return ResponseEntity.notFound().build();
        }
        PostRequestDTO postRequestDTO = postsMapper.postsDtoPostsDto(postsDTO);
        return ResponseEntity.ok(postRequestDTO);
    }

    @Operation(summary = "Atualizar um post existente", description = "Atualiza um post existente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long id, @RequestBody @Valid Posts post){
        post.setId(id);
        Posts postagens1 = service.update(post);
        if (postagens1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(postagens1);
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

