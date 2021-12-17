package buddy.my.pay.servce;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import buddy.my.pay.dal.CompteRepository;
import buddy.my.pay.dal.OperationRepository;
import buddy.my.pay.entity.Compte;
import buddy.my.pay.entity.Operation;
import buddy.my.pay.entity.Retrait;
import buddy.my.pay.entity.Versement;


@Service
@Transactional
public class PayMyBuddyMetrierImpl implements IPayMyBuddyMetier {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte cp = compteRepository.findById(codeCompte).orElse(null);
		if (cp == null) {
			throw new RuntimeException("Compte introuvable");
		}
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant, String description) {
		Compte cp = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant,description, cp);
		operationRepository.save(v);
		cp.setAmount(cp.getAmount() + montant);
		compteRepository.save(cp);

	}

	@Override
	public void retirer(String codeCompte, double montant, String description) throws Exception {
		Compte cp = consulterCompte(codeCompte);
		if (cp.getAmount() < montant) {
			throw new Exception("Solde insuffisant!");
		}
		Retrait r = new Retrait(new Date(), montant,description, cp);
		operationRepository.save(r);
		cp.setAmount(cp.getAmount() - montant);
		compteRepository.save(cp);

	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant, String description) throws Exception {
		retirer(codeCompte1, montant,description);
		verser(codeCompte2, montant,description);

	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		return operationRepository.listOperation(codeCompte, PageRequest.of(page, size));

	}

}
