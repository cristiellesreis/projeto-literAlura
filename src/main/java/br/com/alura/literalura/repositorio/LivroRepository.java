package br.com.alura.literalura.repositorio;

import br.com.alura.literalura.modelos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l JOIN l.idiomas i WHERE LOWER(i) = LOWER(:idioma)")
    List<Livro> findByIdioma(@Param("idioma") String idioma);

    long countByIdiomas(String idioma);

    List<Livro> findTop10ByOrderByDownloadCountDesc();

}