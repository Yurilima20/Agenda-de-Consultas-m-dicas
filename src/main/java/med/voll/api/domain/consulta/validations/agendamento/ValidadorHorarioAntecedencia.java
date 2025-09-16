package med.voll.api.domain.consulta.validations.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDConsulta{

    public void validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        var diferencasEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencasEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
