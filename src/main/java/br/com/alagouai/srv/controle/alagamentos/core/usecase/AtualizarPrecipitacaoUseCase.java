package br.com.alagouai.srv.controle.alagamentos.core.usecase;


import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        List<DadosClimaticos> alagamentosList =  alagamentosOutputPort.buscarRegostrosPorIdControle(idControle, limite);
        var alagamentosListAtualizados = buscarRegistrosHorasAnteriores(alagamentosList);
        alagamentosOutputPort.atualizarRegistros(alagamentosListAtualizados);
    }

    private List<DadosClimaticos> buscarRegistrosHorasAnteriores(List<DadosClimaticos> dadosClimaticos) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");


        ZoneId zonaBrasil = ZoneId.of(TIMEZONE_SAO_PAULO);

        for (DadosClimaticos dadoClimatico : dadosClimaticos) {
            if (dadoClimatico.getPrecipitacaoAcumulada() == null || dadoClimatico.getPrecipitacaoAcumulada() == 0.0) {
                dadoClimatico.setTempoChuva(0);
                String dataHora = dadoClimatico.getDataHora();
                if (dataHora != null && dataHora.length() == 16) {
                    dataHora += ":00";
                }
                assert dataHora != null;
                LocalDateTime dataHoraOriginal;
                try {
                    dataHoraOriginal = LocalDateTime.parse(dataHora, formatter1);
                } catch (DateTimeParseException e) {
                    dataHoraOriginal = LocalDateTime.parse(dataHora, formatter2);
                }
                double acumulado = dadoClimatico.getPrecipitacaoChuva() != null ? dadoClimatico.getPrecipitacaoChuva() : 0.0;
                for (int horaAnterior = 1; horaAnterior <= 3; horaAnterior++) {
                    long timestamp = dataHoraOriginal
                            .minusHours(horaAnterior)
                            .atZone(zonaBrasil)
                            .toEpochSecond();

                    DadosClimaticos climaAnterior = openWeatherOutputPort.buscarDadosClimaticos(String.valueOf(dadoClimatico.getLatitude()), String.valueOf(dadoClimatico.getLongitude()), String.valueOf(timestamp));
                    if (climaAnterior != null && climaAnterior.getPrecipitacaoChuva() != null && climaAnterior.getPrecipitacaoChuva() > 0) {
                        acumulado += climaAnterior.getPrecipitacaoChuva();
                        dadoClimatico.setTempoChuva(horaAnterior + 1);
                    }
                }
                dadoClimatico.setPrecipitacaoAcumulada(acumulado);
            }
        }

        return dadosClimaticos;
    }

}
