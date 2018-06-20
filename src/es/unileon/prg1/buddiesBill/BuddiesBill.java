package es.unileon.prg1.buddiesBill;

/**
 *
 * @author Matias D. Jonatan
 */
//Import log4j classes.
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BuddiesBill {

// Define a static logger variable so that it references the
// Logger instance named "MyApp".
    private static final Logger logger = LogManager.getLogger(BuddiesBill.class);

    public static void main(String[] args) throws BuddiesBillException {
        logger.trace("Entering application.");
        //Checks args size Name event, number of Buddies and number of Movements
        //TODO cambiar cuando esten implementadas Event
        if (args.length != 3) {
            throw new BuddiesBillException("Input error.\nCommand sintaxis:\nBuddiesBill eventTitle maxBuddies maxMovements");
        } else {
            Event event = new Event(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Float.parseFloat(args[3]));
            //TextUI textUI = new TextUI(event);
            TextUI text = new TextUI(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Float.parseFloat(args[3]));
        }

    }
}
