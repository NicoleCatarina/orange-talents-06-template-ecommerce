package br.com.zupacademy.nicolecatarina.mercadolivre.pagamento;

import br.com.zupacademy.nicolecatarina.mercadolivre.venda.Venda;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificador;
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    @CreationTimestamp
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    public Pagamento(String identificador, Venda venda, StatusPagamento statusPagamento) {
        this.identificador = identificador;
        this.venda = venda;
        this.statusPagamento = statusPagamento;
    }

    public boolean sucessoNoPagamento() {
        return statusPagamento.isSucesso();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return identificador.equals(pagamento.identificador) && venda.equals(pagamento.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, venda);
    }

}
