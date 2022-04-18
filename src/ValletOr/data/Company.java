package ValletOr.data;

public abstract class Company {
    protected String Name;
    protected int NOfEmployees;
    public Company(String name, int employ){
        Name = name;
        NOfEmployees = employ;
    }

    public abstract String GetInfo();

    public abstract void SetName(String name);
    public abstract void SetEmp(int emp);
}
