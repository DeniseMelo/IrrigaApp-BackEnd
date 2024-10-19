package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.RegraIrrigacao;
import br.com.fiap.IrrigaApp.model.Sensor;
import br.com.fiap.IrrigaApp.model.Valvula;
import br.com.fiap.IrrigaApp.repository.RegraIrrigacaoRepository;
import br.com.fiap.IrrigaApp.repository.SensorRepository;
import br.com.fiap.IrrigaApp.repository.ValvulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonitoramentoService {

    @Autowired
    private RegraIrrigacaoRepository regraRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ValvulaRepository valvulaRepository;

    // Executa a cada 1 minuto (60000 milissegundos)
    @Scheduled(fixedRate = 60000)
    public void verificarRegras() {
        List<RegraIrrigacao> regras = regraRepository.findAll();

        for (RegraIrrigacao regra : regras) {
            if (regra.getAtiva()) {
                Sensor sensor = sensorRepository.findById(regra.getSensorId()).orElse(null);
                if (sensor != null) {
                    boolean temperaturaAtende = true;
                    boolean umidadeAtende = true;

                    // Verifica a temperatura
                    if (regra.getTemperaturaLimite() != null && sensor.getTemperaturaAmbiente() != null) {
                        temperaturaAtende = sensor.getTemperaturaAmbiente() >= regra.getTemperaturaLimite();
                    }

                    // Verifica a umidade
                    if (regra.getUmidadeLimite() != null && sensor.getUmidadeSolo() != null) {
                        umidadeAtende = sensor.getUmidadeSolo() <= regra.getUmidadeLimite();
                    }

                    if (temperaturaAtende && umidadeAtende) {
                        // Ativa a válvula
                        Valvula valvula = valvulaRepository.findById(regra.getValvulaId()).orElse(null);
                        if (valvula != null && (valvula.getEstadoAtivo() == null || !valvula.getEstadoAtivo())) {
                            valvula.setEstadoAtivo(true);
                            valvulaRepository.save(valvula);
                            // Opcional: Registrar data/hora de ativação
                        }
                    }
                }
            }
        }
    }
}
