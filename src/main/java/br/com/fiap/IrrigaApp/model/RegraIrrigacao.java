package br.com.fiap.IrrigaApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "configuracoes_irrigacao")
public class RegraIrrigacao {
    @Id
    private String id;
    private String sensorId;
    private String valvulaId;
    private Double temperaturaLimite;
    private Double umidadeLimite;
    private Boolean ativa;
}