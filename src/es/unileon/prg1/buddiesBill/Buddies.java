package es.unileon.prg1.buddiesBill;

public class Buddies {
    private int _next;
    private Buddy[] members;
    
    
    public Buddies(int membersNumber){//creacion de un grupo sin nombre
        this._next = 0;
        this.members = new Buddy[membersNumber];
    }
    
    public int getNumberOfMembers(){//cuantos miembros hay en un grupo
        return members.length;
    }
    
    public boolean add(Buddy member) throws BuddiesBillException{//en caso de querer crear un nuevo miembro y ponerlo en el grupo
    	boolean aux = false;
	    if(this._next<members.length && !checkIfPresent(member)){
	    	members[this._next] = member;
	    	this._next = this._next+1;
		    aux = true;
	    }else {
	    	throw new BuddiesBillException("Not allowed");
	    }
        return aux;
    }
    
    public Buddy search(String name){//en caso de que queramos buscar si esta un cierto miembro del grupo
    	Buddy aux = null;
    	int i = 0;
    	do{
            if(members[i]!= null && members[i].getName().equalsIgnoreCase(name)){//comprobamos todos los nombres, pero no tenemos en cuenta como se ha escrito, es decir
            	//no se distingue entre mayusculas y minusculas
                aux = members[i];
            }
            i++;
        }while(i<this._next);
        return aux;
    }
    
    public boolean remove(Buddy member) throws BuddiesBillException{//en caso de que un miembro deje el grupo y no queramos que siga en el
    	boolean aux = false;
    	int i = 0;
        do{
            if(members[i].getName().equalsIgnoreCase(member.getName())){
                members[i] = null;
                aux = true;
                collapse();
            }
            i++;
        }while(i<this._next);
        if(aux == false)
        	throw new BuddiesBillException("No buddy found");
        return aux;
    }
    
    public boolean checkIfPresent(Buddy miembro){//comprobar si alguien esta o no
    	boolean aux = false;
    	int i = 0;
	    	if(_next > 0) {
	    	do{
	            if(members[i].getName().equalsIgnoreCase(miembro.getName())){
	                aux = true;
	            }
	            i++;
	        }while(i<this._next);
    	}
        return aux;
    }
    
    private void collapse(){//colocar a todos los miembros juntos y que no queden huecos vacios entre ellos (dentro del vector de miembros)
        for(int i = 0; i<this._next-1; i++){
            if(members[i]==null && members[i+1]!= null){
                members[i] = members[i+1];
                members[i+1] = null;
            }
        }
        this._next = this._next-1;
    }
    
    public float getBalance() {
    	float total = 0.0f;
    	for(int i = 0; i<_next; i++)
    		total = total + members[i].getMoney();
    	return total;
    }

    @Override
    public String toString() {//nos imprime todos los miembros que haya en el grupo
    	StringBuilder aux = new StringBuilder();
    	for(int i = 0; i<_next; i++) {
    		aux.append(members[i].getName() + " : " + members[i].getMoney() +"\n");
    	}
    	String builded = aux.toString();
    	return builded;
    }
    
    public Buddy[] getList() {//devuelve la lista de buddies del tipo de array de buddy
    	Buddy[] aux = new Buddy[_next];
    	for(int i = 0; i<_next; i++) {
    		aux[i] = this.members[i];
    	}
    	return aux;
    }
    
    public int return_next() {
    	return _next;
    }
}

