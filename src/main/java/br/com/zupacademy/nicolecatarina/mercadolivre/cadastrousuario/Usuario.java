package br.com.zupacademy.nicolecatarina.mercadolivre.cadastrousuario;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    public Usuario(String email, SenhaLimpa senhaLimpa) {
        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

}
