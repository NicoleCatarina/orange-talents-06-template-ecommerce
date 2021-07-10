package br.com.zupacademy.nicolecatarina.mercadolivre.usuarios;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    @CreationTimestamp
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Perfil> perfis = new HashSet<>();

    @OneToMany(mappedBy = "donoDoProduto", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

    public boolean eDonoDoProduto(Produto produto) {
        return this.produtos.contains(produto);
    }

    public String getLogin() {
        return this.login;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return this.login;
    }
}
