package br.com.alagouai.srv.controle.alagamentos.adapter.input.controller;

import br.com.alagouai.srv.controle.alagamentos.adapter.input.request.RegistrarRelatoRequest;
import br.com.alagouai.srv.controle.alagamentos.adapter.input.response.ContarPertoResponse;
import br.com.alagouai.srv.controle.alagamentos.adapter.input.response.RelatoResponse;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.ContagemPerto;
import br.com.alagouai.srv.controle.alagamentos.port.input.ContarPertoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.input.ContarPertoUseCase;
import br.com.alagouai.srv.controle.alagamentos.port.input.EncontrarPertoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.input.RegistrarRelatoInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class RelatoAlagamentoController {

    private final RegistrarRelatoInputPort registrarRelato;
    private final EncontrarPertoInputPort encontrarPerto;
    private final ContarPertoInputPort contarPerto;


    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public RelatoResponse registrar(@Valid @RequestBody RegistrarRelatoRequest req,
                                    @RequestHeader(value = "X-Device-Id", required = false) String dispositivoId) {

        var salvo = registrarRelato.registrar(req.latitude(), req.longitude(), req.data_hora(), req.bairro(), dispositivoId);
        return new RelatoResponse(salvo.getId(), salvo.getLatitude(), salvo.getLongitude(),
                salvo.getBairro(), salvo.getInstanteEvento(), salvo.getInstanteRecebimento());
    }

    @GetMapping("/encontrarperto")
    public List<RelatoResponse> encontrarPerto(@RequestParam double latitude,
                                               @RequestParam double longitude,
                                               @RequestParam(name="raio_m", defaultValue="200") int raioMetros,
                                               @RequestParam(name="desde_min", defaultValue="180") int desdeMinutos) {
        return encontrarPerto.encontrarPerto(latitude, longitude, raioMetros, desdeMinutos)
                .stream()
                .map(relatoAlagamento -> new RelatoResponse(relatoAlagamento.getId(), relatoAlagamento.getLatitude(), relatoAlagamento.getLongitude(),
                        relatoAlagamento.getBairro(), relatoAlagamento.getInstanteEvento(), relatoAlagamento.getInstanteRecebimento()))
                .toList();
    }

    @GetMapping("/contarperto")
    public ContarPertoResponse contarPerto(@RequestParam double latitude,
                                           @RequestParam double longitude,
                                           @RequestParam(name="raio_m", defaultValue="200") int raioMetros,
                                           @RequestParam(name="desde_min", defaultValue="180") int desdeMinutos) {
        ContagemPerto contagemPerto = contarPerto.contarPerto(latitude, longitude, raioMetros, desdeMinutos);
        return new ContarPertoResponse(latitude, longitude, raioMetros, desdeMinutos,
                contagemPerto.totalRelatos(), contagemPerto.pessoasDistintas(), contagemPerto.ultimoInstanteEvento());
    }
}
