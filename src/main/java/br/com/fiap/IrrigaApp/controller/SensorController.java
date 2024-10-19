package br.com.fiap.IrrigaApp.controller;

import br.com.fiap.IrrigaApp.model.Sensor;
import br.com.fiap.IrrigaApp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;


    @GetMapping("/{id}/bateria")
    public ResponseEntity<Double> getStatusBateria(@PathVariable String id) {
        Double nivelBateria = sensorService.getNivelBateria(id);
        if (nivelBateria != null) {
            return ResponseEntity.ok(nivelBateria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/dados-ambientais")
    public ResponseEntity<Map<String, Double>> getTemperaturaEUmidade(@PathVariable String id) {
        Map<String, Double> dadosAmbientais = sensorService.getDadosAmbientais(id);
        if (dadosAmbientais != null) {
            return ResponseEntity.ok(dadosAmbientais);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/status")
    public ResponseEntity<Boolean> getStatusAtivo(@PathVariable String id) {
        Boolean statusAtivo = sensorService.getStatusAtivo(id);
        if (statusAtivo != null) {
            return ResponseEntity.ok(statusAtivo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/localizacao")
    public ResponseEntity<Map<String, Double>> getLocalizacao(@PathVariable String id) {
        Map<String, Double> localizacao = sensorService.getLocalizacao(id);
        if (localizacao != null) {
            return ResponseEntity.ok(localizacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable String id) {
        Sensor sensor = sensorService.findById(id);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensores() {
        List<Sensor> sensores = sensorService.findAll();
        return ResponseEntity.ok(sensores);
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        Sensor novoSensor = sensorService.save(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable String id, @RequestBody Sensor sensorAtualizado) {
        Sensor sensorExistente = sensorService.findById(id);
        if (sensorExistente != null) {
            sensorAtualizado.setId(id); // Garantir que o ID não mude
            Sensor sensorSalvo = sensorService.save(sensorAtualizado);
            return ResponseEntity.ok(sensorSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        Sensor sensorExistente = sensorService.findById(id);
        if (sensorExistente != null) {
            sensorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
