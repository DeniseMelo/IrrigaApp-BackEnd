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
        return (nivelBateria != null) ? ResponseEntity.ok(nivelBateria) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/localizacao")
    public ResponseEntity<Map<String, Double>> getLocalizacao(@PathVariable String id) {
        Map<String, Double> localizacao = sensorService.getLocalizacao(id);
        return (localizacao != null) ? ResponseEntity.ok(localizacao) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable String id) {
        Sensor sensor = sensorService.findById(id);
        return (sensor != null) ? ResponseEntity.ok(sensor) : ResponseEntity.notFound().build();
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

    @PostMapping("/{sensorId}/associar-usuario/{usuarioId}")
    public ResponseEntity<Sensor> associarSensorAoUsuario(@PathVariable String sensorId, @PathVariable String usuarioId) {
        Sensor sensorAssociado = sensorService.associarSensorAoUsuario(sensorId, usuarioId);
        return (sensorAssociado != null) ? ResponseEntity.ok(sensorAssociado) : ResponseEntity.notFound().build();
    }
}
