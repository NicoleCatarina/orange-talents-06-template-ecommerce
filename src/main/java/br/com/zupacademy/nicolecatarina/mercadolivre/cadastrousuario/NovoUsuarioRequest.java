package br.com.zupacademy.nicolecatarina.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
