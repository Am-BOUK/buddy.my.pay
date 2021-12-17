package buddy.my.pay.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Client implements Serializable {
	@NotNull(message = "name is mandatory!")
	@Column(length = 25)
	private String name;
	@Id
	@Email
	@NotNull(message = "email is mandatory!")
	@Column(length = 25)
	private String email;
	@NotNull(message = "password is mandatory!")
	@Column(length = 25)
	private String password;
	@OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Compte compte;


	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Client() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Client(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
