package entities.controller;

import entities.models.Usuario;
import exceptions.CredenciaisInvalidasException;
import exceptions.EmailDuplicadoException;
import services.BibliotecaService;

import java.util.InputMismatchException;
import java.util.Scanner;


import static utils.ValidarUsuario.emailValido;
import static utils.ValidarUsuario.senhaForte;

public class BibliotecaController {

    private final Scanner scanner = new Scanner(System.in);
    private final BibliotecaService service = new BibliotecaService();
    private Usuario usuarioLogado;
    private final String emailGerente = "gerente@biblioteca.com";
    private final String senhaGerente = "Admin@123";



    public void iniciar() {
        int opcao = 0;
        do {
            System.out.println("====== BEM-VINDO À BIBLIOTECA OFFLINE ======");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Entrar como gerente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine(); // limpa o buffer
            }

            switch (opcao) {
                case 1 -> login();
                case 2 -> cadastrarUsuario();
                case 3 -> acessarComoGerente();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (usuarioLogado == null && opcao != 0);

        if (usuarioLogado != null) {
            new MenuUsuario(service, usuarioLogado).exibirMenu();
        }
    }

    private void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try{
            Usuario usuario = service.verificarLogin(email, senha);
            usuarioLogado = usuario;
            System.out.println("Login realizado com sucesso. Bem-vindo(a), " + usuario.getNome() + "!");

        } catch (CredenciaisInvalidasException e) {
            System.out.println(e.getMessage());
        }


    }

    private void acessarComoGerente() {
        System.out.print("Email do gerente: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (email.equals("gerente@biblioteca.com") && senha.equals("Admin@123")) {
            new MenuGerente(service).exibirMenu();
        } else {
            System.out.println("Credenciais de gerente inválidas.");
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

            try {
                if (service.emailJaCadastrado(email)) {
                    throw new EmailDuplicadoException("Este email já está cadastrado. Tente outro.");
                }
            } catch (EmailDuplicadoException e) {
                System.out.println(e.getMessage());
                email = ""; // força repetição do loop
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

}


