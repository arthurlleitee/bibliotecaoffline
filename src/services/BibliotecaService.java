package services;

import entities.models.Usuario;
import entities.models.Livro;
import exceptions.CredenciaisInvalidasException;
import utils.ArquivosUtils;


import java.util.*;

public class BibliotecaService {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private final String caminhoLivros = "data/livros.json";
    private final String caminhoUsuarios = "data/usuarios.json";

    public BibliotecaService() {
        this.livros = Optional.ofNullable(ArquivosUtils.carregarLivrosDeArquivo(caminhoLivros)).orElse(new ArrayList<>());
        this.usuarios = Optional.ofNullable(ArquivosUtils.carregarUsuariosDeArquivo(caminhoUsuarios)).orElse(new ArrayList<>());
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    public List<Livro> buscarLivroPorAutor(String autor) {
        return livros.stream()
                .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .toList();
    }

    public List<Livro> buscarLivroPorGenero(String genero) {
        return livros.stream()
                .filter(l -> l.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .toList();
    }

    public Usuario verificarLogin(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new CredenciaisInvalidasException("Email ou senha incorretos."));
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        ArquivosUtils.salvarUsuariosNoArquivo(usuarios, caminhoUsuarios);
    }

    public int gerarNovoIdUsuario() {
        return usuarios.stream()
                .mapToInt(Usuario::getIdUsuario)
                .max()
                .orElse(0) + 1;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public boolean emailJaCadastrado(String email) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        ArquivosUtils.salvarLivrosNoArquivo(livros, caminhoLivros);
    }


}
