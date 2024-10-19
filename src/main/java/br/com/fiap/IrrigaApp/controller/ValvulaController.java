package br.com.fiap.IrrigaApp.controller;

import br.com.fiap.IrrigaApp.model.Valvula;
import br.com.fiap.IrrigaApp.service.ValvulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/valvulas")
public class ValvulaController {

    @Autowired
    private ValvulaService valvulaService;

    // Obter Valvula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Valvula> getValvulaById(@PathVariable String id) {
        Valvula valvula = valvulaService.findById(id);
        if (valvula != null) {
            return ResponseEntity.ok(valvula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todas as Valvulas
    @GetMapping
    public ResponseEntity<List<Valvula>> getAllValvulas() {
        List<Valvula> valvulas = valvulaService.findAll();
        return ResponseEntity.ok(valvulas);
    }

    // Criar uma nova Valvula
    @PostMapping
    public ResponseEntity<Valvula> createValvula(@RequestBody Valvula valvula) {
        Valvula novaValvula = valvulaService.save(valvula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaValvula);
    }

    // Atualizar uma Valvula existente
    @PutMapping("/{id}")
    public ResponseEntity<Valvula> updateValvula(@PathVariable String id, @RequestBody Valvula valvulaAtualizada) {
        Valvula valvulaExistente = valvulaService.findById(id);
        if (valvulaExistente != null) {
            valvulaAtualizada.setId(id); // Garantir que o ID não mude
            Valvula valvulaSalva = valvulaService.save(valvulaAtualizada);
            return ResponseEntity.ok(valvulaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma Valvula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValvula(@PathVariable String id) {
        Valvula valvulaExistente = valvulaService.findById(id);
        if (valvulaExistente != null) {
            valvulaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obter o estado ativo da Valvula
    @GetMapping("/{id}/estado")
    public ResponseEntity<Boolean> getEstadoAtivo(@PathVariable String id) {
        Boolean estadoAtivo = valvulaService.getEstadoAtivo(id);
        if (estadoAtivo != null) {
            return ResponseEntity.ok(estadoAtivo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obter o volume de água liberado
    @GetMapping("/{id}/volume")
    public ResponseEntity<Double> getVolumeAguaLiberado(@PathVariable String id) {
        Double volume = valvulaService.getVolumeAguaLiberado(id);
        if (volume != null) {
            return ResponseEntity.ok(volume);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obter a localização da Valvula
    @GetMapping("/{id}/localizacao")
    public ResponseEntity<Map<String, Double>> getLocalizacao(@PathVariable String id) {
        Map<String, Double> localizacao = valvulaService.getLocalizacao(id);
        if (localizacao != null) {
            return ResponseEntity.ok(localizacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obter a duração da irrigação
    @GetMapping("/{id}/duracao")
    public ResponseEntity<Integer> getDuracaoIrrigacao(@PathVariable String id) {
        Integer duracao = valvulaService.getDuracaoIrrigacao(id);
        if (duracao != null) {
            return ResponseEntity.ok(duracao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar Valvulas Ativas
    @GetMapping("/ativas")
    public ResponseEntity<List<Valvula>> getValvulasAtivas() {
        List<Valvula> valvulasAtivas = valvulaService.findValvulasAtivas();
        return ResponseEntity.ok(valvulasAtivas);
    }

    // Listar Valvulas por Sensor ID
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<Valvula>> getValvulasBySensorId(@PathVariable String sensorId) {
        List<Valvula> valvulas = valvulaService.findValvulasBySensorId(sensorId);
        return ResponseEntity.ok(valvulas);
    }
}
