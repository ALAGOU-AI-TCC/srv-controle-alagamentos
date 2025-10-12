package br.com.alagouai.srv.controle.alagamentos.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {

    public static String formatadorDataUnix(String data){
        LocalDateTime localDateTime = LocalDateTime.parse(data, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("America/Sao_Paulo"));

        return String.valueOf(zonedDateTime.toEpochSecond());
    }

    public static String subtrairHorasUnix(String unixTimestamp, int horas) {
        long timestamp = Long.parseLong(unixTimestamp);

        ZonedDateTime zonedDateTime = Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.of("America/Sao_Paulo"));

        ZonedDateTime atualizado = zonedDateTime.minusHours(horas);

        return String.valueOf(atualizado.toEpochSecond());
    }

}
