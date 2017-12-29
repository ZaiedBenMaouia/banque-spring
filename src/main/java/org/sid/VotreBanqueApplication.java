package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VotreBanqueApplication implements CommandLineRunner {
 
	@Autowired
  	ClientRepository clientRepository;
	@Autowired
    CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	IBanqueMetier ib;
	
	public static void main(String[] args) {
	         
		
		           SpringApplication.run(VotreBanqueApplication.class, args);
	}

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		/*Client c1 = clientRepository.save(new Client("zaied","zaiedbenmaouia@yahoo.fr"));
		
		Compte cpte1 = compteRepository.save(new CompteCourant("C1", new Date(), 1000, c1, 100) );
		
		Compte cpte2 = compteRepository.save(new CompteEpargne("C2", new Date(), 500, c1,5.5) );
		
		Operation op1 = operationRepository.save(new Versement(new Date(),200, cpte1));
		System.out.println("*******************************************zaied");
		
		Compte c= ib.consulterCompte("C1");
		
		System.out.println(c.getSolde());
		
		System.out.println("*******************************************zaied");*/ 
	}
}
