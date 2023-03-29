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

    public Hashtable<Integer,Integer> integrate(Hashtable<Integer,Integer> poly){ // NEEDS WORK
        Hashtable<Integer,Integer> result = new Hashtable<>();
        for (Integer exponent : poly.keySet()) {
            result.put(exponent+1,poly.get(exponent));
        }
        return result;

    }
}
