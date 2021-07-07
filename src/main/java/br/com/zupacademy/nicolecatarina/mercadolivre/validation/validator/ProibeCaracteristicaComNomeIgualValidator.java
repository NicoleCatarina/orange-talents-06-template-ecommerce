//package br.com.zupacademy.nicolecatarina.mercadolivre.validation.validator;
//
//import br.com.zupacademy.nicolecatarina.mercadolivre.produto.ProdutoRequest;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return ProdutoRequest.class.isAssignableFrom(aClass);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        if (errors.hasErrors()) {
//            return;
//        }
//
//        ProdutoRequest produtoRequest = (ProdutoRequest) target;
//        boolean temCaracteristicasRepetidas = produtoRequest.temCaracteristicasRepetidas();
//
//        if (temCaracteristicasRepetidas) {
//            errors.rejectValue("caracteristicas", null, "Características repetidas não são aceitas");
//        }
//    }
//
//}
