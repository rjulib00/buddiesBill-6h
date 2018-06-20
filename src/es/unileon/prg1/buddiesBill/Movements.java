package es.unileon.prg1.buddiesBill;
/**
 *
 * @author Matias D. Jonatan
 */
public class Movements {
    int _next; //Apunta al primer null
    Movement[] list;

    /**
     * Contructor of the class
     * @param size the size of the array of Movement
     */
    public Movements(int size) {
        this._next = 0;
        this.list = new Movement[size];
    }

    /**
     * Adds a movement to the array in the first available position
     * (given by _next field)
     * @param movement the movement to add
     * @return whether the movement is added
     * @throws es.unileon.prg1.buddiesBill.BuddiesBillException when movement already exists
     */
    public boolean add(Movement movement) throws BuddiesBillException {
        boolean ifAdded;
        
        //Check if there is room in the Movements list
        if (this.list.length == this._next) {
            throw new BuddiesBillException("Cant add more movements, list full.");
        }
                
        //Check if that movement has already added
        ifAdded = checkIfPresent(movement);        
        if (ifAdded) {
            throw new BuddiesBillException("Movement name already exists.");
        }
        
        //Add the money to the buddy
        float money = movement.getBuddy().getMoney();
        float total = money + movement.getMoney();        
        movement.getBuddy().setMoney(total);
        
        this.list[_next] = movement;
        _next++;
        ifAdded = checkIfPresent(movement);
        
        return ifAdded;
    }
    
    /**
     * Removes a movement from the array permanently
     * @param movement the movement to remove
     * @return whether the movement is removed
     */
    public boolean remove(Movement movement) throws BuddiesBillException {
        boolean isRemoved = true;
        
        int positionRemove = indexOf(movement);
        
        if (positionRemove == -1) {
            String error = "Cant find a Movement called: " + movement.getName() + ".";
            throw new BuddiesBillException(error);
        }
        
        //Substract the money to the buddy
        float money = movement.getBuddy().getMoney();
        float total = money - movement.getMoney();        
        movement.buddy.setMoney(total); 
        
        this.list[positionRemove] = null;
        collapse();
        restorePointer();
        
        return isRemoved;
    }

    /**
     * Searches the movement in the array
     * @param movement the movement to localize
     * @return the position of the movement or -1 if it cant be founded
     */
    public int indexOf(Movement movement) {
        int position = -1;
        
        _next = -1;
        while(isAfterNext()) {
            _next++;
            if (this.list[_next].equals(movement)) { //Si lo encuentro
                position = _next;  
                _next = this.list.length;
            }
        }
        
        restorePointer();
        
        return position;
    }
    
    /**
     * Checks if a movement is in the array
     * @param movement the movement to find
     * @return whether the movement is present
     */
    private boolean checkIfPresent(Movement movement) {
        boolean isPresent = false;
        
        if (indexOf(movement) != -1) { 
            isPresent = true;
        }
        
        return isPresent;
    }
    

    /**
     * Checks if the field _next can go further
     * This cant be possible by two reasons:
     * - The cursor is pointing to the last available position (array full)
     * - The next element is null
     * 
     * This method is useful for iterate over the collection
     * @return whether the field _next can go further
     */
    public boolean isAfterNext() {
        boolean isNextNull = false;
        boolean isEndArray;
        
        isEndArray = _next >= this.list.length-1;
        if (!isEndArray) {
            isNextNull = this.list[_next+1] == null;
        }
        
        return !(isEndArray || isNextNull);
    }
    
    /**
     * Searchs a movement in the list by its name and returns it
     * @param name The name of the movement to search
     * @return the movement finded
     * @throws BuddiesBillException  if the movment cant be finded
     */
    public Movement search(String name) throws BuddiesBillException {
        Movement mov = new Movement(name, 0, null);
        int position = indexOf(mov);
        if (position == -1) {
            throw new BuddiesBillException("Cant find a movement called: " + name);
        } 
        return this.list[position];
    }
    
    /**
     * Maintain the array by allocating all the non-null elements to the first
     * positions of the array
     * 
     * 1    2   3   null    4   5   
     * 1    2   3   4   5   null
     */
    private void collapse() {
        Movement[] collapsed = new Movement[list.length];
        int j = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                    collapsed[j++] = list[i];
            }
        }
        this.list = collapsed;
    }

    /**
     * Points _next to the first null
     */
    public void restorePointer() {
        _next = -1;
        
        while (isAfterNext()) {
            _next++;
        }
        _next++;
        
    }
    
    @Override
    public String toString() {
    	StringBuilder aux = new StringBuilder();
    	for(int i = 0; i<_next; i++)
    		aux.append(list[i].toString())
                   .append("\n");
    	return aux.toString();
    }
}