package postSocialMedia.postSocialMedia.dto;

import lombok.*;


public class PostRequestDTO {
    private String texto;

    public PostRequestDTO() {
    }

    public PostRequestDTO(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
