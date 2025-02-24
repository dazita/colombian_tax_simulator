package view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TaxDialog extends JDialog{

    private JLabel taxLabel;
    
    public TaxDialog(Frame frame){
        this.setSize(300, 200);
        this.setLocationRelativeTo(frame);
        this.setLayout(new BorderLayout());
        customizeTaxLabel();
        this.setVisible(false);
    }

    public void customizeTaxLabel(){
        taxLabel = new JLabel("", SwingConstants.CENTER);
        this.add(taxLabel, BorderLayout.CENTER);
    }

    public void setTaxLabelText(String text){
        taxLabel.setText(text);
    }
}
