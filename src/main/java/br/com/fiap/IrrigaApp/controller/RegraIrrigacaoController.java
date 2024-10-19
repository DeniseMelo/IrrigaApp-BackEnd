package br.com.fiap.IrrigaApp.controller;

import br.com.fiap.IrrigaApp.model.RegraIrrigacao;
import br.com.fiap.IrrigaApp.service.RegraIrrigacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/regrasIrrigacao")
public class RegraIrrigacaoController {

    @Autowired
    private RegraIrrigacaoService regraService;

    @GetMapping("/{id}")
    public ResponseEntity<RegraIrrigacao> getById(@PathVariable String id) {
        RegraIrrigacao regra = regraService.findById(id);
        if (regra != null) {
            return ResponseEntity.ok(regra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RegraIrrigacao>> getAll() {
        List<RegraIrrigacao> regras = regraService.findAll();
        return ResponseEntity.ok(regras);
    }

    @PostMapping
    public ResponseEntity<RegraIrrigacao> create(@RequestBody RegraIrrigacao regra) {
        RegraIrrigacao novaRegra = regraService.save(regra);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRegra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegraIrrigacao> update(@PathVariable String id, @RequestBody RegraIrrigacao regraAtualizada) {
        RegraIrrigacao existente = regraService.findById(id);
        if (existente != null) {
            regraAtualizada.setId(id);
            RegraIrrigacao salva = regraService.save(regraAtualizada);
            return ResponseEntity.ok(salva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        RegraIrrigacao existente = regraService.findById(id);
        if (existente != null) {
            regraService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
