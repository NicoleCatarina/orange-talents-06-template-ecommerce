package br.com.zupacademy.nicolecatarina.mercadolivre.produto.imagem;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;

import javax.persistence.*;

@Entity
public class ProdutoImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public ProdutoImagem(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public ProdutoImagem() {

    }

    public String getLink() {
        return link;
    }

}
