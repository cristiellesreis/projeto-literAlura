# Challenge Literalura 📚

Aplicação em **Spring Boot + JPA + banco relacional** que permite buscar, salvar e listar livros e autores usando a API do Project Gutenberg e persistência local via repositório.

---

## 🔍 Funcionalidades

- Buscar livro pelo título (API do Project Gutenberg + salvar no banco)
- Listar livros registrados
- Listar autores registrados
- Listar autores vivos em um ano específico
- Listar livros por idioma (pt, en, es, fr)
- Exibir Top 10 livros mais baixados
- Encerrar a aplicação

---

## ⚙️ Tecnologias

- Java 17+  
- Spring Boot 
- Hibernate / Data JPA
- Banco de dados (PostgreSQL)  
- Maven  
- API: Project [Gutenberg](https://gutendex.com/)

---

## 🔧 Configuração
1. Clone o repositório  
```bash
git clone https://github.com/cristiellesreis/projeto-literAlura.git
cd projeto-literAlura
```
## 🔧 Configure o banco de dados

No arquivo `src/main/resources/application.properties`, ajuste para usar PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
🚀 Executando
```bash
./mvnw spring-boot:run

```
Abra o terminal e siga o menu interativo:

```
***** Literalura *****

Escolha o número de sua opção:

1- Buscar livro pelo título
2- Listar livros registrados
3- Listar autores registrados
4- Listar autores vivos em um determinado ano
5- Listar livros em um determinado idioma
6- Top 10 livros mais baixados

7 - Sair

```
📂 Estrutura
```
src/
 ├─ main/
 │   ├─ java/br/com/alura/literalura/
 │   │    ├─ modelos/       → Entidades JPA (Livro, Autor)
 │   │    ├─ dto/           → DTOs para transferência de dados
 │   │    ├─ repositorio/   → Repositórios Spring Data
 │   │    ├─ servico/       → Lógica de negócios (chamada à API, conversões)
 │   │    └─ principal/     → Classe de entrada e menu interativo
 │   └─ resources/          → Configurações (application.properties)

```
## 📚 Créditos

Este projeto foi proposto como desafio pelo programa:

- [Oracle Next Education](https://www.oracle.com/br/education/oracle-next-education/)
- [Alura - Cursos de tecnologia](https://www.alura.com.br/)

---

## 📄 Licença

Projeto de uso educacional, livre para estudos e modificações.

---

Desenvolvido com ☕ por Cristielle Reis


