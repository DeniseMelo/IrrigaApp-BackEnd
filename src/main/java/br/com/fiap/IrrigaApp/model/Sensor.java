package br.com.fiap.IrrigaApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sensores")
public class Sensor {

    @Id
    private String id;
    private String nomeSensor;
    private String usuarioId;

    // Lista para armazenar leituras (inclui todos os dados variáveis)
    private List<Leitura> leituras = new ArrayList<>();

    public void adicionarLeitura(Leitura leitura) {
        this.leituras.add(leitura);
    }
}
