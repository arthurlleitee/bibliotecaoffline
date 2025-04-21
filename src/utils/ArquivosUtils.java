package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.models.Livro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArquivosUtils {

    private static final Type tipoLista = new TypeToken<List<Livro>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Livro> carregarLivrosDeArquivo(String caminho) {
        try (FileReader reader = new FileReader(caminho)) {
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarLivrosNoArquivo(List<Livro> livros, String caminho) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(livros, tipoLista, writer);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
    }
}
