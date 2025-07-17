package br.com.alura.literalura.servico;

import br.com.alura.literalura.dto.RespostaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class LivroService {

    private ObjectMapper mapper = new ObjectMapper();

    public RespostaDTO buscarLivro(String titulo) {
        try {
            String tituloFormatado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://gutendex.com/books/?search=" + tituloFormatado))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            return mapper.readValue(json, RespostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}


