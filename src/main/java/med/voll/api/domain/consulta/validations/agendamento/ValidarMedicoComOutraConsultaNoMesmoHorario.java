package med.voll.api.domain.consulta.validations.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
