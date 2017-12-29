package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	
      public Compte consulterCompte(String codeCpte); 
      public void verser(String codeCpte, long montant);
      public void retirer(String codeCpte, long montant);
      public void virement(String codeCpte1, String cpte2, long montant);
      public Page<Operation> listOperations(String codeCpte, int page, int size);

}
