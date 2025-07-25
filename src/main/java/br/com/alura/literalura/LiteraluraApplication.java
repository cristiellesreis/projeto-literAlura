package br.com.alura.literalura;

import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.repositorio.AutorRepository;
import br.com.alura.literalura.repositorio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository repositorio;

    @Autowired
    private AutorRepository autorRepository;


    public static void main(String[] args) {

        SpringApplication.run(LiteraluraApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio, autorRepository);
        principal.exibeMenu();
    }
}
