package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.Sensor;
import br.com.fiap.IrrigaApp.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensorServiceImpl implements SensorService{

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public Sensor findById(String id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteById(String id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public Double getNivelBateria(String id) {
        Sensor sensor = findById(id);
        return (sensor != null) ? sensor.getNivelBateria() : null;
    }

    @Override
    public Map<String, Double> getDadosAmbientais(String id) {
        Sensor sensor = findById(id);
        if (sensor != null) {
            Map<String, Double> dadosAmbientais = new HashMap<>();
            dadosAmbientais.put("temperatura", sensor.getTemperaturaAmbiente());
            dadosAmbientais.put("umidade", sensor.getUmidadeSolo());
            return dadosAmbientais;
        }
        return null;
    }

    @Override
    public Boolean getStatusAtivo(String id) {
        Sensor sensor = findById(id);
        return (sensor != null) ? sensor.getAtivo() : null;
    }

    @Override
    public Map<String, Double> getLocalizacao(String id) {
        Sensor sensor = findById(id);
        if (sensor != null) {
            Map<String, Double> localizacao = new HashMap<>();
            localizacao.put("latitude", sensor.getLatitude());
            localizacao.put("longitude", sensor.getLongitude());
            return localizacao;
        }
        return null;
    }
}
