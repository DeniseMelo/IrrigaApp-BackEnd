package br.com.fiap.IrrigaApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitura {

    private Double umidadeSolo;
    private Double nivelBateria;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}

