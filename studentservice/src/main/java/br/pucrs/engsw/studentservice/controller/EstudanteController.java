package br.pucrs.engsw.studentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.engsw.studentservice.model.Estudante;
import br.pucrs.engsw.studentservice.repository.EstudanteRepository;
import br.pucrs.engsw.studentservice.service.Validador;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "/estudante/")
public class EstudanteController {
    private EstudanteRepository repoEs;
    private Validador validador = new Validador();

    // @Autowired is not necessary in this case, but it is a good practice to use it. Is it??!!	
    public EstudanteController(EstudanteRepository repoEs) {
        this.repoEs = repoEs;
    }

    @PostMapping(value = "/regestu/")
    public ResponseEntity<String> registerStudent(@RequestBody Estudante std) {

        log.info("Estudante recebido: " + std);

        if (validador.eEstudanteValido(std))
            if (repoEs.findByNroDocumento(std.getNroDocumento()) != null)
                return ResponseEntity.badRequest().body("Estudante já registrado!");
            else {
                repoEs.save(std);
                return ResponseEntity.ok("Estudante registrado!");
            }
        else
            return ResponseEntity.badRequest().body("Estudante inválido!");

    }

    @GetMapping(value = "/liststu/")
    public ResponseEntity<Collection<Estudante>> listStudents() {
        return ResponseEntity.ok(repoEs.findAll());
    }
}
