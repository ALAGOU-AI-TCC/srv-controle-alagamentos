package br.com.alagouai.srv.controle.alagamentos.core.usecase;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;
import br.com.alagouai.srv.controle.alagamentos.port.input.PreverAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.PredictionApiOutputPort;

import static br.com.alagouai.srv.controle.alagamentos.utils.Utils.formatadorDataUnix;
import static br.com.alagouai.srv.controle.alagamentos.utils.Utils.subtrairHorasUnix;

public class PreverAlagamentoUseCase implements PreverAlagamentoInputPort {

    private final OpenWeatherOutputPort openWeatherOutputPort;
    private final PredictionApiOutputPort predictionApiOutputPort;

    public PreverAlagamentoUseCase(OpenWeatherOutputPort openWeatherOutputPort,
                                   PredictionApiOutputPort predictionApiOutputPort) {
        this.openWeatherOutputPort = openWeatherOutputPort;
        this.predictionApiOutputPort = predictionApiOutputPort;
    }

    @Override
    public Previsao prever(Previsao previsao) {
        final String tsAtual = formatadorDataUnix(previsao.getTime());

        DadosClimaticos dadosClimaticosAtual =
                openWeatherOutputPort.buscarDadosClimaticos(previsao.getLatitude(), previsao.getLongitude(), tsAtual);
        dadosClimaticosAtual.setLatitude(Double.valueOf(previsao.getLatitude()));
        dadosClimaticosAtual.setLongitude(Double.valueOf(previsao.getLongitude()));
        Acumulados4h acumulados = calcularAcumulados4h(
                previsao.getLatitude(),
                previsao.getLongitude(),
                tsAtual,
                dadosClimaticosAtual
        );

        if (dadosClimaticosAtual != null) {
            dadosClimaticosAtual.setPrecipitacaoAcumulada(acumulados.precipitacaoAcumulada());
            dadosClimaticosAtual.setTempoChuva(acumulados.tempoChuvaHoras());
        }

        return predictionApiOutputPort.prever(dadosClimaticosAtual);
    }

    private Acumulados4h calcularAcumulados4h(String latitude,
                                              String longitude,
                                              String tsReferenciaSegundos,
                                              DadosClimaticos dadosClimaticosAtual) {
        double totalPrecip = 0.0;
        int totalHorasChuva = 0;

        totalPrecip += safeChuva(dadosClimaticosAtual);
        totalHorasChuva += choveuNestaHora(dadosClimaticosAtual);

        for (int h = 1; h <= 3; h++) {
            String ts = subtrairHorasUnix(tsReferenciaSegundos, h);
            DadosClimaticos d = openWeatherOutputPort.buscarDadosClimaticos(latitude, longitude, ts);
            totalPrecip += safeChuva(d);
            totalHorasChuva += choveuNestaHora(d);
        }

        totalPrecip = Math.max(0.0, totalPrecip);
        totalHorasChuva = Math.max(0, Math.min(4, totalHorasChuva));

        return new Acumulados4h(totalPrecip, totalHorasChuva);
    }

    private record Acumulados4h(double precipitacaoAcumulada, int tempoChuvaHoras) {}

    private int choveuNestaHora(DadosClimaticos a) {
        if (a == null) return 0;

        Integer t = a.getTempoChuva();
        if (t != null) return (t > 0) ? 1 : 0;

        double mm = safeChuva(a);
        return (mm > 0.0) ? 1 : 0;
    }

    private double safeChuva(DadosClimaticos a) {
        if (a == null) return 0.0;
        Double mm = a.getPrecipitacaoChuva();
        if (mm == null || Double.isNaN(mm) || Double.isInfinite(mm)) return 0.0;
        return Math.max(0.0, mm);
    }
}
