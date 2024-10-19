package br.com.fiap.IrrigaApp.repository;

import br.com.fiap.IrrigaApp.model.RegraIrrigacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraIrrigacaoRepository extends MongoRepository<RegraIrrigacao, String> {
}
