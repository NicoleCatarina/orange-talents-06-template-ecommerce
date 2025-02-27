package br.com.zupacademy.nicolecatarina.mercadolivre.produto.detalhes;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos/{id}")
public class ProdutoDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity detalharProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Produto de id %d não encontrado", id)));

        ProdutoDetalhesResponse produtoDetalhesResponse = new ProdutoDetalhesResponse(produto);

        return ResponseEntity.ok(produtoDetalhesResponse);
    }

}
