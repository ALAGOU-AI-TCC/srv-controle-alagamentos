package br.com.alagouai.srv.controle.alagamentos.adapter.output.feign;


import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherClient", url = "${openweathermap.url}")
public interface OpenWeatherClient {

    @GetMapping("/data/3.0/onecall/timemachine")
    OpenWeatherResponse getWeatherData(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("dt") long dt,
            @RequestParam("appid") @Value("${openweathermap.key}") String appId,
            @RequestParam("units") @Value("${openweathermap.units}") String units,
            @RequestParam("lang") @Value("${openweathermap.lang}") String lang
    );
}
