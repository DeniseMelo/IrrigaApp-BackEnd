package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.Valvula;
import br.com.fiap.IrrigaApp.repository.ValvulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.time.Duration;

@Service
public class ValvulaServiceImpl implements ValvulaService {

    @Autowired
    private ValvulaRepository valvulaRepository;

    @Override
    public Valvula findById(String id) {
        return valvulaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Valvula> findAll() {
        return valvulaRepository.findAll();
    }

    @Override
    public Valvula save(Valvula valvula) {
        // Calcular duração e volume de água liberado se aplicável
        if (valvula.getDataHoraInicio() != null && valvula.getDataHoraFim() != null) {
            long duracao = Duration.between(valvula.getDataHoraInicio(), valvula.getDataHoraFim()).toMinutes();
            valvula.setDuracaoMinutos((int) duracao);

            // Supondo uma taxa de fluxo fixa (ex.: 10 litros por minuto)
            double taxaFluxo = 10.0; // Litros por minuto
            double volumeAgua = taxaFluxo * duracao;
            valvula.setVolumeAguaLiberado(volumeAgua);
        }

        return valvulaRepository.save(valvula);
    }

    @Override
    public void deleteById(String id) {
        valvulaRepository.deleteById(id);
    }

    // Métodos adicionais

    @Override
    public Boolean getEstadoAtivo(String id) {
        Valvula valvula = findById(id);
        return (valvula != null) ? valvula.getEstadoAtivo() : null;
    }

    @Override
    public Double getVolumeAguaLiberado(String id) {
        Valvula valvula = findById(id);
        return (valvula != null) ? valvula.getVolumeAguaLiberado() : null;
    }

    @Override
    public Map<String, Double> getLocalizacao(String id) {
        Valvula valvula = findById(id);
        if (valvula != null) {
            Map<String, Double> localizacao = new HashMap<>();
            localizacao.put("latitude", valvula.getLatitude());
            localizacao.put("longitude", valvula.getLongitude());
            return localizacao;
        }
        return null;
    }

    @Override
    public Integer getDuracaoIrrigacao(String id) {
        Valvula valvula = findById(id);
        return (valvula != null) ? valvula.getDuracaoMinutos() : null;
    }

    @Override
    public List<Valvula> findValvulasAtivas() {
        return valvulaRepository.findAll().stream()
                .filter(Valvula::getEstadoAtivo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Valvula> findValvulasBySensorId(String sensorId) {
        return valvulaRepository.findAll().stream()
                .filter(valvula -> sensorId.equals(valvula.getSensorId()))
                .collect(Collectors.toList());
    }
}
