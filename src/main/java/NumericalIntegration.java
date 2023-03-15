import java.util.function.Function;

public class NumericalIntegration {
    public double integrate(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (f.apply(a) + f.apply(b)) / 2.0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f.apply(x);
        }

        return h * sum;
    }
}
