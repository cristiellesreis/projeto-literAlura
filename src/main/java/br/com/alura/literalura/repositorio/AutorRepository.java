package br.com.alura.literalura.repositorio;

import br.com.alura.literalura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("""
                SELECT a FROM Autor a 
                WHERE a.anoNascimento IS NOT NULL 
                AND a.anoNascimento <= :ano 
                AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
            """)
    List<Autor> buscarAutoresVivosNoAno(@Param("ano") int ano);
}
