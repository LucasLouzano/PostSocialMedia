package postSocialMedia.postSocialMedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.PostagensService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostagensController {
    @Autowired
    private PostagensService service;
    @Operation(summary = "Obter todos os posts", description = "Retorna uma lista de todos os posts.")
    @GetMapping
    public ResponseEntity<List<Posts>> getPostagens() {
        List<Posts> postagens = service.findAll();
        if (postagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @Operation(summary = "Obter um post por ID", description = "Retorna um post específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post encontrado"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<Posts> getPostagensById(@PathVariable Long id) {
        Posts postagens = service.findById(id);
        if (postagens == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @Operation(summary = "Salvar um novo post", description = "Salva um novo post.")
    @PostMapping
    public ResponseEntity<Posts> savePost(@RequestBody @Valid PostRequestDTO postagens){
        Posts post = service.save(postagens);
        return ResponseEntity.ok(post);
    }
    @Operation(summary = "Atualizar um post existente", description = "Atualiza um post existente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long id, @RequestBody @Valid Posts postagens){
        postagens.setId(id);
        Posts postagens1 = service.update(postagens);
        return ResponseEntity.ok(postagens1);
    }
    @Operation(summary = "Deletar um post", description = "Deleta um post com base no ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Posts> deletePost(@PathVariable Long id){
        Posts postagens = service.delete(id);
        if(postagens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);


    }
}

