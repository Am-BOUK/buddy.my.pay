package buddy.my.pay.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import buddy.my.pay.dal.CompteRepository;
import buddy.my.pay.entity.Compte;


/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@RestController
public class CompteRestService {
	private static final Logger logger = LogManager.getLogger("CompteRestService");

	/**
	 * implementation of compte business processing
	 * 
	 */
	@Autowired
	private CompteRepository compteRepository;

	/**
	 * Read - Get all comptes
	 * 
	 * @return list of comptes
	 */
	@RequestMapping(value = "/comptes", method = RequestMethod.GET)
	public List<Compte> getAllComptes() {
		logger.info("Getting All Comptes");
		return compteRepository.findAll();
	}

	/**
	 * Read-Get one client by email
	 * 
	 * @param email
	 * @return a client object full filled
	 */
	@RequestMapping(value = "/compte/{codeCompte}", method = RequestMethod.GET)
	public Optional<Compte> getClientByCodeCompte(@PathVariable String codeCompte) {
		logger.info("Getting client by email");
		return compteRepository.findById(codeCompte);
	}
	/**
	 * Read-Get list comptes by email
	 * 
	 * @param email
	 * @return a list of comptes object full filled per page
	 */
	@RequestMapping(value = "/compte", method = RequestMethod.GET)
	public Page<Compte> findComptesByEmail(@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		logger.info("getting list of clients by name per page");
		return compteRepository.findComptesByEmail("%" + email + "%", PageRequest.of(page, size));
	}
	
	/**
	 * Create - Add a new compte
	 * 
	 * @param compte object
	 * @return The compte object added
	 */
	@RequestMapping(value = "/compte", method = RequestMethod.POST)
	public Compte save(@Valid @RequestBody Compte compte) {
		return compteRepository.save(compte);
	}
	
	/**
	 * Update - update an existing compte
	 * 
	 * @param email  the email of the client to update
	 * @param client the client object updated
	 * @return client the client object updated
	 */
	@RequestMapping(value = "/compte/{codeCompte}", method = RequestMethod.PUT)
	public Compte save(@PathVariable String codeCompte, @RequestBody Compte compte) {
		compte.setCodeCompte(codeCompte);
		return compteRepository.save(compte);
	}
	
	/**
	 * Delete - delete a compte
	 * 
	 * @param codeCompte the codeCompte of the compte to delete
	 */
	@RequestMapping(value = "/compte/{codeCompte}", method = RequestMethod.DELETE)
	public boolean deleteByEmail(@PathVariable String codeCompte) {
		compteRepository.deleteById(codeCompte);
		return true;
	}
	/**
	 * Handle specified types of exceptions ** Processing the validation errors:
	 * 
	 * @param ex argument of method not valid
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		logger.info("the specified Compte object is invalid : " + errors);
		return errors;
	}
}
