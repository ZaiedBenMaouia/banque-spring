package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BansqueController {
	
	@Autowired
	private IBanqueMetier iBanqueMetier;

	@RequestMapping("/comptes")
	public String index() {
		return "compte";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte,
			                @RequestParam(value="page",defaultValue="0")int page,
			                @RequestParam(value="size",defaultValue="6")int size) {
		
		          model.addAttribute("codeCompte", codeCompte );
		try {
			Compte cp = iBanqueMetier.consulterCompte(codeCompte);
			
			
			model.addAttribute("compte",cp);
			Page<Operation> pageOperation  = iBanqueMetier.listOperations(codeCompte,page,size); 
			model.addAttribute("listOperation",pageOperation.getContent());
			int [] pages = new int [pageOperation.getTotalPages()];
			model.addAttribute("pages",pages);
 		} catch (Exception e) {
			model.addAttribute("error",e);
		}
		return "compte";
	}
	
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model ,String codeCompte,String codeCompte2,long montant,String typeOperation){
		
		try {
			if(typeOperation.equals("VERS")) {
				iBanqueMetier.verser(codeCompte, montant);
			} else if(typeOperation.equals("RET")){
				iBanqueMetier.retirer(codeCompte, montant);
			} else if(typeOperation.equals("VIR"))
				iBanqueMetier.virement(codeCompte, codeCompte2, montant);
		} catch (Exception e) {
			model.addAttribute("exception",e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
		
	}
	
	
}
