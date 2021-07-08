package br.com.zupacademy.nicolecatarina.mercadolivre.produto.pergunta;

import br.com.zupacademy.nicolecatarina.mercadolivre.email.EmailProdutoPerguntaEvent;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProdutoPergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @CreationTimestamp
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public ProdutoPergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Deprecated
    public ProdutoPergunta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEmailDoVendedor() {
        return this.produto.getEmailDoDonoDoProduto();
    }

    public String getEmailDoCurioso() {
        return this.usuario.getEmail();
    }

    public String getNomeDoProduto() {
        return this.produto.getNome();
    }

    public EmailProdutoPerguntaEvent criarEmail() {
        return new EmailProdutoPerguntaEvent(this);
    }

}
