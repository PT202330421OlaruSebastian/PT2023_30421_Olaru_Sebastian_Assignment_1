package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class MainForm extends JDialog{
    private JPanel mainPanel;
    private JTextField firstPolynomial;
    private JTextField secondPolynomial;
    private JTextField resultField;
    private JButton multiplicateButton;
    private JButton subtractButton;
    private JButton divideButton;
    private JButton moduloButton;
    private JButton addButton;
    private JButton exitButton;

    HashMap<Integer,Integer> firstPoly,secondPoly = new HashMap<>();

    public MainForm(JFrame parent) {
        super(parent);
        setTitle("Polynomial Calculator");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(800,200));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        multiplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstP = getTextFromTextField(firstPolynomial);
                String secondP = getTextFromTextField(secondPolynomial);


            }
        });
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        moduloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstP[] = getTextFromTextField(firstPolynomial).split("-|\\+|x\\^");
                String secondP[] = getTextFromTextField(secondPolynomial).split("");

                System.out.println(Arrays.toString(firstP));
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    String getTextFromTextField(JTextField textField){
        return textField.getText();
    }
}
