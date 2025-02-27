package br.com.zupacademy.nicolecatarina.mercadolivre.venda;

import br.com.zupacademy.nicolecatarina.mercadolivre.pagamento.Pagamento;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.Produto;
import br.com.zupacademy.nicolecatarina.mercadolivre.usuarios.Usuario;
import br.com.zupacademy.nicolecatarina.mercadolivre.venda.gateway.GatewayPagamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda = StatusVenda.INICIADA;
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
//    @ManyToMany
//    @JoinTable(name = "venda_produto",
//    joinColumns = @JoinColumn(name = "id_venda"),
//    inverseJoinColumns = @JoinColumn(name ="id_produto"))
//    private List<Produto> produtos;
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    private Usuario comprador;
    private BigDecimal valor;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<Pagamento> pagamentos;

    public Venda(GatewayPagamento gatewayPagamento, Produto produto, int quantidade, Usuario comprador) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }

    @Deprecated
    public Venda() {
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public GatewayPagamento getGatewayVenda() {
        return gatewayPagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getVendedor() {
        return this.produto.getDonoDoProduto();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getEmailDoComprador() {
        return this.comprador.getEmail();
    }

    public Produto getProduto() {
        return produto;
    }

    public String getUrlDeRetorno() {
        return this.gatewayPagamento.getUrlRetorno(this.id);
    }

    public void adicionarTentativaPagamento(Pagamento novoPagamento) {
        this.pagamentos.add(novoPagamento);

        if (novoPagamento.sucessoNoPagamento()) {
            this.statusVenda = StatusVenda.FINALIZADA;
        }
    }

    public boolean taPaga() {
        return this.statusVenda.equals(StatusVenda.FINALIZADA);
    }
}
