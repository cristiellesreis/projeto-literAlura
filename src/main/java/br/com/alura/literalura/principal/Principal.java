package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.RespostaDTO;
import br.com.alura.literalura.modelos.Autor;
import br.com.alura.literalura.modelos.Livro;
import br.com.alura.literalura.repositorio.AutorRepository;
import br.com.alura.literalura.repositorio.LivroRepository;
import br.com.alura.literalura.servico.ConverteDados;
import br.com.alura.literalura.servico.LivroService;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private LivroService livroServico = new LivroService();
    private LivroRepository repositorio;
    private final AutorRepository autorRepository;


    public Principal(LivroRepository repositorio, AutorRepository autorRepository) {
        this.repositorio = repositorio;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 7) {
            var menu = """
                    \n***** Literalura *****
                    
                    Escolha o número de sua opção:                    
                    
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    6- Top 10 livros mais baixados
                    
                    7 - Sair
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
                case 6:
                    listarTop10LivrosMaisBaixados();
                    break;
                case 7:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarTop10LivrosMaisBaixados() {

        List<Livro> livrosMaisBaixados = repositorio.findTop10ByOrderByDownloadCountDesc();

        System.out.println("\n---------------------------------------" +
                "\nTop 10 livros mais baixados:\n");

        if (livrosMaisBaixados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        int posicao = 1;
        for (Livro livro : livrosMaisBaixados) {
            System.out.printf(posicao++ + "º" + " - " + livro.getTitulo() + " (" + livro.getDownloadCount() + ")\n");
        }
        System.out.println("---------------------------------------");
    }

    private void listarLivrosPorIdioma() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Escolha um idioma (digite o número ou o código):
                
                1 - pt
                2 - en
                3 - es
                4 - fr
                """);

        String entrada = scanner.nextLine().trim().toLowerCase();
        String idioma;

        switch (entrada) {
            case "1", "pt" -> idioma = "pt";
            case "2", "en" -> idioma = "en";
            case "3", "es" -> idioma = "es";
            case "4", "fr" -> idioma = "fr";
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        List<Livro> livros = repositorio.findByIdioma(idioma);
        long quantidade = repositorio.countByIdiomas(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
            return;
        }

        System.out.println("\n---------------------------------------" +
                "\nLivros em: " + idioma + "\n");

        livros.stream()
                .map(Livro::getTitulo)
                .forEach(titulo -> System.out.println("- " + titulo));
        System.out.println("\nQuantidade de livros: " + quantidade +
                "\n---------------------------------------");
    }

    private void listarAutoresVivosPorAno() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um ano: ");
        int ano = scanner.nextInt();

        List<Autor> autoresVivos = autorRepository.buscarAutoresVivosNoAno(ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado nesse ano.");
        } else {
            System.out.println("\n---------------------------------------" +
                    "\nAutores vivos no ano " + ano + ":\n");
            autoresVivos.forEach(autor -> System.out.println("- " + autor.getNome()));
            System.out.println("\n---------------------------------------");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void buscarLivroPeloTitulo() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Qual o nome do livro que deseja buscar? ");
            var nome = leitura.nextLine();

            RespostaDTO resposta = livroServico.buscarLivro(nome);

            if (resposta.livros().isEmpty()) {
                System.out.println("Nenhum livro encontrado.");
            } else {
                resposta.livros().stream()
                        .findFirst()
                        .ifPresent(livroDTO -> {
                            System.out.println(livroDTO);

                            Livro livro = ConverteDados.converterParaEntidade(livroDTO);
                            repositorio.save(livro);
                        });
            }

            System.out.println("Quer buscar outro livro? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
