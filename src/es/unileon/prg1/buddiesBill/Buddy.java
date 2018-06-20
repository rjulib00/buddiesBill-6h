package es.unileon.prg1.buddiesBill;

public class Buddy {
    private String _name;
    private float _money;
    
    
    public Buddy(String name, float money){//metodo para la creacion de un miembro
        this._name = name;
        this._money = money;
    }
    
    public void setName(String name){//darle un nombre nuevos
        this._name = name;
    }
    public void setMoney(float money){//darle una nueva cantidad
        this._money = money;
    }
    public String getName(){//saber el nombre del miembro
        return this._name;
    }
    public float getMoney(){//saber el dinero que ha puesto ese miembro
        return this._money;
    }
    
    @Override
    public String toString(){//imprimir el miembro y cuanto ha pagado
        return this._name + " : " + this._money;
    }
    
}
