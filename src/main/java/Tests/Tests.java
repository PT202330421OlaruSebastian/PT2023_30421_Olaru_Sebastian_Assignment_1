package Tests;

import javax.swing.*;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {
    public void additionTests(){
        Hashtable<Integer, Integer> firstPoly = new Hashtable<>();
        Hashtable<Integer, Integer> secondPoly = new Hashtable<>();
        String exp[] = {"x^2","x^2","25x^5+30x^4+20x^3-12x^2+x-5","x","2x","2x^5","5","x+5","2x+5","2x^2+5","x^5","x^5"};
        String res[] = {"2x^2","25x^5+30x^4+20x^3-11x^2+x-5","25x^5+30x^4+20x^3-12x^2+2x-5","3x","2x^5+2x","2x^5+5","x+10","3x+10","2x^2+2x+10","x^5+2x^2+5","2x^5"};
        int tests = 0;
        for(int i = 1 ; i < exp.length; i++){
            PolynomialParser(exp[i],firstPoly);
            PolynomialParser(exp[i-1],secondPoly);

            firstPoly = addPolynomials(firstPoly,secondPoly);
            if (returnPolynomial(firstPoly).equals(res[i-1])){
                tests++;
            }else {
                System.out.println(returnPolynomial(firstPoly) + " result");
                System.out.println(res[i-1] + " expected");
            }
            firstPoly.clear();
            secondPoly.clear();
        }
        System.out.println(tests + "/" + res.length + " Tests suceeded");
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
                if (parts[0].isEmpty()) {
                    coefficient = 1;
                } else if (parts[0].equals("-")) {
                    coefficient = -1;
                } else {
                    coefficient = Integer.parseInt(parts[0]);
                }
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

        return sb.toString();
    }
}
