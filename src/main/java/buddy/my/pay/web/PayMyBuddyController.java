package buddy.my.pay.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import buddy.my.pay.servce.IPayMyBuddyMetier;


@Controller
public class PayMyBuddyController {
	@Autowired
	private IPayMyBuddyMetier payMyBuddyMetier;
	
	@RequestMapping("/home/operations")
	public String index() {
		return "comptes";
	}
	

}
