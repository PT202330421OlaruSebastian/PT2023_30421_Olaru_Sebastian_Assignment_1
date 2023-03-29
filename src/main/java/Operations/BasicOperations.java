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
    public Hashtable<Integer, Integer> subtractPolynomials(Hashtable<Integer, Integer> poly1,
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

    public Hashtable<Integer, Integer> multiplicatePolynomials(Hashtable<Integer, Integer> poly1,
                                                           Hashtable<Integer, Integer> poly2) {
        Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();

        for (Integer exponent : poly1.keySet()) {
            for(Integer exponent2 : poly2.keySet()){
                result.put(exponent+exponent2,poly1.get(exponent)*poly2.get(exponent2));
            }
        }
        return result;
    }
}
