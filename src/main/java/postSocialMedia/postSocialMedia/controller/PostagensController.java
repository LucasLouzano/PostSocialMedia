package postSocialMedia.postSocialMedia.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postSocialMedia.postSocialMedia.model.Postagens;
import postSocialMedia.postSocialMedia.service.impl.PostagensServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagensController {
    private PostagensServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Postagens>> getPostagens() {
        List<Postagens> postagens = service.findAll();
        if (postagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagens> getPostagensById(@PathVariable Long id) {
        Postagens postagens = service.findById(id);
        if (postagens == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);
    }

    @PostMapping
    public ResponseEntity<Postagens> savePost(Postagens postagens){
        Postagens post= service.save(postagens);
        return ResponseEntity.ok(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Postagens> updatePost(@PathVariable Long id, @RequestBody @Valid Postagens postagens){
        postagens.setId(id);
        Postagens postagens1 = service.update(postagens);
        return ResponseEntity.ok(postagens1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Postagens> deletePost(@PathVariable Long id){
        Postagens postagens = service.delete(id);
        if(postagens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagens);


    }
}

