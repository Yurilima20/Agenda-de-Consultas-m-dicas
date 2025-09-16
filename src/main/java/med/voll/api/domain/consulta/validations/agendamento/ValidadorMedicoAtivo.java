package med.voll.api.domain.consulta.validations.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.exceptions.ValidacaoException;
import med.voll.api.repository.MedicoRepsository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDConsulta{
    @Autowired
    private MedicoRepsository repsository;

    public void validar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repsository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com medico excluído");
        }
    }
}
