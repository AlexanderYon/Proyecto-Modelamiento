package modelo;

import java.text.DecimalFormat;
import java.util.Objects;

public class Rut {

    private final long numero;
    private final char digitoVerificador;

    public Rut(long num, char dv) {
        this.numero = num;
        this.digitoVerificador = Character.toUpperCase(dv);
    }

    /**Retorna una cadena con el formato "xx.xxx.xxx-x"*/
    @Override
    public String toString(){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(numero) + "-" + digitoVerificador;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rut rut = (Rut) o;
        return numero == rut.numero && digitoVerificador == rut.digitoVerificador;
    }

    @Override
    public int hashCode(){
        return Objects.hash(numero, digitoVerificador);
    }

    /**Valida que una cadena pasada por parámetro corresponda a un Rut, comprobando que el dígito verificador sea correcto y que
     * cumpla con alguno de los siguientes formatos:
     * <pre>
     *     "xx.xxx.xxx-x" o "xxxxxxxx-x"
     * @param rut Corresponde al rut que se desea validar
     * @return true; si cumple con el formato o false en caso contrario
     * @see #digitoValido(String)
     * */
    public static boolean esValido(String rut){
        String[] partes = rut.split("-");   // [xx.xxx.xxx] [x] o [xxxxxxxx] [x]

        // El rut debe tener solo un guión
        if (partes.length != 2)
            return false;

        // Si tiene puntos, deben ser sólo 2
        if (partes[0].contains(".") && partes[0].split("\\.").length != 3) {
            return false;

        }else if (partes[0].contains(".")) {

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

    /**Retorna un objeto Rut construido a partir de una cadena pasada por parámetro.<p>
     *
     * @param rut Corresponde al rut que se quiere construir
     * @return un Rut; si la cadena cumple con el formato, no nulo.
     * @see #esValido(String)
     * */
    public static Rut valueOf(String rut) throws IllegalAccessException{
        if (esValido(rut))
            return new Rut(
                    Long.parseLong(rut.substring(0, rut.indexOf("-")).replace(".", "")), // xxxxxxxx
                    rut.charAt(rut.length()-1) // x
            );
        throw new IllegalAccessException("ERROR: El rut " + rut + " no cumple con el formato o no existe. ");
    }

    /**Comprueba que el rut corresponda a un rut chileno válido calculando el dígito verificador a través del "módulo 11".
     * */
    private static boolean digitoValido(String rut){
        String[] parts = rut.split("-");
        String tempNumero = parts[0].replace(".", "");
        tempNumero = new StringBuilder(tempNumero).reverse().toString();

        long[] serieNumerica = {2,3,4,5,6,7};
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