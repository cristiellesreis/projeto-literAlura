package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonProperty("id") Integer id,
                       @JsonProperty("title") String titulo,
                       @JsonProperty("authors") List<AutorDTO> autores,
                       @JsonProperty("languages") List<String> idiomas,
                       @JsonProperty("download_count") Integer downloadCount) {

    @Override
    public String toString() {
        String nomesAutores = autores.stream()
                .map(AutorDTO::nome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");

        return "\n---------------------------------------\n" +
                "Livro: " + titulo + "\n" +
                "Autor: " + nomesAutores + "\n" +
                "Idioma: " + idiomas + "\n" +
                "Downloads: " + downloadCount + "\n" +
                "---------------------------------------\n";
    }
}


