package Operations;

import java.util.Hashtable;

public class BasicOperations {
    public Hashtable<Integer, Integer> addPolynomials(Hashtable<Integer, Integer> poly1, Hashtable<Integer, Integer> poly2) {
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

        // Remove terms with zero coefficients
        Hashtable<Integer, Integer> newResult = new Hashtable<Integer, Integer>();
        for (Integer exponent : result.keySet()) {
            int coefficient = result.get(exponent);
            if (coefficient != 0) {
                newResult.put(exponent, coefficient);
            }
        }

        return newResult;
    }

    public Hashtable<Integer, Integer> multiplicatePolynomials(Hashtable<Integer, Integer> poly1,
                                                           Hashtable<Integer, Integer> poly2) {
        Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();
        for (Integer exponent : poly1.keySet()) {
            for(Integer exponent2 : poly2.keySet()){
                if (result.containsKey(exponent+exponent2)) {
                    System.out.println(exponent + " " + exponent2);
                    result.put(exponent+exponent2, result.get(exponent+exponent2) + poly1.get(exponent)*poly2.get(exponent2));
                } else {
                    result.put(exponent+exponent2, poly1.get(exponent) * poly2.get(exponent2));
                }
            }
        }
        return result;
    }

    public static Hashtable<Integer, Integer> dividePolynomials(Hashtable<Integer, Integer> dividend,
                                                                Hashtable<Integer, Integer> divisor) {
        Hashtable<Integer, Integer> quotient = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> remainder = dividend;

        while (!remainder.isEmpty() && compareDegree(remainder, divisor) >= 0) {
            int divDegree = getHighestDegree(divisor);
            int remDegree = getHighestDegree(remainder);
            int degreeDiff = remDegree - divDegree;

            int divCoeff = divisor.get(divDegree);
            int remCoeff = remainder.get(remDegree);
            int quotientCoeff = remCoeff / divCoeff;

            quotient.put(degreeDiff, quotientCoeff);
            Hashtable<Integer, Integer> term = multiplyTerm(divisor, degreeDiff, quotientCoeff);
            remainder = subtractPolynomials(remainder, term);
            System.out.println("cplm");
        }

        return remainder;
    }

    private static Hashtable<Integer, Integer> multiplyTerm(Hashtable<Integer, Integer> polynomial, int degree, int coefficient) {
        Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();
        for (Integer exponent : polynomial.keySet()) {
            result.put(exponent - degree, polynomial.get(exponent) * coefficient);
        }
        return result;
    }

    private static int getHighestDegree(Hashtable<Integer, Integer> polynomial) {
        int maxDegree = 0;
        for (Integer exponent : polynomial.keySet()) {
            if (exponent > maxDegree) {
                maxDegree = exponent;
            }
        }
        return maxDegree;
    }

    private static int compareDegree(Hashtable<Integer, Integer> poly1, Hashtable<Integer, Integer> poly2) {
        int degree1 = getHighestDegree(poly1);
        int degree2 = getHighestDegree(poly2);
        return Integer.compare(degree1, degree2);
    }
}
