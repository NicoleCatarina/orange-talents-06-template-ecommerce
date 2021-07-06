package br.com.zupacademy.nicolecatarina.mercadolivre.categorias;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_categoria_mae")
    private Categoria categoriaMae;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

//Uso exclusivo do Hibernate
    @Deprecated
    public Categoria() {
    }
}
