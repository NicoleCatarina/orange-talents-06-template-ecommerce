package br.com.zupacademy.nicolecatarina.mercadolivre.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    public CategoriaRepository categoriaRepository;

    @PostMapping
    public void criarCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria categoria = novaCategoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
    }

}
