package br.com.alagouai.srv.controle.alagamentos.config;


import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

@Component
public class GeometryFac {
    private final GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
    public GeometryFactory get() { return factory; }
}
