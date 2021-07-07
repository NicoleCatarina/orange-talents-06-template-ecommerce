package br.com.zupacademy.nicolecatarina.mercadolivre.produto;

import br.com.zupacademy.nicolecatarina.mercadolivre.categorias.Categoria;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.caracteristicas.ProdutoCaracteristica;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.caracteristicas.ProdutoCaracteristicaRequest;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import br.com.zupacademy.nicolecatarina.mercadolivre.validation.annotation.ExistsId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero
    @JsonProperty("quantidade_disponivel")
    private int quantidadeDisponivel;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @Size(min = 3, max = 100)
    @Valid
    private List<ProdutoCaracteristicaRequest> caracteristicas;
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @JsonProperty("id_categoria")
    private Long idCategoria;

    public ProdutoRequest(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao, List<ProdutoCaracteristicaRequest> produtoCaracteristicaRequest, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = produtoCaracteristicaRequest;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(Categoria categoriaDoProduto, Usuario donoDoProduto) {
        Set<ProdutoCaracteristica> produtoCaracteristicas;

        produtoCaracteristicas = this.caracteristicas.stream()
                .map(ProdutoCaracteristicaRequest::toModel)
                .collect(Collectors.toSet());

        return new Produto(
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.descricao,
                produtoCaracteristicas,
                categoriaDoProduto,
                donoDoProduto);
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<ProdutoCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean temCaracteristicasRepetidas() {
        List<String> caracteristicasDepoisDoDistinct = this.caracteristicas.stream()
                .map(ProdutoCaracteristicaRequest::getNome)
                .distinct()
                .collect(Collectors.toList());

        return (caracteristicasDepoisDoDistinct.size() != this.caracteristicas.size());
    }

}
