package es.unileon.prg1.buddiesBill;
/**
 * 
* @author Rosales Robles David
*/
public class Payments {

	int _next;//point to the first null
	Payment[] list;
	
	/**
     * Builder of the Payments class
     * @param size 
     */
	public Payments(int size) {
		this._next=0;
		this.list= new Payment[size];
	}
	
	/**
     * Add payment at the first position available in the array
     * @param payment to add
     * @return if the payment is added 
     */
	boolean add(Payment payment) {
		boolean ifAdded;
		this.list[_next]=payment;
		_next++;
		ifAdded=checkIfPresent(payment);
		return ifAdded;
	}
	
	/**
     * Checks if a payment is in the array
     * @param payment to find 
     * @return if the payment is present
     */
	boolean checkIfPresent(Payment element) {
		boolean isPresent=false;
			if(list[_next-1]==element) {
				isPresent=true;
			}
		
		return isPresent;
	}
	
	@Override
    public String toString() {
    	StringBuilder aux = new StringBuilder();
    	for(int i = 0; i<_next; i++)
    		aux.append(list[i].toString());
    	return aux.toString();
    }
	
}
