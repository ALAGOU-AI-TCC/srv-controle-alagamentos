package br.com.alagouai.srv.controle.alagamentos.adapter.output.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CondicaoClima {
    private int id;
    private String main;
    private String description;
    private String icon;
}
