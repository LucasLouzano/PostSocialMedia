package postSocialMedia.postSocialMedia.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.model.Posts;
import postSocialMedia.postSocialMedia.service.PostagensService;
import postSocialMedia.postSocialMedia.service.impl.PostagensServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostagensController {
    @Autowired
    private PostagensService service;

    @GetMapping
    public ResponseEntity<List<Posts>> getPostagens() {
        List<Posts> postagens = service.findAll();
        if (postagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posts> getPostagensById(@PathVariable Long id) {
        Posts postagens = service.findById(id);
        if (postagens == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @PostMapping
    public ResponseEntity<Posts> savePost(@RequestBody @Valid PostRequestDTO postagens){
        Posts post = service.save(postagens);
        return ResponseEntity.ok(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long id, @RequestBody @Valid Posts postagens){
        postagens.setId(id);
        Posts postagens1 = service.update(postagens);
        return ResponseEntity.ok(postagens1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Posts> deletePost(@PathVariable Long id){
        Posts postagens = service.delete(id);
        if(postagens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);


    }
}

