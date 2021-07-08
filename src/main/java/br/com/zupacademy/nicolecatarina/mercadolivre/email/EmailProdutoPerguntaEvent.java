package br.com.zupacademy.nicolecatarina.mercadolivre.email;


import br.com.zupacademy.nicolecatarina.mercadolivre.email.Email;
import br.com.zupacademy.nicolecatarina.mercadolivre.produto.pergunta.ProdutoPergunta;

public class EmailProdutoPerguntaEvent implements Email {

    private ProdutoPergunta pergunta;

    public EmailProdutoPerguntaEvent(ProdutoPergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public String getTitulo() {
        return this.pergunta.getTitulo();
    }

    @Override
    public String getRemetente() {
        return this.pergunta.getEmailDoCurioso();
    }

    @Override
    public String getDestinatario() {
        return this.pergunta.getEmailDoVendedor();
    }

}
