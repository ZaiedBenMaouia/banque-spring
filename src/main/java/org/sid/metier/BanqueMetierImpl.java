package org.sid.metier;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service    //pour que spring puisse instatcier la classe au demarrage
@Transactional
public class  BanqueMetierImpl implements IBanqueMetier {

	
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	 
	public Compte consulterCompte(String codeCpte) {
		Compte cpte = compteRepository.findOne(codeCpte);
	     if(cpte == null) throw new RuntimeException("compte introuvable");
	     return cpte;
	}

	public void verser(String codeCpte, long montant) {
		
		Compte cpte = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cpte);
		operationRepository.save(v);
		cpte.setSolde(cpte.getSolde()+montant);
		compteRepository.save(cpte);
		
	}

	public void retirer(String codeCpte, long montant) {
		Compte cpte = consulterCompte(codeCpte);
		
		long faciliteCaisse= 0;
		  
		  if(cpte instanceof CompteCourant)
			faciliteCaisse = (long) ((CompteCourant) cpte).getDecouvert();
		  
		  if(cpte.getSolde() + faciliteCaisse < montant) throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cpte);
		operationRepository.save(r);
		cpte.setSolde(cpte.getSolde()-montant);
		compteRepository.save(cpte);
		
	}
     
	public void virement(String codeCpte1, String codeCpte2, long montant) {
		 
		if(codeCpte1.equals(codeCpte2)) throw new RuntimeException("Impossible virement sur le meme compte");
		retirer(codeCpte1, montant);
		  verser(codeCpte2, montant);
		
	}

	public Page<Operation> listOperations(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.ListOperations(codeCpte, new PageRequest(page, size));
	}

	

}
