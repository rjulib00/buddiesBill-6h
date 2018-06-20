package es.unileon.prg1.buddiesBill;
/**
 *
 * @author Matias D. Jonatan
 */
public class Movement {
    String name;
    float money;
    Buddy buddy;

    public Movement(String name, float money, Buddy buddy) {
        this.name = name;
        this.money = money;
        this.buddy = buddy;
    }

    public String getName() {
        return name;
    }

    public float getMoney() {
        return money;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    /**
     * Compare 2 Movement
     * 2 Movement are equal only if its names are the same
     * @param movement The movement to compare with the invoker one
     * @return Wheter if the Movement objetcs has the same name
     */
    public boolean equals(Movement movement) {
        boolean sameName;
        
        sameName = this.name.equals(movement.getName());
        
        return sameName;
    }
    
    
    @Override
    public String toString() {
        return this.getName() + " - " + this.getMoney() + " paid by " + this.getBuddy().getName();
    }
    
}
