package entities.controller;

import entities.models.Livro;
import entities.models.Usuario;
import services.BibliotecaService;

import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private final BibliotecaService service;
    private final Usuario usuario;
    private final Scanner scanner = new Scanner(System.in);

    public MenuUsuario(BibliotecaService service, Usuario usuario) {
        this.service = service;
        this.usuario = usuario;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DO USUÁRIO ===");
            System.out.println("1 - Listar todos os livros");
            System.out.println("2 - Buscar livro por título");
            System.out.println("3 - Buscar livro por autor");
            System.out.println("4 - Buscar livro por gênero");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> service.listarLivros().forEach(System.out::println);
                case 2 -> buscar("título");
                case 3 -> buscar("autor");
                case 4 -> buscar("gênero");
                case 0 -> System.out.println("Logout realizado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void buscar(String tipo) {
        System.out.print("Digite o " + tipo + ": ");
        String termo = scanner.nextLine();

        List<Livro> resultados = switch (tipo) {
            case "título" -> service.buscarLivroPorTitulo(termo);
            case "autor" -> service.buscarLivroPorAutor(termo);
            case "gênero" -> service.buscarLivroPorGenero(termo);
            default -> List.of();
        };

        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            resultados.forEach(System.out::println);
        }
    }
}
