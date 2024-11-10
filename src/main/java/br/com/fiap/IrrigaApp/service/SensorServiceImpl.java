package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.Sensor;
import br.com.fiap.IrrigaApp.model.Leitura;
import br.com.fiap.IrrigaApp.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensorServiceImpl implements SensorService {

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
        if (sensor != null && !sensor.getLeituras().isEmpty()) {
            return sensor.getLeituras().get(sensor.getLeituras().size() - 1).getNivelBateria();
        }
        return null;
    }

    @Override
    public Map<String, Double> getLocalizacao(String id) {
        Sensor sensor = findById(id);
        if (sensor != null && !sensor.getLeituras().isEmpty()) {
            Leitura ultimaLeitura = sensor.getLeituras().get(sensor.getLeituras().size() - 1);
            Map<String, Double> localizacao = new HashMap<>();
            localizacao.put("latitude", ultimaLeitura.getLatitude());
            localizacao.put("longitude", ultimaLeitura.getLongitude());
            return localizacao;
        }
        return null;
    }

    @Override
    public Sensor associarSensorAoUsuario(String sensorId, String usuarioId) {
        Sensor sensor = findById(sensorId);
        if (sensor != null && sensor.getUsuarioId() == null) {
            sensor.setUsuarioId(usuarioId);
            return sensorRepository.save(sensor);
        }
        return null;
    }

    @Override
    public Sensor adicionarLeitura(String sensorId, Leitura leitura) {
        Sensor sensor = findById(sensorId);
        if (sensor != null) {
            sensor.adicionarLeitura(leitura);
            return sensorRepository.save(sensor);
        }
        return null;
    }
}
