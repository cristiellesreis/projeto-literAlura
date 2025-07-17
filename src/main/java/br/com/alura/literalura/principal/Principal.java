package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.RespostaDTO;
import br.com.alura.literalura.servico.LivroService;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private LivroService livroServico = new LivroService();

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 9) {
            var menu = """
                    \n***** Literalura *****
                    
                    Escolha o número de sua opção:                    
                    
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarLivrosPorIdioma() {


    }

    private void listarAutoresVivosPorAno() {

    }

    private void listarAutoresRegistrados() {

    }

    private void listarLivrosRegistrados() {

    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Qual o nome do livro que deseja buscar? ");
        var nome = leitura.nextLine();

        RespostaDTO resposta = livroServico.buscarLivro(nome);

        if (resposta.livros().isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            resposta.livros().stream()
                    .findFirst()
                    .ifPresent(System.out::println);
        }
    }

}
