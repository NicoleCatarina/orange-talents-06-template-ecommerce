package br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.vendedor.ranking;

import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import br.com.zupacademy.nicolecatarina.mercadolivre.venda.Venda;
import br.com.zupacademy.nicolecatarina.mercadolivre.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios/ranking-vendedor")
public class VendedorRankingController {

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public ResponseEntity pontuarVendedor(@RequestBody @Valid VendedorRankingRequest vendedorRankingRequest) {
        Venda venda = vendaRepository.findById(vendedorRankingRequest.getIdVenda())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Não foi encontrada uma venda com id %d", vendedorRankingRequest.getIdVenda()))
                );

        Usuario vendedor = venda.getVendedor();

        return ResponseEntity.ok().build();
    }
}
