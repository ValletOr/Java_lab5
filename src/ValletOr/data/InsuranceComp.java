package ValletOr.data;

public class InsuranceComp extends Company{
    protected int NOfClients;
    public InsuranceComp(String name, int employ, int clients){
        super(name, employ);
        NOfClients = clients;
    }
    @Override
    public String GetInfo(){
        String outMessage = "";
        outMessage += Name + ";" + NOfEmployees + ";" + NOfClients;
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

    public void SetNOfClients(int NOfClients) {
        this.NOfClients = NOfClients;
    }
}
