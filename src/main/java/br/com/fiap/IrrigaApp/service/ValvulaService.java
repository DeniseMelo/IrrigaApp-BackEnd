package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.Valvula;

import java.util.List;
import java.util.Map;

public interface ValvulaService {

    Valvula findById(String id);

    List<Valvula> findAll();

    Valvula save(Valvula valvula);

    void deleteById(String id);

    // Métodos adicionais além do CRUD básico
    Boolean getEstadoAtivo(String id);

    Double getVolumeAguaLiberado(String id);

    Map<String, Double> getLocalizacao(String id);

    Integer getDuracaoIrrigacao(String id);

    List<Valvula> findValvulasAtivas();

    List<Valvula> findValvulasBySensorId(String sensorId);
}

