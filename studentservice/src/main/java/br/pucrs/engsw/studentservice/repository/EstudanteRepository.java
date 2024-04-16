package br.pucrs.engsw.studentservice.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.pucrs.engsw.studentservice.model.Estudante;

public interface EstudanteRepository extends CrudRepository<Estudante, String> {
    public Estudante findByNroDocumento(String n);
    public List<Estudante> findByNome(String nome);
    public List<Estudante> findByEndereco(String endereco);
    public List<Estudante> findAll();
}
