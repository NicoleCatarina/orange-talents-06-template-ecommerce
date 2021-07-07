package br.com.zupacademy.nicolecatarina.mercadolivre.produto;

import br.com.zupacademy.nicolecatarina.mercadolivre.categorias.Categoria;
import br.com.zupacademy.nicolecatarina.mercadolivre.categorias.CategoriaRepository;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

//    @InitBinder
//    public void init(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
//    }

    @PostMapping
    @Transactional
    public ResponseEntity criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                                @AuthenticationPrincipal Usuario usuarioLogado) {
        Categoria categoria = categoriaRepository.findById(produtoRequest.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe uma categoria com id %d", produtoRequest.getIdCategoria())));

        Produto novoProduto = produtoRequest.toModel(categoria, usuarioLogado);
        produtoRepository.save(novoProduto);
        return ResponseEntity.ok().build();
    }

}
