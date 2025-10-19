package br.com.alagouai.srv.controle.alagamentos.adapter.input.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PrevisaoRequest {

    @NotBlank(message = "latitude é obrigatória")
    @Pattern(
            regexp = "^-?\\d{1,2}(\\.\\d+)?$|^-?90(\\.0+)?$",
            message = "latitude deve ser numérica entre -90 e 90"
    )
    private String latitude;

    @NotBlank(message = "longitude é obrigatória")
    @Pattern(
            regexp = "^-?\\d{1,3}(\\.\\d+)?$|^-?180(\\.0+)?$",
            message = "longitude deve ser numérica entre -180 e 180"
    )
    private String longitude;

    @NotBlank(message = "data é obrigatório (formato yyyy-MM-dd HH:mm:ss)")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$",
            message = "data deve seguir o formato yyyy-MM-dd HH:mm:ss (ex: 2023-11-28 20:46:00)"
    )
    private String time;

    public PrevisaoRequest() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
