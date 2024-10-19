package br.com.fiap.IrrigaApp.repository;

import br.com.fiap.IrrigaApp.model.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String> {

}
