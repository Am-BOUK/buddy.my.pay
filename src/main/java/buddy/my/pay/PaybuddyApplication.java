package buddy.my.pay;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import buddy.my.pay.dal.ClientRepository;
import buddy.my.pay.dal.CompteRepository;
import buddy.my.pay.dal.OperationRepository;
import buddy.my.pay.entity.Client;
import buddy.my.pay.entity.Compte;
import buddy.my.pay.entity.Retrait;
import buddy.my.pay.entity.Versement;
import buddy.my.pay.servce.IPayMyBuddyMetier;

@SpringBootApplication
public class PaybuddyApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IPayMyBuddyMetier payMyBuddyMetier;

	public static void main(String[] args) {
		SpringApplication.run(PaybuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Client client1 = clientRepository.save(new Client("Hassan", "hassan@gmail.com","admin123"));
//		Client client2 = clientRepository.save(new Client("Rachid", "rachid@gmail.com","admin123"));
//		Client client3 = clientRepository.save(new Client("Ali", "ali@gmail.com","admin123"));
//
//		Compte compte1 = compteRepository.save(new Compte("c1", new Date(), 3000, client1));
//		Compte compte2 = compteRepository.save(new Compte("c2", new Date(), 6000, client2));
//		Compte compte3 = compteRepository.save(new Compte("c3", new Date(), 9000, client3));
//
//
//		operationRepository.save(new Versement(new Date(), 9000,"Versement sur mon compte", compte1));
//		operationRepository.save(new Versement(new Date(), 6000,"Versement sur mon compte", compte1));
//		operationRepository.save(new Versement(new Date(), 2300,"Versement sur mon compte", compte1));
//		operationRepository.save(new Retrait(new Date(), 9000,"Retrait de mon compte", compte1));
//
//		operationRepository.save(new Versement(new Date(), 2300,"Versement sur mon compte", compte2));
//		operationRepository.save(new Versement(new Date(), 400,"Versement sur mon compte", compte2));
//		operationRepository.save(new Versement(new Date(), 2300,"Versement sur mon compte", compte2));
//		operationRepository.save(new Retrait(new Date(), 3000,"Retrait de mon compte", compte2));
//		
//		
//		payMyBuddyMetier.verser("c3", 300,"versement sur mon compte");
//		payMyBuddyMetier.virement("c1", "c2", 1000,"Trip money");
	}
		
	
}
