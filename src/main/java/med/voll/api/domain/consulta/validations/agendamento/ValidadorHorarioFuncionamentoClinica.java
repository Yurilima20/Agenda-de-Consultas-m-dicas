package med.voll.api.domain.consulta.validations.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDConsulta{

    public void validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
