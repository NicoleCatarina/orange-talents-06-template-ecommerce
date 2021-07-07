package br.com.zupacademy.nicolecatarina.mercadolivre.produto.caracteristicas;

import javax.validation.constraints.NotBlank;

public class ProdutoCaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public ProdutoCaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public ProdutoCaracteristica toModel() {
        return new ProdutoCaracteristica(this.nome, this.descricao);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
