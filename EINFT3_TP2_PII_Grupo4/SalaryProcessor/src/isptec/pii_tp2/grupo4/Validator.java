package isptec.pii_tp2.grupo4;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    public static boolean isEmailValido(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email != null && email.matches(regex);
    }

    public static LocalDate validarData(String dataStr) {
        try {
            LocalDate data = LocalDate.parse(dataStr);
            if (data.isAfter(LocalDate.now())) {
                return null;
            }
            return data;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValorPositivo(double valor) {
        return valor > 0;
    }

    public static boolean hasContent(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }
    
    public static boolean isNomeArquivoValido(String nomeArquivo) {
        if (!hasContent(nomeArquivo)) {
            return false; 
        }
        // Caracteres inválidos comuns em Windows/Linux para nomes de arquivo
        String caracteresInvalidos = "[<>:\"/\\\\|?*]";
        return !nomeArquivo.matches(".*" + caracteresInvalidos + ".*");
    }
}