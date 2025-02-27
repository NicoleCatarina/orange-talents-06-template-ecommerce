package br.com.zupacademy.nicolecatarina.mercadolivre.produto.detalhes;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;

import java.math.BigDecimal;

public class ProdutoDetalheDto {

    private String nome;
    private String descricao;
    private BigDecimal preco;

    public ProdutoDetalheDto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

}
