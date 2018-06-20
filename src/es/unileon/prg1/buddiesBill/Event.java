package es.unileon.prg1.buddiesBill;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * 
* @author Rosales Robles David
*/
public class Event {
	
	private static final Logger logger = LogManager.getLogger(BuddiesBill.class);
	
	String name;
	float balance;
	Buddies buddies;
	Movements movements;
	Payments payments;
	TextUI text = new TextUI();
	float pot;
	
	/**
     * Builder of the Event class
     * @param name of the event
     * @param max. number of Buddies
     * @param max. number of Movements
     */
	public Event(String name, int numBuddies, int numMovements, float potpool){
       this.name=name;
       buddies = new Buddies(numBuddies);
       movements = new Movements(numMovements);
       this.balance = 0f;
       payments = new Payments ((numBuddies-1)*numBuddies);
       this.pot = potpool;
    }
	
	public String getName() {
		return name;
	}


	public float getBalance() {
		return balance;
	}


	public Buddies getBuddies() {
		return buddies;
	}


	public Movements getMovements() {
		return movements;
	}
	
	public Payments getPayments() {
		
		return payments;
	}
	
	/*/**
     * Show the summary
     * Get information about the people, the movements and the balance of the event 
     * Send the information to the TextUI class
     */
	/*public void showSummary() {
		StringBuilder summary = new StringBuilder();
		Buddy[] buddy = buddies.getList();
		for (int i=0; i<buddy.length;i++) {
			balance=getBalance()+buddy[i].getMoney();
		}
		summary.append("Event: " + getName() + "\nPeople:\n" + buddies.toString() +
			"\nMovements:\n" + movements.toString() + "\nBalance: "+ getBalance());
		text.print(summary.toString());
	}*/

	/**
     *Calculate the payments
     *Create an auxiliary array to save the debts and balance it 
     */
	public void settleUp() {
		Buddy[] buddy = buddies.getList();
		float deudas[]= new float[buddy.length];
		float media;
		float totalpot = buddy.length * this.pot;
	
		for (int i=1; i<buddy.length;i++) {
			balance=getBalance()+buddy[i].getMoney();
		}
		
		media=(getBalance()-totalpot)/buddy.length-1;
		
		for (int i=1; i<buddy.length;i++) {
			deudas[i]=media-buddy[i].getMoney();
		}	
	
		for (int i=0; i<deudas.length;i++) {
			if(deudas[i]>0) {
				float tr=deudas[i];
				deudas[i]=0;
				for (int j=0; j<deudas.length;j++) {
					if (deudas[j]<0&&(-1*deudas[j])>=tr&&tr>0) {
						Payment payment = new Payment(buddy[i], buddy[j], tr);
						payments.add(payment);
						deudas[j]=deudas[j]+tr;
						tr=0;
					} else if (deudas[j]<0&&(-1*deudas[j])<tr&&tr>0) {
						tr=tr+deudas[j];
						Payment payment = new Payment(buddy[i], buddy[j], -1*deudas[j]);
						payments.add(payment);
						deudas[j]=0;
					}
				}
			}
		}
		buddy[0].setMoney(0.0f);
		logger.info(payments.toString());
		text.print(payments.toString());
	}


}