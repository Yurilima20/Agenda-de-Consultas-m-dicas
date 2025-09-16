package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.dtos.DadosAtualizadosMedico;
import med.voll.api.domain.medico.dtos.DadosCadastroMedico;
import med.voll.api.domain.medico.dtos.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.dtos.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.MedicoRepsository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepsository repository;

    @PostMapping
    @Transactional
    public ResponseEntity  cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){

        var medico = new Medico(dados);

        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"})  Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(m -> new DadosListagemMedico(m.getId(), m.getNome(), m.getEmail(),
                m.getCrm(), m.getEspecialidade()));

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity  atualizar(@RequestBody @Valid DadosAtualizadosMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    /*com o  @Transactional não precisamos falar de forma explicitada para deletar ou autualizar
    pois como houve mudança de atributo a jpa irá relaizar o update automatico, como aconteceu nesse
     no método de cima.*/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity  excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}