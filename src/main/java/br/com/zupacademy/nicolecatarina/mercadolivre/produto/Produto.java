package br.com.zupacademy.nicolecatarina.mercadolivre.produto;

import br.com.zupacademy.nicolecatarina.mercadolivre.categorias.Categoria;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.caracteristicas.ProdutoCaracteristica;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private int quantidadeDisponivel;
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduto")
    private Set<ProdutoCaracteristica> caracteristicas;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario donoDoProduto;
    @CreationTimestamp
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao,
                   Set<ProdutoCaracteristica> caracteristicas, Categoria categoria, Usuario donoDoProduto) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.donoDoProduto = donoDoProduto;

        Assert.isTrue(this.caracteristicas.size() >= 3, "É necessário ao menos 3 características para um produto");
    }

    @Deprecated
    public Produto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidadeDisponivel, descricao, caracteristicas, categoria, donoDoProduto, instanteCriacao);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Usuario getDonoDoProduto() {
        return this.donoDoProduto;
    }

    public String getEmailDoDonoDoProduto() {
        return this.donoDoProduto.getEmail();
    }

    public boolean possuiQuantidadeEmEstoque(Long quantidade) {
        return this.quantidadeDisponivel >= quantidade;
    }

}
