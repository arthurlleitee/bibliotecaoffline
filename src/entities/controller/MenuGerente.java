package entities.controller;

import entities.models.Livro;
import services.BibliotecaService;

import java.util.Scanner;

public class MenuGerente {

    private final BibliotecaService service;
    private final Scanner scanner = new Scanner(System.in);

    public MenuGerente(BibliotecaService service) {
        this.service = service;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DO GERENTE ===");
            System.out.println("1 - Cadastrar novo livro");
            System.out.println("2 - Listar todos os livros");
            System.out.println("3 - Buscar livro por título");
            System.out.println("4 - Buscar livro por autor");
            System.out.println("5 - Buscar livro por gênero");
            System.out.println("0 - Sair do menu do gerente");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> service.listarLivros().forEach(System.out::println);
                case 3 -> buscar("título");
                case 4 -> buscar("autor");
                case 5 -> buscar("gênero");
                case 0 -> System.out.println("Saindo do menu do gerente...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.print("Link para o PDF: ");
        String linkPdf = scanner.nextLine();

        int novoId = service.listarLivros().stream()
                .mapToInt(Livro::getIdLivro)
                .max()
                .orElse(0) + 1;

        Livro livro = new Livro(novoId, titulo, autor, genero, linkPdf);
        service.adicionarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void buscar(String tipo) {
        System.out.print("Digite o " + tipo + ": ");
        String termo = scanner.nextLine();
        switch (tipo) {
            case "título" -> service.buscarLivroPorTitulo(termo).forEach(System.out::println);
            case "autor" -> service.buscarLivroPorAutor(termo).forEach(System.out::println);
            case "gênero" -> service.buscarLivroPorGenero(termo).forEach(System.out::println);
        }
    }
}
