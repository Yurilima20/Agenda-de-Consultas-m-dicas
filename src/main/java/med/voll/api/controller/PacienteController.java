package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.dtos.DadosAtualizadosPaciente;
import med.voll.api.domain.paciente.dtos.DadosCadastroPaciente;
import med.voll.api.domain.paciente.dtos.DadosListagemPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPacientes(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(p -> new DadosListagemPaciente(p.getId(), p.getNome(), p.getEmail(), p.getCpf()));
    }

    @PutMapping
    @Transactional
    public void atualizarPacientes(@RequestBody @Valid DadosAtualizadosPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void excluirPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
