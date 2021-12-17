package buddy.my.pay.web;
/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */

import java.util.Date;
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

import buddy.my.pay.dal.OperationRepository;
import buddy.my.pay.entity.Operation;
import buddy.my.pay.entity.Retrait;
import buddy.my.pay.entity.Versement;
import buddy.my.pay.servce.IPayMyBuddyMetier;



@RestController
public class OperationRestService {
	private static final Logger logger = LogManager.getLogger("OperationRestService");
	/**
	 * implementation of client business processing
	 * 
	 */
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IPayMyBuddyMetier payMyBuddyMetier;
	
	/**
	 * Read - Get all operations
	 * 
	 * @return list of operations
	 */
	@RequestMapping(value = "/operations", method = RequestMethod.GET)
	public List<Operation> getAllOperations() {
		logger.info("Getting All operations");
		return operationRepository.findAll();
	}
	
	/**
	 * Read-Get one operation by operation Id
	 * 
	 * @param operationId
	 * @return a operation object full filled
	 */
	@RequestMapping(value = "/operation/{operationId}", method = RequestMethod.GET)
	public Optional<Operation> getClientByCodeCompte(@PathVariable Long operationId) {
		logger.info("Getting client by email");
		return operationRepository.findById(operationId);
	}
	
	/**
	 * Read-Get list operations by codeCompte
	 * 
	 * @param email
	 * @return a list of operations object full filled per page
	 */
	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	public Page<Operation> findOperationsByCodeCompte(@RequestParam(name = "codeCompte", defaultValue = "") String codeCompte,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		logger.info("getting list of clients by name per page");
		return operationRepository.listOperation("%" + codeCompte + "%", PageRequest.of(page, size));
	}
	
//	/**
//	 * Read-Get list operations by email client
//	 * 
//	 * @param email
//	 * @return a list of operations object full filled per page
//	 */
//	@RequestMapping(value = "/operation/client", method = RequestMethod.GET)
//	public Page<Operation> findOperationsByEmail(@RequestParam(name = "email", defaultValue = "") String email,
//			@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "size", defaultValue = "5") int size) {
//		logger.info("getting list of clients by name per page");
//		return operationRepository.listOperationByEmail("%" + email + "%", PageRequest.of(page, size));
//	}
	
	/************************************************/
	/**
	 * Create - Add a new versement
	 * 
	 * @param operation 
	 * @return operation object added
	 */
	@RequestMapping(value = "/operation/versement", method = RequestMethod.POST)
	public Operation saveVersement(@Valid @RequestBody Operation operation) {
		Versement v = new Versement();
		v.setDateOperation(operation.getDateOperation());
		v.setAmount(operation.getAmount());
		v.setCompte(operation.getCompte());
		v.setDescription(operation.getDescription());
				
		return operationRepository.save(v);
	}
	
	/************************************************/
	/**
	 * Create - Add a new retrait
	 * 
	 * @param operation 
	 * @return operation object added
	 */
	@RequestMapping(value = "/operation/retrait", method = RequestMethod.POST)
	public Operation saveRetrait(@Valid @RequestBody Operation operation) {
		Retrait r = new Retrait();
		r.setDateOperation(operation.getDateOperation());
		r.setAmount(operation.getAmount());
		r.setCompte(operation.getCompte());
		r.setDescription(operation.getDescription());
				
		return operationRepository.save(r);
	}
	/**
	 * Create - Add a new operation
	 * 
	 * @param operation 
	 * @return operation object added
	 */
//	@RequestMapping(value = "/operation", method = RequestMethod.POST)
//	public Operation saveVersement(@Valid @RequestBody Operation operation) {
//		operation.set
//		return operationRepository.save(operation);
//	}
	/*************************************************/
	/**
	 * Update - update an existing operation
	 * 
	 * @param email  the email of the client to update
	 * @param client the client object updated
	 * @return client the client object updated
	 */
	@RequestMapping(value = "/operation/{operationId}", method = RequestMethod.PUT)
	public Operation save(@PathVariable Long operationId, @RequestBody Operation operation) {
		operation.setOperationId(operationId);
		return operationRepository.save(operation);
	}
	
	/**
	 * Delete - delete a operation
	 * 
	 * @param operationId the operationId of the operation to delete
	 */
	@RequestMapping(value = "/operation/{operationId}", method = RequestMethod.DELETE)
	public boolean deleteOperationById(@PathVariable Long operationId) {
		operationRepository.deleteById(operationId);
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
		logger.info("the specified Client object is invalid : " + errors);
		return errors;
	}
}
