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

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @ElementCollection
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    private List<String> idiomas;

    private String copyright;
    private Integer downloadCount;
    private String mediaType;

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

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        String nomesAutores = autores.stream()
                .map(Autor::getNome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");

        return "Livro: " + titulo +
                ", Autor: " + nomesAutores +
                ", Idioma: " + idiomas +
                ", Copyright: " + copyright +
                ", Downloads: " + downloadCount +
                ", Tipo: " + mediaType;
    }
}
