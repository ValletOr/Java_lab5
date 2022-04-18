package ValletOr;

import ValletOr.data.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class MyTableModel extends AbstractTableModel {

    private CompControl data;

    public MyTableModel(CompControl cc){
        this.data = cc;
    }

    @Override
    public int getRowCount() {
        return data.GetSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column){
        String outMessage = "";
        switch (column){
            case 0:
                outMessage = "№";
                break;
            case 1:
                outMessage = "Тип компании";
                break;
            case 2:
                outMessage = "Название компании";
                break;
            case 3:
                outMessage = "Кол-во сотрудников";
                break;
            case 4:
                outMessage = "Кол-во кораблей/типов самолётов/клиентов";
                break;
        }
        return outMessage;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String outMessage = "-";
        switch (columnIndex){
            case 0:
                return rowIndex + 1;
            case 1:
                if(data.GetComp(rowIndex) instanceof ShipbuildComp){
                    outMessage = "Судостроительная компания";
                }
                else if (data.GetComp(rowIndex) instanceof InsuranceComp){
                    outMessage = "Страховая компания";
                }
                else {
                    outMessage = "Самолётостроительная компания";
                }
                break;
            case 2:
                outMessage = data.GetComp(rowIndex).GetInfo().split(";")[0];
                break;
            case 3:
                outMessage = data.GetComp(rowIndex).GetInfo().split(";")[1];
                break;
            case 4:
                outMessage = data.GetComp(rowIndex).GetInfo().split(";")[2];
                break;
        }
        return outMessage;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 2:
                data.GetComp(rowIndex).SetName((String)aValue);
                break;
            case 3:
                if(((String)aValue).matches("[0-9]+")){
                    data.GetComp(rowIndex).SetEmp(Integer.parseInt((String)aValue));
                }
                break;
            case 4:
                if(((String)aValue).matches("[0-9]+")) {
                    Company temp = data.GetComp(rowIndex);
                    if (temp instanceof ShipbuildComp) {
                        ((ShipbuildComp) data.GetComp(rowIndex)).SetShipsPerWeek(Integer.parseInt((String) aValue));
                    } else if (temp instanceof InsuranceComp) {
                        ((InsuranceComp) data.GetComp(rowIndex)).SetNOfClients(Integer.parseInt((String) aValue));
                    } else {
                        ((AircraftComp) data.GetComp(rowIndex)).SetTypesOfPlanes(Integer.parseInt((String) aValue));
                    }
                }
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex>1){
            return true;
        }
        else{
            return false;
        }
    }

    public void Remove(int ind){
        data.RemoveComp(ind);
        fireTableDataChanged();
    }

    public void Add(String name, int emp, int spec, int type){
        switch (type){
            case 0:
                data.AddComp(new ShipbuildComp(name, emp, spec));
                break;
            case 1:
                data.AddComp(new InsuranceComp(name, emp, spec));
                break;
            case 2:
                data.AddComp(new AircraftComp(name, emp, spec));
                break;
        }
        fireTableDataChanged();
    }
}
