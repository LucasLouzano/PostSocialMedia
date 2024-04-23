package postSocialMedia.postSocialMedia.dto;

import java.time.LocalDateTime;

public class PostsDTO {
    private String texto;
    private LocalDateTime createDateTime;

    public PostsDTO() {
    }

    public PostsDTO(String texto, LocalDateTime createDateTime) {
        this.texto = texto;
        this.createDateTime = createDateTime;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
