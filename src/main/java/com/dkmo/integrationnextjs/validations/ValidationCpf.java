package com.dkmo.integrationnextjs.validations;

import org.springframework.stereotype.Component;
import com.dkmo.integrationnextjs.interfaces.CpfValidator;
import br.com.caelum.stella.validation.CPFValidator;
@Component
public class ValidationCpf  implements CpfValidator {
    @Override
    public boolean validateCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
         cpfValidator.assertValid(cpf);
         return true; 
        } catch (Exception e) {
         e.printStackTrace();
         return false;
        }
     }
}
