package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import interfaces.iEVDContract;
import interfaces.iEVDContract.Presenter;

public class View extends JFrame implements iEVDContract.View{

    private iEVDContract.Presenter presenter;
    private JLabel dateLabel;
    private JLabel brandLabel;
    private JLabel lineLabel;
    private JLabel modelLabel;
    private JTextField brandField;
    private JTextField lineField;
    private JTextField modelField;
    private JButton searchButton;
    private JCheckBox departmentCondition;
    private JDialog taxDialog;


    public View(){
        customizeFrame();
        customizeDateLabel();
        customizeBrandLabel();
        customizeLineLabel();
        customizeModelLabel();
        customizeBrandField();
        customizeLineField();
        customizeModelField();
        customizeSearchButton();
        customizeDepartmentCondition();
        customizeJDialog();
        this.setVisible(true);
    }

    public void customizeJDialog(){
        taxDialog = new TaxDialog(this);
        this.add(taxDialog);
    }

    public void customizeFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tax Simulator");
        this.setLayout(null);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void customizeDateLabel(){
        dateLabel = new JLabel(LocalDate.now().toString());
        dateLabel.setBounds(30, 0, 200, 100);
        this.add(dateLabel);
    }

    public void customizeBrandLabel(){
        brandLabel = new JLabel("digite el nombre de la marca");
        brandLabel.setBounds(81, 60, 188, 68);
        this.add(brandLabel);
    }

    public void customizeLineLabel(){
        lineLabel = new JLabel("digite la línea del vehículo");
        lineLabel.setBounds(81, 140, 188, 68);
        this.add(lineLabel);
    }

    public void customizeLineField(){
        lineField = new JTextField();
        lineField.setBounds(300, 140, 188, 68);
        this.add(lineField);
    }

    public void customizeModelField(){
        modelField = new JTextField();
        modelField.setBounds(300, 220, 188, 68);
        this.add(modelField);
    }

    public void customizeModelLabel(){
        modelLabel = new JLabel("digite el modelo del vehículo");
        modelLabel.setBounds(81, 220, 188, 68);
        this.add(modelLabel);
    }

    public void customizeBrandField(){
        brandField = new JTextField();
        brandField.setBounds(300, 60, 188, 68);
        this.add(brandField);
    }

    public void customizeSearchButton(){
        searchButton = new JButton("Buscar");
        searchButton.setBounds(200, 400, 100, 40);
        searchButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String brand = brandField.getText();
                String line = lineField.getText();
                String model = modelField.getText();
                boolean registeredInBoyacá = departmentCondition.isSelected();
                presenter.calculateTaxValue(brand, line, model, registeredInBoyacá);
            }
            
        });
        this.add(searchButton);
    }

    public void customizeDepartmentCondition(){
        departmentCondition = new JCheckBox("Matriculado en boyacá");
        departmentCondition.setBounds(180, 300, 800, 40);
        this.add(departmentCondition);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFinalTax(double finalTax) {
        ((TaxDialog)taxDialog).setTaxLabelText(String.valueOf(finalTax));
        taxDialog.setVisible(rootPaneCheckingEnabled);
    }

    @Override
    public void carNotFound() {
        ((TaxDialog)taxDialog).setTaxLabelText("Vehículo no encontrado");
    }
}
