package br.com.zupacademy.nicolecatarina.mercadolivre.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class CadastrarUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void cadastrarUsuarios(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.toModel();
        usuarioRepository.save(usuario);
    }
}
