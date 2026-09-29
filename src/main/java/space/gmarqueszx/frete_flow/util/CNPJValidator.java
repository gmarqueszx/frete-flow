package space.gmarqueszx.frete_flow.util;

public class CNPJValidator {

    private static final int[] PESO1 = {5,4,3,2,9,8,7,6,5,4,3,2};
    private static final int[] PESO2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

    public static boolean isValid(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) {
            return false;
        }
        int[] d = cnpj.chars().map(c -> c - '0').toArray();

        int soma1 = 0;
        for (int i = 0; i < 12; i++) soma1 += d[i] * PESO1[i];
        int dv1 = soma1 % 11;
        dv1 = dv1 < 2 ? 0 : 11 - dv1;

        int soma2 = 0;
        for (int i = 0; i < 13; i++) soma2 += d[i] * PESO2[i];
        int dv2 = soma2 % 11;
        dv2 = dv2 < 2 ? 0 : 11 - dv2;

        return dv1 == d[12] && dv2 == d[13];
    }
}