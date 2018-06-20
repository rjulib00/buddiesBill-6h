package es.unileon.prg1.buddiesBill;
/**
 * 
* @author Rosales Robles David
*/
public class Payment {
	
	Buddy buddySender;
	Buddy buddyReceiver;
	float money;
	
	/**
     * Builder of the Payment class
     * @param buddy who sender the money
     * @param buddy who receiver the money
     * @param amount to pay 
     */
	public Payment(Buddy sender, Buddy receiver, float money){
		this.buddySender=sender;
		this.buddyReceiver=receiver;
		this.money=money;
	}
	
	Buddy getSender() {
		return buddySender;
	}
	Buddy getReceiver() {
		return buddyReceiver;
	}
	
	float getMoney() {
		return money;
	}
	
	 @Override
	public String toString() {
		return "\nPayment: " +this.getMoney()+" from "+this.getSender().getName()+" to "+this.getReceiver().getName();
	}
	
}
