// Equipe: Vanessa Ellen Paixão, Aislan Silva, Icaro Dantas e Kaique Conceição

package br.com.api.veiculos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
   name = "veiculos"
)
@Getter
@Setter

public class VeiculoModelo {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long codigo;
   private String marca;
   private String modelo;
   private int ano;

}

