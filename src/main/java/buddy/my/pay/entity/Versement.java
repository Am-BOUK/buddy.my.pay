package buddy.my.pay.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	public Versement() {
		super();
	}

	public Versement(Date dateOperation, double amount,String description, Compte compte) {
		super(dateOperation, amount,description, compte);
	}

	

}
