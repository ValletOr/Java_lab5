package ValletOr.data;

import java.util.ArrayList;

public class CompControl {
    public ArrayList<Company> CompList = new ArrayList<>();
    public CompControl(){
        CompList.add(new ShipbuildComp("Ships Comp.", 500, 10));
        CompList.add(new AircraftComp("Civil Air Comp.", 400, 15));
        CompList.add(new InsuranceComp("MoneyForTheLifes Comp.", 50, 100));
        CompList.add(new ShipbuildComp("Warships Comp.", 800, 30));
        CompList.add(new InsuranceComp("Ins Comp.", 30, 70));
    }
    public void AddComp(Company company){
        CompList.add(company);
    }

    public void RemoveComp(int index){
        CompList.remove(index);
    }

    public int GetSize(){
        return this.CompList.size();
    }

    public Company GetComp(int i) {
        return CompList.get(i);
    }
}
