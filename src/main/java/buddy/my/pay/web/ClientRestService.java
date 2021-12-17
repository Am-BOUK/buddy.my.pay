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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import buddy.my.pay.dal.ClientRepository;
import buddy.my.pay.entity.Client;



/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@RestController
public class ClientRestService {
	private static final Logger logger = LogManager.getLogger("ClientController");
	/**
	 * implementation of client business processing
	 * 
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * Read - Get all clients
	 * 
	 * @return list of clients
	 */
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public List<Client> getAllClients() {
		logger.info("Getting All Clients");
		return clientRepository.findAll();
	}

	/**
	 * Read-Get one client by email
	 * 
	 * @param email
	 * @return a client object full filled
	 */
	@RequestMapping(value = "/client/{email}", method = RequestMethod.GET)
	public Optional<Client> getClientByEmail(@PathVariable String email) {
		logger.info("Getting client by email");
		return clientRepository.findById(email);
	}

	/**
	 * Read-Get list clients by name
	 * 
	 * @param name
	 * @return a list of clients object full filled per page
	 */
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public Page<Client> findClientByName(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		logger.info("getting list of clients by name per page");
		return clientRepository.findClientByName("%" + name + "%", PageRequest.of(page, size));
	}

	/**
	 * Create - Add a new client
	 * 
	 * @param client a client object
	 * @return The client object added
	 */
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public Client save(@Valid @RequestBody Client client) {
		return clientRepository.save(client);
	}

	/**
	 * Update - update an existing client
	 * 
	 * @param email  the email of the client to update
	 * @param client the client object updated
	 * @return client the client object updated
	 */
	@RequestMapping(value = "/client/{email}", method = RequestMethod.PUT)
	public Client save(@PathVariable String email, @RequestBody Client client) {
		client.setEmail(email);
		return clientRepository.save(client);
	}

	/**
	 * Delete - delete a client
	 * 
	 * @param email the email of the client to delete
	 */
	@RequestMapping(value = "/client/{email}", method = RequestMethod.DELETE)
	public boolean deleteByEmail(@PathVariable String email) {
		clientRepository.deleteById(email);
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
