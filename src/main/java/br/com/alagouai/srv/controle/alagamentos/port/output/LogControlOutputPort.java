package br.com.alagouai.srv.controle.alagamentos.port.output;

public interface LogControlOutputPort {

    void logInfo(String msg);

    void logInfo(String msg, Object... args);

    void logError(String msg, Object... args);
}
