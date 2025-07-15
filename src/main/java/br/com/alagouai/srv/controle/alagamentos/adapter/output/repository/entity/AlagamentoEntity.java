package br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;
    private Integer umidade;
    private Integer pressao;
    private Double velocidadeVento;
    private Double precipitacaoChuva;
    private String tempoChuva;
    private String intensidadeChuva;
    private Double pontoOrvalho;
    private Double latitude;
    private Double longitude;
    private String dataHora;
    private Integer historicoAlagamento;
    private String solo;
    private String bairro;
    private Double precipitacaoDiaria;
    private Double precipitacaoAcumulada;
}
