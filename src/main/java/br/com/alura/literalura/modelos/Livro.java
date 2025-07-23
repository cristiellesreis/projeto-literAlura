package br.com.alura.literalura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    private List<String> idiomas;

    private Integer downloadCount;

    public Livro(String titulo, List<Autor> autores, List<String> idiomas, Integer downloadCount) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.downloadCount = downloadCount;
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        String nomesAutores = autores.stream()
                .map(Autor::getNome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");

        return "\nLivro: " + titulo + "\n" +
                "Autor: " + nomesAutores + "\n" +
                "Idioma: " + idiomas + "\n" +
                "Downloads: " + downloadCount + "\n";
    }

}
