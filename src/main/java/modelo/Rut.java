package modelo;

import javax.persistence.Embeddable;
import java.text.DecimalFormat;
import java.util.Objects;

@Embeddable
public class Rut {

    private final long numero;
    private final char digitoVerificador;

    public Rut(long num, char dv) {
        this.numero = num;
        this.digitoVerificador = Character.toUpperCase(dv);
    }

    /**Retorna una cadena con el formato "xx.xxx.xxx-x"*/
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(numero) + "-" + digitoVerificador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rut rut = (Rut) o;
        return numero == rut.numero && digitoVerificador == rut.digitoVerificador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, digitoVerificador);
    }

    public static boolean esValido(String rut) {
        String[] partes = rut.split("-");   // [xx.xxx.xxx] [x] o [xxxxxxxx] [x]

        // El rut debe tener solo un guión
        if (partes.length != 2)
            return false;

        // Si tiene puntos, deben ser sólo 2
        if (partes[0].contains(".") && partes[0].split("\\.").length != 3) {
            return false;
        } else if (partes[0].contains(".")) {

            StringBuilder tempNumero = new StringBuilder(partes[0]);
            tempNumero.reverse();

            if (tempNumero.charAt(3) == '.' && tempNumero.charAt(7) == '.') {
                String numero = tempNumero.reverse().toString().replace(".", "");
                return numero.matches("[0-9]+") && digitoValido(rut);
            }

            return false;
        }
        return partes[0].replace(".", "").matches("[0-9]+") && digitoValido(rut);
    }

    public static Rut valueOf(String rut) throws IllegalAccessException {
        if (esValido(rut))
            return new Rut(
                    Long.parseLong(rut.substring(0, rut.indexOf("-")).replace(".", "")), // xxxxxxxx
                    rut.charAt(rut.length() - 1) // x
            );
        throw new IllegalAccessException("ERROR: El rut " + rut + " no cumple con el formato o no existe.");
    }


    private static boolean digitoValido(String rut) {
        String[] parts = rut.split("-");
        String tempNumero = parts[0].replace(".", "");
        tempNumero = new StringBuilder(tempNumero).reverse().toString();

        long[] serieNumerica = {2, 3, 4, 5, 6, 7};
        long result = 0;

        for (int i = 0; i < tempNumero.length(); i++) {
            result += Long.parseLong(String.valueOf(tempNumero.charAt(i))) * serieNumerica[i % 6];
        }

        result = 11 - (result % 11);

        if (result == 10)
            return parts[1].matches("[Kk]");
        else if (result == 11)
            return parts[1].equals("0");
        else
            return Character.isDigit(parts[1].charAt(0)) && result == Long.parseLong(String.valueOf(parts[1]));
    }
}
