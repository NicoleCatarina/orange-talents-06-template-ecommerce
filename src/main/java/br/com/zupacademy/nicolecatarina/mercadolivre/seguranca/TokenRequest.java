package br.com.zupacademy.nicolecatarina.mercadolivre.seguranca;

public class TokenRequest {

    private String token;
    private String tipo;

    public TokenRequest(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

}
