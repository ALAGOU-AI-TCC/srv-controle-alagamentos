package br.com.alagouai.srv.controle.alagamentos.core.usecase;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;
import br.com.alagouai.srv.controle.alagamentos.port.input.PreverAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.PredictionApiOutputPort;

import static br.com.alagouai.srv.controle.alagamentos.utils.Utils.formatadorDataUnix;
import static br.com.alagouai.srv.controle.alagamentos.utils.Utils.subtrairHorasUnix;

public class PreverAlagamentoUseCase implements PreverAlagamentoInputPort {

    private final LogControlOutputPort log;
    private final OpenWeatherOutputPort openWeatherOutputPort;
    private final PredictionApiOutputPort predictionApiOutputPort;

    public PreverAlagamentoUseCase(LogControlOutputPort log,
                                   OpenWeatherOutputPort openWeatherOutputPort,
                                   PredictionApiOutputPort predictionApiOutputPort) {
        this.log = log;
        this.openWeatherOutputPort = openWeatherOutputPort;
        this.predictionApiOutputPort = predictionApiOutputPort;
    }

    @Override
    public Previsao prever(Previsao previsao) {
        final String tsAtual = formatadorDataUnix(previsao.getTime());

        Alagamento alagamentoAtual = openWeatherOutputPort.buscarDadosClimaticos(previsao.getLatitude(), previsao.getLongitude(), tsAtual);

        double precipAcum4h = calcularPrecipitacaoAcumulada4h(
                previsao.getLatitude(),
                previsao.getLongitude(),
                tsAtual,
                alagamentoAtual
        );

        int tempoChuva4h = calcularTempoChuvaAcumulado4h(
                previsao.getLatitude(),
                previsao.getLongitude(),
                tsAtual,
                alagamentoAtual
        );

        if (alagamentoAtual != null) {
            alagamentoAtual.setPrecipitacaoAcumulada(precipAcum4h);
            alagamentoAtual.setTempoChuva(tempoChuva4h);
        }

        Previsao resposta = predictionApiOutputPort.prever(alagamentoAtual);

        return resposta;
    }

    private double calcularPrecipitacaoAcumulada4h(String latitude,
                                                   String longitude,
                                                   String tsReferenciaSegundos,
                                                   Alagamento alagamentoAtual) {
        double total = 0.0;
        total += safeChuva(alagamentoAtual);

        for (int h = 1; h <= 3; h++) {
            String ts = subtrairHorasUnix(tsReferenciaSegundos, h);
            Alagamento a = openWeatherOutputPort.buscarDadosClimaticos(latitude, longitude, ts);
            total += safeChuva(a);
        }

        return Math.max(0.0, total);
    }


    private int calcularTempoChuvaAcumulado4h(String latitude,
                                              String longitude,
                                              String tsReferenciaSegundos,
                                              Alagamento alagamentoAtual) {
        int totalHoras = 0;

        totalHoras += choveuNestaHora(alagamentoAtual);

        for (int h = 1; h <= 3; h++) {
            String ts = subtrairHorasUnix(tsReferenciaSegundos, h);
            Alagamento a = openWeatherOutputPort.buscarDadosClimaticos(latitude, longitude, ts);
            totalHoras += choveuNestaHora(a);
        }

        if (totalHoras < 0) totalHoras = 0;
        if (totalHoras > 4) totalHoras = 4;

        return totalHoras;
    }

    private int choveuNestaHora(Alagamento a) {
        if (a == null) return 0;

        Integer t = a.getTempoChuva();
        if (t != null) {
            return (t > 0) ? 1 : 0;
        }

        double mm = safeChuva(a);
        return (mm > 0.0) ? 1 : 0;
    }

    private double safeChuva(Alagamento a) {
        if (a == null) return 0.0;
        Double mm = a.getPrecipitacaoChuva();
        if (mm == null || Double.isNaN(mm) || Double.isInfinite(mm)) return 0.0;
        return Math.max(0.0, mm);
    }
}
