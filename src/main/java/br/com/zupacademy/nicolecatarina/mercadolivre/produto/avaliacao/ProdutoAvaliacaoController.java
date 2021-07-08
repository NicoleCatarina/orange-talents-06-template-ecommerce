package br.com.zupacademy.nicolecatarina.mercadolivre.produto.avaliacao;

import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/avaliacoes")
public class ProdutoAvaliacaoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoAvaliacaoRepository produtoAvaliacaoRepository;

    @PostMapping
    public ResponseEntity criarAvaliacao(@PathVariable Long id,
                                @RequestBody @Valid ProdutoAvaliacaoRequest produtoAvaliacaoRequest,
                                @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Produto de id %s não encontrado", id));
        }

        Produto produto = possivelProduto.get();
        ProdutoAvaliacao novaAvaliacao = produtoAvaliacaoRequest.toModel(produto, usuarioLogado);
        produtoAvaliacaoRepository.save(novaAvaliacao);

        return ResponseEntity.ok().build();
    }

}
