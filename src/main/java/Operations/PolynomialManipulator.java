package Operations;

import java.util.Hashtable;

public class PolynomialManipulator {
    public void PolynomialParser(String polynomial, Hashtable<Integer, Integer> poly) {
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
            } else if (term.matches("[-+]?x\\^\\d+")) {
                coefficient = 1;
            } else if (term.contains("x")) {
                String[] parts = term.split("x");
                coefficient = Integer.parseInt(parts[0]);
            } else {
                coefficient = Integer.parseInt(term);
                exponent = 0;
                if (poly.containsKey(exponent)) {
                    poly.put(exponent, poly.get(exponent) + coefficient);
                } else {
                    poly.put(exponent, coefficient);
                }
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
    public String returnPolynomial(Hashtable<Integer, Integer> poly) {
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
    public String returnPolynomialDouble(Hashtable<Integer, Double> poly) {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;

        for (Integer exponent : poly.keySet()) {
            double coefficient = poly.get(exponent);

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
}
