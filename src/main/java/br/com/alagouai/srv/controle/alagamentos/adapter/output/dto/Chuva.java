package br.com.alagouai.srv.controle.alagamentos.adapter.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Chuva {

    @JsonProperty("1h")
    private double umaHora;

    public double getUmaHora() {
        return umaHora;
    }

    public void setUmaHora(double umaHora) {
        this.umaHora = umaHora;
    }
}
