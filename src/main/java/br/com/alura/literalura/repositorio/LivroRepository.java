package br.com.alura.literalura.repositorio;

import br.com.alura.literalura.modelos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}