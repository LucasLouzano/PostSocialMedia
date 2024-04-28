package postSocialMedia.postSocialMedia.dto;

public class PostsDTO {
    private String texto;


    public PostsDTO() {
    }

    public PostsDTO(String texto) {
        this.texto = texto;

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
