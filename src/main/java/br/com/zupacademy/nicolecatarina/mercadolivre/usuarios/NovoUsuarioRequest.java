package br.com.zupacademy.nicolecatarina.mercadolivre.usuarios;

import br.com.zupacademy.nicolecatarina.mercadolivre.validation.annotation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaLimpa(senha));
    }
}
