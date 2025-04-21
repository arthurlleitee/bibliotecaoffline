package services;

import entities.models.Livro;
import entities.models.Usuario;
import utils.ArquivosUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaService {

    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private final String caminhoArquivoLivros = "data/livros.json";

    public BibliotecaService() {
        this.livros = ArquivosUtils.carregarLivrosDeArquivo(caminhoArquivoLivros);
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarLivroPorAutor(String autor) {
        return livros.stream()
                .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarLivroPorGenero(String genero) {
        return livros.stream()
                .filter(l -> l.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Livro buscarLivroPorId(int id) {
        return livros.stream()
                .filter(l -> l.getIdLivro() == id)
                .findFirst()
                .orElse(null);
    }

    public void adicionarLivro(Livro novoLivro) {
        livros.add(novoLivro);
        ArquivosUtils.salvarLivrosNoArquivo(livros, caminhoArquivoLivros);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario verificarLogin(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
}





