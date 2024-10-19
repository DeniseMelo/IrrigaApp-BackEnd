package br.com.fiap.IrrigaApp.repository;

import br.com.fiap.IrrigaApp.model.Valvula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValvulaRepository extends MongoRepository<Valvula, String> {

}
