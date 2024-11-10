package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.Sensor;

import java.util.List;
import java.util.Map;

public interface SensorService {

    Sensor findById(String id);

    List<Sensor> findAll();

    Sensor save(Sensor sensor);

    void deleteById(String id);

    Double getNivelBateria(String id);

    Map<String, Double> getLocalizacao(String id);

    Sensor associarSensorAoUsuario(String sensorId, String usuarioId);
}

