package buddy.my.pay.servce;

import org.springframework.data.domain.Page;

import buddy.my.pay.entity.Compte;
import buddy.my.pay.entity.Operation;



public interface IPayMyBuddyMetier {
	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCompte, double montant, String descirtion);
	public void retirer(String codeCompte, double montant, String descirtion);
	public void virement(String codeCompte1, String codeCompte2, double montant, String description);
	public Page<Operation> listOperation(String codeCompte, int page, int size);

}
