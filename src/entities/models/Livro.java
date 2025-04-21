package entities.models;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private String genero;
    private String linkPdf;

    public Livro() {
        // Construtor vazio para o Gson
    }

    public Livro(int idLivro, String titulo, String autor, String genero, String linkPdf) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.linkPdf = linkPdf;
    }

    // Getters e setters
    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Título: %s | Autor: %s | Gênero: %s%nLink: %s%n",
                idLivro, titulo, autor, genero, linkPdf
        );
    }
}
