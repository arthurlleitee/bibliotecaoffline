package utils;

import java.util.regex.Pattern;

public class ValidarUsuario {
    public static boolean emailValido(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@(gmail|outlook|hotmail)\\.com$";
        return Pattern.matches(regex, email);
    }

    public static boolean senhaForte(String senha) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(regex, senha);
    }


}
