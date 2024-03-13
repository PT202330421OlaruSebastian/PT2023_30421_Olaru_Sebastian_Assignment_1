package Operations;

import java.util.Hashtable;
import java.util.function.Function;

public class NumericalIntegration {

    public Hashtable<Integer,Integer> derivate(Hashtable<Integer,Integer> poly){
        Hashtable<Integer,Integer> result = new Hashtable<>();
        for (Integer exponent : poly.keySet()) {
            result.put(exponent-1,poly.get(exponent)*exponent);
        }
        return result;
    }

    public Hashtable<Integer,Double> integrate(Hashtable<Integer,Integer> poly){ // NEEDS WORK
        Hashtable<Integer,Double> result = new Hashtable<>();
        for (Integer exponent : poly.keySet()) {
            double aux = poly.get(exponent)/exponent;
            result.put(exponent+1,aux);
        }
        return result;

    }
}
