package interfaces;


public interface iEVDContract {

    public interface Model {
        public void setPresenter(Presenter presenter);
        public double calculateTaxFinal(String brand, String line, String model, boolean departmentCondition);
    }
    
    public interface View {
        public void setPresenter(Presenter presenter);
        public void showFinalTax(double finalTax);
        public void carNotFound();
    }
    
    public interface Presenter {
        public void setView(View view);
        public void setModel(Model model);
        public void calculateTaxValue(String brand, String line, String model, boolean departmentCondition);
        public void carNotFound();
    }
}