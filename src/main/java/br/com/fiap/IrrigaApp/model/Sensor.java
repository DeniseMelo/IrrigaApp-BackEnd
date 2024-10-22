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
@Document(collection = "sensores")
public class Sensor {

    @Id
    private String id;
    private String nomeSensor;
    private Double umidadeSolo;
    private Double temperaturaAmbiente;
    private Double nivelBateria;
    private Boolean ativo;
    private Double latitude;
    private Double longitude;

}
//adicionando um comentario  para testar a workflow novamente