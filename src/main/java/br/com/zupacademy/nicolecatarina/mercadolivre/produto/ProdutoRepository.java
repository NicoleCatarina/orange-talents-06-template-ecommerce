package br.com.zupacademy.nicolecatarina.mercadolivre.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
