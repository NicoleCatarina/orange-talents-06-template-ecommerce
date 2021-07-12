package br.com.zupacademy.nicolecatarina.mercadolivre.pagamento;

import br.com.zupacademy.nicolecatarina.mercadolivre.venda.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class PagamentoRequest {

    @NotBlank
    @JsonProperty("identificador_pagamento")
    private String identificadorPagamento;

    @NotBlank
    private String status;

    public PagamentoRequest(String identificadorPagamento, String status) {
        this.identificadorPagamento = identificadorPagamento;
        this.status = status;
    }

    public Pagamento toModel(Venda venda) {
        StatusPagamento statusPagamentoEValido = StatusPagamento.getEnumByString(this.status);

        if (Objects.isNull(statusPagamentoEValido)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A resposta do gateway não é válida para os gateways mapeados");
        }

        StatusPagamento statusPagamento = StatusPagamento.getEnumByString(this.status);

        return new Pagamento(this.identificadorPagamento, venda, statusPagamento);
    }

}
