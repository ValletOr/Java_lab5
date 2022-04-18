package ValletOr.data;

public class ShipbuildComp extends Company {
    protected int ShipsPerWeek;
    public ShipbuildComp(String name, int employ, int ships){
        super(name, employ);
        ShipsPerWeek = ships;
    }
    @Override
    public String GetInfo(){
        String outMessage = "";
        outMessage += Name + ";" + NOfEmployees + ";" + ShipsPerWeek;
        return outMessage;
    }
    @Override
    public void SetName(String name) {
        this.Name = name;
    }

    @Override
    public void SetEmp(int emp) {
        this.NOfEmployees = emp;
    }

    public void SetShipsPerWeek(int shipsPerWeek) {
        this.ShipsPerWeek = shipsPerWeek;
    }
}
