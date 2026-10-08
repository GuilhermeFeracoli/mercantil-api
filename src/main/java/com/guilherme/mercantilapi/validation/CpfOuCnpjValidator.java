
package com.guilherme.mercantilapi.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOuCnpjValidator
        implements ConstraintValidator<CpfOuCnpj, DocumentoValidavel> {
    @Override
    public boolean isValid(
            DocumentoValidavel documento,
            ConstraintValidatorContext context) {
        if (documento == null) {
            return true;
        }
        String cpf = documento.getCpf();
        String cnpj = documento.getCnpj();
        if ((cpf == null || cpf.isBlank()) &&
                (cnpj == null || cnpj.isBlank())) {
            return false;
        }
        return true;
    }
}