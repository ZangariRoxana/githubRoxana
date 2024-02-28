import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private int[] coeficienti;

    public Polynomial() {
        coeficienti = new int[1];
    }
    public Polynomial(String input) {
        analiza(input);
    }

    private void analiza(String input) {
        Pattern pattern = Pattern.compile("(-?\\d+)x(\\^\\d+)?");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int coef = Integer.parseInt(matcher.group(1));
            int exponent = matcher.group(2) != null ? Integer.parseInt(matcher.group(2).substring(1)) : 1;


            capacitate(exponent + 1);
            coeficienti[exponent] += coef;
        }
    }

    private void capacitate(int size) {
        if (coeficienti == null || coeficienti.length <= size) {
            int[] newArray = new int[size + 1];
            if (coeficienti != null) {
                System.arraycopy(coeficienti, 0, newArray, 0, coeficienti.length);
            }
            coeficienti = newArray;
        }
    }

    public Polynomial add(Polynomial other) {
        int maxim = Math.max(this.coeficienti.length, other.coeficienti.length);
        Polynomial result = new Polynomial("");

        for (int i = 0; i < maxim; i++) {
            int thisCoefficient = (i < this.coeficienti.length) ? this.coeficienti[i] : 0;
            int otherCoefficient = (i < other.coeficienti.length) ? other.coeficienti[i] : 0;
            result.capacitate(i);
            result.coeficienti[i] = thisCoefficient + otherCoefficient;
        }

        return result;
    }

    public Polynomial subtract(Polynomial other) {
        int maxim = Math.max(this.coeficienti.length, other.coeficienti.length);
        Polynomial result = new Polynomial("");

        for (int i = 0; i < maxim; i++) {
            int thisCoefficient = (i < this.coeficienti.length) ? this.coeficienti[i] : 0;
            int otherCoefficient = (i < other.coeficienti.length) ? other.coeficienti[i] : 0;
            result.capacitate(i);
            result.coeficienti[i] = thisCoefficient - otherCoefficient;
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();

        for (int i = coeficienti.length - 1; i >= 0; i--) {
            if (coeficienti[i] != 0) {
                if (resultat.length() > 0) {
                    resultat.append(coeficienti[i] > 0 ? " + " : " - ");
                }
                resultat.append(Math.abs(coeficienti[i]));
                if (i > 0) {
                    resultat.append("x");
                    if (i > 1) {
                        resultat.append("^").append(i);
                    }
                }
            }
        }

        

        return resultat.toString();
    }
}