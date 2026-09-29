package space.gmarqueszx.frete_flow.util;

public class CPFValidator {
    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }
        int[] d = cpf.chars().map(c -> c - '0').toArray();

        int soma1 = 0;
        for (int i = 0; i < 9; i++) soma1 += d[i] * (10 - i);
        int dv1 = (soma1 * 10) % 11;
        if (dv1 == 10) dv1 = 0;

        int soma2 = 0;
        for (int i = 0; i < 10; i++) soma2 += d[i] * (11 - i);
        int dv2 = (soma2 * 10) % 11;
        if (dv2 == 10) dv2 = 0;

        return dv1 == d[9] && dv2 == d[10];
    }
}
