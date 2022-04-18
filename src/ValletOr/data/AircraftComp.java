package ValletOr.data;

public class AircraftComp extends Company{
    protected int TypesOfPlanes;
    public AircraftComp(String name, int employ, int types){
        super(name, employ);
        TypesOfPlanes = types;
    }
    @Override
    public String GetInfo(){
        String outMessage = "";
        outMessage += Name + ";" + NOfEmployees + ";" + TypesOfPlanes;
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

    public void SetTypesOfPlanes(int typesOfPlanes) {
        this.TypesOfPlanes = typesOfPlanes;
    }
}
