package br.pucrs.engsw.studentservice.service;

import br.pucrs.engsw.studentservice.model.Estudante;

public class Validador {
    public boolean eEstudanteValido(Estudante std) {
        return std != null && std.getNroDocumento() != null && std.getNome() != null && std.getEndereco() != null;
    }
}
