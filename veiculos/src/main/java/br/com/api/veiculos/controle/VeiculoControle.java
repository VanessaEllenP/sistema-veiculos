// Equipe: Vanessa Ellen Paixão, Aislan Silva, Icaro Dantas e Kaique Conceição

package br.com.api.veiculos.controle;

import br.com.api.veiculos.modelo.RespostaModelo;
import br.com.api.veiculos.modelo.VeiculoModelo;
import br.com.api.veiculos.servico.VeiculoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
   origins = {"*"}
)
public class VeiculoControle {
   @Autowired
   private VeiculoServico vs;

   public VeiculoControle() {
   }

   @GetMapping({""})
   public String rota() {
      return "api funcionando";
   }

   // Rota para listar
   @GetMapping({"/listar"})
   public Iterable<VeiculoModelo> listar() {
      return this.vs.listar();
   }

   // Rota para cadastrar
   @PostMapping({"/cadastrar"})
   public ResponseEntity<?> cadastrar(@RequestBody VeiculoModelo vm) {
      return this.vs.cadastrarAlterar(vm, "cadastrar");
   }

   // Rota para alterar
   @PutMapping({"/alterar"})
   public ResponseEntity<?> alterar(@RequestBody VeiculoModelo vm) {
      return this.vs.cadastrarAlterar(vm, "alterar");
   }

   // Rota para remover
   @DeleteMapping({"/remover/{codigo}"})
   public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
      return this.vs.remover(codigo);
   }
}
