package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.models.Livro;
import entities.models.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArquivosUtils {
    private static final Gson gson = new Gson();

    public static List<Livro> carregarLivrosDeArquivo(String caminho) {
        try (FileReader reader = new FileReader(caminho)) {
            Type tipoLista = new TypeToken<List<Livro>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarLivrosNoArquivo(List<Livro> livros, String caminho) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(livros, writer);
        } catch (Exception e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarUsuariosDeArquivo(String caminho) {
        try (FileReader reader = new FileReader(caminho)) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarUsuariosNoArquivo(List<Usuario> usuarios, String caminho) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}