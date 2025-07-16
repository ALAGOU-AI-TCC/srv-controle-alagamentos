package br.com.alagouai.srv.controle.alagamentos.adapter.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenWeatherResponse {

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

    @JsonProperty("timezone")
    private String fusoHorario;

    @JsonProperty("timezone_offset")
    private int offsetFusoHorario;
    private List<WeatherData> data;
}
