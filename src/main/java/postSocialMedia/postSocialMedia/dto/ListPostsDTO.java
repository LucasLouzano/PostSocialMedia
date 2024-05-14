package postSocialMedia.postSocialMedia.dto;


public class ListPostsDTO {
    private String texto;

    public ListPostsDTO() {
    }

    public ListPostsDTO(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
