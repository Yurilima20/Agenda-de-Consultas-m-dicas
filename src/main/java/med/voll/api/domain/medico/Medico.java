package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.dtos.DadosAtualizadosMedico;
import med.voll.api.domain.medico.dtos.DadosCadastroMedico;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(){

    }

    public Medico(Long id, String nome, String email, String telefone, String crm, Especialidade
            especialidade, Endereco endereco, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Medico(DadosCadastroMedico dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public Long getId() {
        return id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void atualizarInformacoes(DadosAtualizadosMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir( ) {
        this.ativo = false;
    }
}
