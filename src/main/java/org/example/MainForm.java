package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Function;

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

    Hashtable<Integer,Integer> firstPoly = new Hashtable<>(),secondPoly = new Hashtable<>();

    public MainForm(JFrame parent) {
        super(parent);
        setTitle("Polynomial Calculator");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(800,210));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        multiplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation(firstPolynomial) && inputValidation(secondPolynomial)) {
                    String result = "";

                    PolynomialParser(firstPolynomial.getText(), firstPoly);
                    PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = subtractPolynomials(firstPoly, secondPoly);

                    result = returnPolynomial(firstPoly);
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

            }
        });
        derivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputValidation(firstPolynomial) && inputValidation(secondPolynomial)) {
                    String result = "";


                    PolynomialParser(firstPolynomial.getText(), firstPoly);
                    PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = integrate(firstPoly);

                    result = returnPolynomial(firstPoly);
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
                if(inputValidation(firstPolynomial) && inputValidation(secondPolynomial)) {
                    String result = "";


                    PolynomialParser(firstPolynomial.getText(), firstPoly);
                    PolynomialParser(secondPolynomial.getText(), secondPoly);

                    firstPoly = addPolynomials(firstPoly, secondPoly);

                    result = returnPolynomial(firstPoly);
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

    public static boolean inputValidation(JTextField textField){
        String exp = textField.getText();
        if(exp.equals(""))
            return false;
        Pattern pattern = Pattern.compile("[A-z&&[^^x]]");
        Matcher matcher = pattern.matcher(exp);
        if(matcher.find())
            return false;
        else
            return true;
    }

    public static void PolynomialParser(String polynomial,Hashtable<Integer, Integer> poly) {
        String[] terms = polynomial.split("(?=[+-])");

        for (String term : terms) {
            if (term.isEmpty()) {
                continue;
            }

            int coefficient;
            int exponent;

            if (term.equals("x") || term.equals("+x")) {
                coefficient = 1;
            } else if (term.equals("-x")) {
                coefficient = -1;
            } else if (term.contains("x")) {
                String[] parts = term.split("x");
                coefficient = Integer.parseInt(parts[0]);
            } else {
                coefficient = Integer.parseInt(term);
                exponent = 0;
                poly.put(exponent, coefficient);
                continue;
            }

            if (term.contains("^")) {
                String[] parts = term.split("\\^");
                exponent = Integer.parseInt(parts[1]);
            } else {
                exponent = 1;
            }

            if (poly.containsKey(exponent)) {
                poly.put(exponent, poly.get(exponent) + coefficient);
            } else {
                poly.put(exponent, coefficient);
            }
        }
    }
    public static String returnPolynomial(Hashtable<Integer, Integer> poly) {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;

        for (Integer exponent : poly.keySet()) {
            int coefficient = poly.get(exponent);

            if (coefficient == 0) {
                continue;
            }

            if (!firstTerm && coefficient > 0) {
                sb.append("+");
            }

            if (coefficient == -1 && exponent != 0) {
                sb.append("-");
            } else if (coefficient != 1 || exponent == 0) {
                sb.append(coefficient);
            }

            if (exponent > 0) {
                sb.append("x");
                if (exponent > 1) {
                    sb.append("^" + exponent);
                }
            }

            firstTerm = false;
        }
        if (sb.isEmpty())
            sb.append("0");
        return sb.toString();
    }
    public static Hashtable<Integer, Integer> addPolynomials(Hashtable<Integer, Integer> poly1, Hashtable<Integer, Integer> poly2) {
        Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();

        for (Integer exponent : poly1.keySet()) {
            int coefficient = poly1.get(exponent);
            result.put(exponent, coefficient);
        }

        for (Integer exponent : poly2.keySet()) {
            int coefficient = poly2.get(exponent);
            if (result.containsKey(exponent)) {
                result.put(exponent, result.get(exponent) + coefficient);
            } else {
                result.put(exponent, coefficient);
            }
        }

        return result;
    }
    public static Hashtable<Integer, Integer> subtractPolynomials(Hashtable<Integer, Integer> poly1,
                                                       Hashtable<Integer, Integer> poly2) {
        Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();

        for (Integer exponent : poly1.keySet()) {
            if (poly2.containsKey(exponent)) {
                result.put(exponent, poly1.get(exponent) - poly2.get(exponent));
            } else {
                result.put(exponent, poly1.get(exponent));
            }
        }

        for (Integer exponent : poly2.keySet()) {
            if (!poly1.containsKey(exponent)) {
                result.put(exponent, -poly2.get(exponent));
            }
        }

        return result;
    }
    public static Hashtable<Integer, Double> integrate(Hashtable<Integer, Integer> poly) {
        Hashtable<Integer, Double> result = new Hashtable<Integer, Double>();
        Function<Double, Double> f = (x) -> evaluate(poly, x);

        for (int i = 0; i <= poly.keySet().stream().max(Integer::compare).orElse(0); i++) {
            double coefficient = poly.getOrDefault(i, 0.0);
            if (coefficient != 0.0) {
                double integralCoefficient = coefficient / (i + 1);
                Function<Double, Double> fIntegrand = (x) -> {
                    double integrandResult = 0.0;
                    for (int j = 0; j <= i; j++) {
                        double termCoefficient = poly.getOrDefault(j, 0.0);
                        if (termCoefficient != 0.0) {
                            integrandResult += termCoefficient * Math.pow(x, j);
                        }
                    }
                    return integrandResult;
                };
                NumericalIntegrat
                double integral = NumericalIntegration.integrate(fIntegrand, 0, 1, 1000); // upper bound is arbitrary
                result.put(i + 1, integralCoefficient * integral);
            }
        }

        return result;
    }
    private static double evaluate(Hashtable<Integer, Integer> poly, double x) {
        double result = 0.0;

        for (Integer exponent : poly.keySet()) {
            double coefficient = poly.get(exponent);
            result += coefficient * Math.pow(x, exponent);
        }

        return result;
    }
}
