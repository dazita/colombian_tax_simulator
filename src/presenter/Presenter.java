package presenter;

import interfaces.iEVDContract;
import interfaces.iEVDContract.Model;
import interfaces.iEVDContract.View;

public class Presenter implements iEVDContract.Presenter{

    private iEVDContract.View view;
    private iEVDContract.Model model;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void calculateTaxValue(String brand, String line, String model, boolean departmentCondition) {
        model.calculateTaxValue(brand, line, model, departmentCondition);
    }
}
