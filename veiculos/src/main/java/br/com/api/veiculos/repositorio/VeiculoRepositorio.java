// Equipe: Vanessa Ellen Paixão, Aislan Silva, Icaro Dantas e Kaique Conceição

package br.com.api.veiculos.repositorio;

import br.com.api.veiculos.modelo.VeiculoModelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepositorio extends CrudRepository<VeiculoModelo, Long> {
    
}
