package br.com.alura.literalura.servico;

import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.modelos.Autor;
import br.com.alura.literalura.modelos.Livro;

import java.util.List;

public class ConverteDados {

    public static Livro converterParaEntidade(LivroDTO dto) {
        List<Autor> autores = dto.autores().stream()
                .map(autorDTO -> new Autor(autorDTO.nome(), autorDTO.anoNascimento(), autorDTO.anoFalecimento()))
                .toList();

        return new Livro(dto.titulo(), autores, dto.idiomas(), dto.downloadCount());
    }

}
