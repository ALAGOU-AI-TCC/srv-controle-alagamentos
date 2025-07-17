package br.com.alagouai.srv.controle.alagamentos.core.usecase;


import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static br.com.alagouai.srv.controle.alagamentos.core.common.constant.Constants.TIMEZONE_SAO_PAULO;

public class AtualizarPrecipitacaoUseCase implements AtualizarAlagamentoInputPort {

    private final LogControlOutputPort log;
    private final AlagamentosOutputPort alagamentosOutputPort;
    private final OpenWeatherOutputPort openWeatherOutputPort;

    public AtualizarPrecipitacaoUseCase(LogControlOutputPort log, AlagamentosOutputPort alagamentosOutputPort, OpenWeatherOutputPort openWeatherOutputPort) {
        this.log = log;
        this.alagamentosOutputPort = alagamentosOutputPort;
        this.openWeatherOutputPort = openWeatherOutputPort;
    }

    @Override
    public void atualizarPrecipitacaoAcumulada(Integer idControle, Integer limite) {
        log.logInfo("Iniciando processo de atualização da precipitacao acumulada e teempo de chuva para os registros a partir do Id {}", idControle);

        List<Alagamento> alagamentosList =  alagamentosOutputPort.buscarRegostrosPorIdControle(idControle, limite);
        var alagamentosListAtualizados = buscarRegistrosHorasAnteriores(alagamentosList);
        alagamentosOutputPort.atualizarRegistros(alagamentosListAtualizados);
    }

    private List<Alagamento> buscarRegistrosHorasAnteriores(List<Alagamento> alagamentos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zonaBrasil = ZoneId.of(TIMEZONE_SAO_PAULO);

        for (Alagamento alagamento : alagamentos) {
            if (alagamento.getPrecipitacaoAcumulada() == null || alagamento.getPrecipitacaoAcumulada() == 0.0) {
                alagamento.setTempoChuva(0);
                LocalDateTime dataHoraOriginal = LocalDateTime.parse(alagamento.getDataHora(), formatter);
                double acumulado = alagamento.getPrecipitacaoChuva() != null ? alagamento.getPrecipitacaoChuva() : 0.0;                for (int horaAnterior = 1; horaAnterior <= 3; horaAnterior++) {
                    long timestamp = dataHoraOriginal
                            .minusHours(horaAnterior)
                            .atZone(zonaBrasil)
                            .toEpochSecond();

                    Alagamento climaAnterior = openWeatherOutputPort.buscarDadosClimaticosDataAlterada(alagamento, timestamp);
                    if (climaAnterior != null && climaAnterior.getPrecipitacaoChuva() != null && climaAnterior.getPrecipitacaoChuva() > 0) {
                        acumulado += climaAnterior.getPrecipitacaoChuva();
                        alagamento.setTempoChuva(horaAnterior + 1);
                    }
                }

                alagamento.setPrecipitacaoAcumulada(acumulado);
            }
        }

        return alagamentos;
    }

}
