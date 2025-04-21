package entities.controller;

import entities.models.Usuario;
import services.BibliotecaService;

import java.util.Scanner;
import utils.ValidarUsuario;

import static utils.ValidarUsuario.emailValido;
import static utils.ValidarUsuario.senhaForte;

public class BibliotecaController {

    private final Scanner scanner = new Scanner(System.in);
    private final BibliotecaService service = new BibliotecaService();
    private Usuario usuarioLogado;



    public void iniciar() {
        int opcao;
        do {
            System.out.println("====== BEM-VINDO À BIBLIOTECA OFFLINE ======");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> login();
                case 2 -> cadastrarUsuario();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (usuarioLogado == null && opcao != 0);

        if (usuarioLogado != null) {
            exibirMenuPrincipal();
        }
    }

    private void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = service.verificarLogin(email, senha);
        if (usuario != null) {
            usuarioLogado = usuario;
            System.out.println("Login realizado com sucesso. Bem-vindo(a), " + usuario.getNome() + "!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    private void cadastrarUsuario() {

        String nome;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome não pode estar em branco.");
            }
        } while (nome.isEmpty());

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();

            if (!emailValido(email)) {
                System.out.println("Email inválido. Aceitamos apenas Gmail, Outlook ou Hotmail.");
            }
        } while (!emailValido(email));

        String senha;
        String confirmarSenha = "";
        do {
            System.out.print("Senha: ");
            senha = scanner.nextLine().trim();

            if (!senhaForte(senha)) {
                System.out.println("Senha fraca. Ela deve conter:");
                System.out.println("- Pelo menos 1 letra maiúscula");
                System.out.println("- Pelo menos 1 letra minúscula");
                System.out.println("- Pelo menos 1 número");
                System.out.println("- Pelo menos 1 símbolo (@#$%^&+=!)");
                System.out.println("- Mínimo de 8 caracteres");
                continue;
            }

            System.out.print("Confirme a senha: ");
            confirmarSenha = scanner.nextLine().trim();

            if (!senha.equals(confirmarSenha)) {
                System.out.println("As senhas não coincidem. Tente novamente.");
            }

        } while (!senhaForte(senha) || !senha.equals(confirmarSenha));


        int novoId = service.gerarNovoIdUsuario();
        Usuario usuario = new Usuario(novoId, nome, email, senha);
        service.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Listar todos os livros");
            System.out.println("2 - Buscar livro por título");
            System.out.println("3 - Buscar livro por autor");
            System.out.println("4 - Buscar livro por gênero");
            System.out.println("0 - Sair");
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
        switch (tipo) {
            case "título" -> service.buscarLivroPorTitulo(termo).forEach(System.out::println);
            case "autor" -> service.buscarLivroPorAutor(termo).forEach(System.out::println);
            case "gênero" -> service.buscarLivroPorGenero(termo).forEach(System.out::println);
        }
    }
}


