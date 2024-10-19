package br.com.fiap.IrrigaApp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "valvulas")
public class Valvula {
    private String id;
    private String nomeValvula;
    private String sensorId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Integer duracaoMinutos;
    private Boolean estadoAtivo;
    private Double volumeAguaLiberado;
    private Double latitude;
    private Double longitude;
}

