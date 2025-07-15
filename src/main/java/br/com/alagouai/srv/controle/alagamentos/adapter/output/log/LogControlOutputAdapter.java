package br.com.alagouai.srv.controle.alagamentos.adapter.output.log;

import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogControlOutputAdapter implements LogControlOutputPort {


    @Override
    public void logInfo(String msg) {
        log.info(msg);
    }

    @Override
    public void logInfo(String msg, Object... args) {
        log.info(msg, args);
    }

    @Override
    public void logError(String msg, Object... args) {
        log.error(msg, args);
    }
}
