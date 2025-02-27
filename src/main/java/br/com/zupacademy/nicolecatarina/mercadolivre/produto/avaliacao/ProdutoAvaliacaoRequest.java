package br.com.zupacademy.nicolecatarina.mercadolivre.produto.avaliacao;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProdutoAvaliacaoRequest {

    @Min(1)
    @Max(5)
    @NotNull
    private int nota;
    @NotBlank
    private String titulo;
    @Length(min = 3, max = 500)
    @NotBlank
    private String descricao;

    public ProdutoAvaliacaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public ProdutoAvaliacao toModel(Produto produto, Usuario usuario) {
        return new ProdutoAvaliacao(this.nota, this.titulo, this.descricao, produto, usuario);
    }

}
