package br.com.zupacademy.nicolecatarina.mercadolivre.venda.gateway;

public enum GatewayPagamento {

    PAYPAL("paypal.com?buyerId={%s}&redirectUrl={http://localhost:8080/retorno-paypal/%s}"),
    PAGSEGURO("pagseguro.com?returnId={%s}&redirectUrl={http://localhost:8080/retorno-pagseguro/%s}");

    private String urlRetorno;

    GatewayPagamento(String urlRetorno) {
        this.urlRetorno = urlRetorno;
    }

    public String getUrlRetorno(Long idVenda) {
        return String.format(this.urlRetorno, idVenda, idVenda);
    }

}
