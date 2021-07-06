package br.com.zupacademy.nicolecatarina.mercadolivre.categorias;

import br.com.zupacademy.nicolecatarina.mercadolivre.validation.annotation.ExistsId;
import br.com.zupacademy.nicolecatarina.mercadolivre.validation.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @ExistsId(domainClass = Categoria.class, fieldName = "idCategoriaMae")
    @JsonProperty("id_categoria_mae")
    private Long idCategoriaMae;

    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(this.nome);

        if (Objects.nonNull(idCategoriaMae)) {
            Optional<Categoria> categoriaMae = categoriaRepository.findById(this.idCategoriaMae);

            if (categoriaMae.isEmpty()) {
                throw new EntityNotFoundException(String.format("Não foi encontrada uma categoria com id %d", idCategoriaMae));
            }
            categoria.setCategoriaMae(categoriaMae.get());
        }
        return categoria;
    }
}
