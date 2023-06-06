/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author chems
 */
class CustomDialog extends JDialog {
   private JLabel label;

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    public void setLabel(String text) {
        
        this.label = new JLabel (text);
    }
    public CustomDialog(JFrame parent) {
        super(parent, "Boîte de dialogue personnalisée", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Ajout de composants à la boîte de dialogue
         label = new JLabel("Contenu de la boîte de dialogue");
        getContentPane().add(label);
    }}
