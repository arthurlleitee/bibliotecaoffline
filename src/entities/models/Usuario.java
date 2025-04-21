package entities.models;

import java.time.LocalDateTime;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataNascimento;

    public Usuario(LocalDateTime dataNascimento, String email, int idUsuario, String nome, String senha) {
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuário {" +
                "ID = " + idUsuario +
                ", Nome = '" + nome + '\'' +
                ", Email = '" + email + '\'' +
                ", Data de Nascimento = " + dataNascimento +
                '}';
    }
}
