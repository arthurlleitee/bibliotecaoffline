package entities.models;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private String genero;
    private String linkPdf;

    public Livro(int idLivro, String titulo, String autor, String genero, String linkPdf) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.linkPdf = linkPdf;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }

    @Override
    public String toString() {
        return "\n---------------------------\n" +
                "ID: " + idLivro +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nGênero: " + genero +
                "\nPDF: " + linkPdf +
                "\n---------------------------";
    }
}
