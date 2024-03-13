package org.example;

import Operations.BasicOperations;
import Operations.InputValidation;
import Operations.NumericalIntegration;
import Operations.PolynomialManipulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainForm extends JDialog{
    private JPanel mainPanel;
    private JTextField firstPolynomial;
    private JTextField secondPolynomial;
    private JTextField resultField;
    private JButton multiplicateButton;
    private JButton subtractButton;
    private JButton integralButton;
    private JButton derivativeButton;
    private JButton addButton;
    private JButton exitButton;
    private JButton divideButton;

    private BasicOperations basicOperations = new BasicOperations();
    private PolynomialManipulator polynomialManipulator = new PolynomialManipulator();
    private InputValidation inputValidation = new InputValidation();
    private NumericalIntegration numericalIntegration = new NumericalIntegration();
    Hashtable<Integer,Integer> firstPoly = new Hashtable<>(),secondPoly = new Hashtable<>();

    public MainForm(JFrame parent) {
        super(parent);
        setTitle("Polynomial Calculator");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(850,210));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        multiplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial) && inputValidation.inputValidation(secondPolynomial)) {
                    String result = "";

                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = basicOperations.multiplicatePolynomials(firstPoly, secondPoly);

                    result = polynomialManipulator.returnPolynomial(firstPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial) && inputValidation.inputValidation(secondPolynomial)) {
                    String result = "";

                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = basicOperations.subtractPolynomials(firstPoly, secondPoly);

                    result = polynomialManipulator.returnPolynomial(firstPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        integralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial)) {
                    String result = "";


                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    Hashtable<Integer,Double> resultPoly = new Hashtable<>();
                    System.out.println(resultPoly);
                    resultPoly = numericalIntegration.integrate(firstPoly);

                    result = polynomialManipulator.returnPolynomialDouble(resultPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        derivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial)) {
                    String result = "";


                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = numericalIntegration.derivate(firstPoly);
                    result = polynomialManipulator.returnPolynomial(firstPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial) && inputValidation.inputValidation(secondPolynomial)) {
                    String result = "";


                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = basicOperations.addPolynomials(firstPoly, secondPoly);

                    result = polynomialManipulator.returnPolynomial(firstPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation.inputValidation(firstPolynomial) && inputValidation.inputValidation(secondPolynomial)) {
                    String result = "";


                    polynomialManipulator.PolynomialParser(firstPolynomial.getText(), firstPoly);
                    polynomialManipulator.PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = basicOperations.dividePolynomials(firstPoly, secondPoly);

                    result = polynomialManipulator.returnPolynomial(firstPoly);
                    resultField.setText(result);

                    firstPoly.clear();
                    secondPoly.clear();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong type of input","Dialog",JOptionPane.ERROR_MESSAGE);
                }
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
}
