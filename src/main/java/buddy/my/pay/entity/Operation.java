package buddy.my.pay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Operation implements Serializable {
	@Id
	@GeneratedValue
	@Column(length = 10)
	private Long operationId;
	@NotNull(message = "date of operation is mandatory!")
	private Date dateOperation;
	@NotNull(message = "amount is mandatory!")
	@Column(length = 25)
	private double amount;
	@NotNull(message = "description is mandatory!")
	@Column(length = 225)
	private String description;
	@NotNull(message = "CODE_COMPTE is mandatory!")
	@ManyToOne
	@JoinColumn(name = "CODE_COMPTE")
	private Compte compte;

	public Operation() {
		super();
	}

//	public Operation(Date dateOperation, double amount, Compte compte) {
//		super();
//		this.dateOperation = dateOperation;
//		this.amount = amount;
//		this.compte = compte;
//	}

	public Operation(Date dateOperation, double amount, String description, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.amount = amount;
		this.description = description;
		this.compte = compte;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
