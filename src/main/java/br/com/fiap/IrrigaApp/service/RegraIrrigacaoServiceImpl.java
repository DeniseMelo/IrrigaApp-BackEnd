package br.com.fiap.IrrigaApp.service;

import br.com.fiap.IrrigaApp.model.RegraIrrigacao;
import br.com.fiap.IrrigaApp.repository.RegraIrrigacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegraIrrigacaoServiceImpl implements RegraIrrigacaoService {

    @Autowired
    private RegraIrrigacaoRepository regraRepository;

    @Override
    public RegraIrrigacao findById(String id) {
        return regraRepository.findById(id).orElse(null);
    }

    @Override
    public List<RegraIrrigacao> findAll() {
        return regraRepository.findAll();
    }

    @Override
    public RegraIrrigacao save(RegraIrrigacao regra) {
        return regraRepository.save(regra);
    }

    @Override
    public void deleteById(String id) {
        regraRepository.deleteById(id);
    }
}
