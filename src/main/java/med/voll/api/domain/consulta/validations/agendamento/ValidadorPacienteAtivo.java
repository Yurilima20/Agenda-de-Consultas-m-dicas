package med.voll.api.domain.consulta.validations.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.exceptions.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDConsulta{
    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacenteEstaAtivo = repository.findAtivoById(dados.idPaciente());

        if(!pacenteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciuente excluído");
        }
    }
}
