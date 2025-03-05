// Equipe: Vanessa Ellen Paixão, Aislan Silva, Icaro Dantas e Kaique Conceição

package br.com.api.veiculos.servico;

import br.com.api.veiculos.modelo.RespostaModelo;
import br.com.api.veiculos.modelo.VeiculoModelo;
import br.com.api.veiculos.repositorio.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoServico {
   @Autowired
   private VeiculoRepositorio vr;
   @Autowired
   private RespostaModelo rm;

   // Método para listar os veículos
   public Iterable<VeiculoModelo> listar() {
      return this.vr.findAll();
   }

   // Método para cadastrar e alterar os veículos
   public ResponseEntity<?> cadastrarAlterar(VeiculoModelo vm, String acao) {
      if (vm.getMarca().equals("")) {
         this.rm.setMensagem("A marca é obrigatória");
         return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
      } else if (vm.getModelo().equals("")) {
         this.rm.setMensagem("O modelo é obrigatório");
         return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
      } else if (vm.getAno() <= 0) {
         this.rm.setMensagem("O ano é obrigatório e deve ser um valor válido");
         return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
      } else {
         if (acao.equals("cadastrar")) {
            return new ResponseEntity<VeiculoModelo>(vr.save(vm), HttpStatus.CREATED);
         }else{
            return new ResponseEntity<>(vr.save(vm), HttpStatus.OK);
         }
      }
   }

   // Método para remover veículos
   public ResponseEntity<RespostaModelo> remover(long codigo) {
      vr.deleteById(codigo);

      rm.setMensagem("O veículo foi removido com sucesso.");
      return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
   }
}
