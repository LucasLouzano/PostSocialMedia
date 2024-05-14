package postSocialMedia.postSocialMedia.dto;

public class PostsByIdResponse {
    private String texto;

    public PostsByIdResponse() {
    }

    public PostsByIdResponse(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
