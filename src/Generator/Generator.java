package Generator;

public class Generator {
    public static int rand(int min, int max) {
        double resdouble = (Math.random() * ((max-min) + 1)) + min;
        return (int) resdouble;
    }
}
