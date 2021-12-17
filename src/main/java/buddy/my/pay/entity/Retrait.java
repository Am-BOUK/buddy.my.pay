package buddy.my.pay.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double amount, String description, Compte compte) {
		super(dateOperation, amount, description, compte);
		// TODO Auto-generated constructor stub
	}

	
	

}
