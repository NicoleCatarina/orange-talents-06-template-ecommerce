package br.com.zupacademy.nicolecatarina.mercadolivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoImagemRequest {

    @Size(min = 1, max = 10)
    @NotNull
    private List<MultipartFile> imagens;

    public ProdutoImagemRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

}
