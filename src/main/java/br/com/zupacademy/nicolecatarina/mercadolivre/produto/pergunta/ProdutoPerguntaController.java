package br.com.zupacademy.nicolecatarina.mercadolivre.produto.pergunta;

import br.com.zupacademy.nicolecatarina.mercadolivre.email.EmailProdutoPerguntaEvent;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/perguntas")
public class ProdutoPerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoPerguntaRepository produtoPerguntaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    @Transactional
    public ResponseEntity criarPerguntas(@AuthenticationPrincipal Usuario usuarioLogado,
                                @PathVariable Long id,
                                @RequestBody @Valid ProdutoPerguntaRequest produtoPerguntaRequest) {

        if (Objects.isNull(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É necessário estar logado para realizar esta operação");
        }
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Não foi encontrado um produto com id %d", id));
        }

        Produto produto = possivelProduto.get();
        ProdutoPergunta novoProdutoPergunta = produtoPerguntaRequest.toModel(produto, usuarioLogado);
        EmailProdutoPerguntaEvent emailDePergunta = novoProdutoPergunta.criarEmail();

        publisher.publishEvent(emailDePergunta);

        produtoPerguntaRepository.save(novoProdutoPergunta);

        return ResponseEntity.ok().build();
    }

}
