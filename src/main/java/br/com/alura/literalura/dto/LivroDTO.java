package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonProperty("id") Integer id,
                       @JsonProperty("title") String titulo,
                       @JsonProperty("authors")List<AutorDTO> autores) {
}
