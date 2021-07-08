package br.com.zupacademy.nicolecatarina.mercadolivre.produto.pergunta;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;

import javax.validation.constraints.NotBlank;

public class ProdutoPerguntaRequest {

    @NotBlank
    private String titulo;

    /**
     * Bug do jackson, nao aceitou o construtor com um parametro nem setTitulo
     */
    @Deprecated
    public ProdutoPerguntaRequest() {
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ProdutoPergunta toModel(Produto produto, Usuario usuario) {
        return new ProdutoPergunta(this.titulo, produto, usuario);
    }


}
