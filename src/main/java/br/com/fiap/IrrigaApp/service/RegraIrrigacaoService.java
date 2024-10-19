package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.RegraIrrigacao;
import java.util.List;

public interface RegraIrrigacaoService {
    RegraIrrigacao findById(String id);
    List<RegraIrrigacao> findAll();
    RegraIrrigacao save(RegraIrrigacao regra);
    void deleteById(String id);
}
