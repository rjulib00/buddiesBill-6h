package es.unileon.prg1.buddiesBill;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class TextUI {
	
	private static final Logger logger = LogManager.getLogger(BuddiesBill.class);

	
	public TextUI(String event, int people, int quantity, float potpool) throws BuddiesBillException {
		logger.trace("Initialization");
		run(event, people, quantity, potpool);
	}
	public TextUI() {
		
	}
	
	public String startUp(String evento) {//mensaje con el menu principal
		return evento + " event\n------\nEvent: "+ evento + "\nPeople:\nMovements:\nBalance: 0.0\n---------\n" + options();
	}
	public String options() {//opciones disponibles
		return "Select an option:\n\t1- Add buddy\n\t2- Remove buddy\n\t3- Add movement\n\t4- Remove movement\n\t5- Show summary\n\t6- Settle up\n\t7- Exit";
	}
	
	public int option() {//comprobar que la opcion introducida es correcta
		int aux = Teclado.readInteger();
		if(aux<1 || aux>7) {
			System.out.println("Invalid option, repeat:");
			aux = -1;
		}
		return aux;
	}
	
	public String info(Buddies group, Movements movements, String evento) {//informacion de movimientos y miembros
		String information = "";
		Buddy aux = group.search("Pot");
		information = "Event: "+ evento + "\nPeople:\n" + group.toString()+"\nMovements:\n" + movements.toString() + "\nPot: " + aux.getMoney() + "\nBalance: " + group.getBalance() +"\n";
		logger.info(information);
		return information;
	}
	public String removeMember() {//eliminar miembro de un grupo
		System.out.println("Name of the buddy to remove:\n");
		String aux = Teclado.readString();
		return aux;
	}
	public String addMember() {//introducir un nuevo miembro en el grupo
		System.out.println("Name of the buddy to add:\n");
		String aux = Teclado.readString();
		return aux;
	}
	
	public String[] addMovement() {//crear un nuevo movimiento (no pueden haber 2 movimientos con el mismo nombre)
		String aux[] = new String[3];
			System.out.println("What:");
			aux[0] = Teclado.readString();
			System.out.println("How much:");
			aux[1] = Teclado.readString() + "";
			System.out.println("Who:");
			aux[2] = Teclado.readString();
		return aux;
	}
	public String removeMovement() {//eliminar un movimiento ya existente
		String aux = "";
		System.out.println("Name of the movement to remove:\n");
		aux = Teclado.readString();
		return aux;
	}
	public String summary(Buddies group, Movements movements, String evento) {//imprimir el resumen de como van las cosas
		String aux;
		aux = info(group, movements, evento);
		return aux;
	}
	
	public void print(String printed) {
		System.out.println(printed);
	}
	
	public void run(String event, int members, int movements, float potpool) throws BuddiesBillException {//ejecucion del programa
		int exit = 1;
		Event programm = new Event(event, members, movements);//creamos el evento
		System.out.println(startUp(event));//iniciamos el programa
		boolean end = false;//auxiliar para saber cuando acabamos
		int select = 0;//la opcion que escogemos
		String aux[] = new String[3];//un auxiliar para trabajar
		programm.getBuddies().add(new Buddy("Pot", 0.0f));
		while(!end) {
			select = option();
			if(select != -1) {
					switch (select) {
						case 1://Add Member
							aux[0] = addMember();
							try {
								programm.getBuddies().add(new Buddy(aux[0], 0.0f));
								programm.getBuddies().search("Pot").setMoney(programm.getBuddies().search("Pot").getMoney()+potpool);
								logger.trace("Add member");
								logger.info("Member add: "+ aux[0].toString());
							}catch (BuddiesBillException e) {
								logger.error("Failed to add member");
								System.err.println("Failed to add member: " + aux[0]);
							}
							break;
						case 2://Remove Member
							aux[0] = removeMember();
							try {
								programm.getBuddies().remove(programm.getBuddies().search(aux[0]));
								programm.getBuddies().search("Pot").setMoney(programm.getBuddies().search("Pot").getMoney()-potpool);
								logger.trace("Remove member");
								logger.info("Member remove: "+ aux[0].toString());
							}catch(BuddiesBillException e) {
								logger.error("Failed to remove member");
								System.err.println("Failed to remove member: " + aux[0]);
							}
							break;
						case 3://Add Movement
							aux = addMovement();
							try {
								float price = Float.parseFloat(aux[1]);
								programm.getMovements().add(new Movement(aux[0], price, programm.getBuddies().search(aux[2])));
								logger.trace("Add movement");
								logger.info("Movement add: " + aux[0].toString());
							}catch (NumberFormatException e) {
								logger.error("Failed to add movement");
								System.err.println("Failed to add movement: " + aux[0]);
							}
							aux[0] = "";
							aux[1] = "";
							aux[2] = "";
							break;
						case 4://remove Movement
							aux[0] = removeMovement();
							try {
								programm.getMovements().remove(programm.getMovements().search(aux[0]));
								logger.trace("Remove movement");
								logger.info("Movement remove: " + aux[0].toString());
							}catch (BuddiesBillException e) {
								logger.error("Failed remove movement");
								System.err.println("Failed to remove movement: " + aux[0]);
							}
							break;
						case 5://Obtener lo mismo que "info" pero en el momento que el usuario elija
							String information = "";
							try {
								information = summary(programm.getBuddies(), programm.getMovements(), event);
								logger.trace("Show Summary");
								System.out.println(information);
							}catch (Exception e) {
								logger.error("Failed to give info");
								System.err.println("Failed to give info");
							}
							break;
						case 6://iniciar cuentas (settle up)
							try {
								programm.settleUp();
								logger.trace("Execute SettleUp");
							}catch (Exception e) {
								logger.error("Failed at SettleUp");
								System.err.println("Failed at settleUp");
							}
							break;
						case 7://terminar programa
							try {
								end = true;
								logger.trace("Exit");
								System.out.println("Bye!");
								exit = 0;
							}catch (Exception e) {
								logger.error("Exit Failed");
								System.err.println("Fail at exit");
							}
							break;
					}
					if(exit == 1) {
						System.out.print(info(programm.getBuddies(), programm.getMovements(), event));//imprimimos el estado del grupo
						System.out.println(options());
					}
			}
		}
	}

}
