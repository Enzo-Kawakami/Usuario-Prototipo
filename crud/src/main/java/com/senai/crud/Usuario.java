package com.senai.crud;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // Avisa ao Spring que isso é uma tabela do banco de dados
@Table(name = "usuarios")
public class Usuario {

    @Id // Define o ID como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID automaticamente (1, 2, 3...)
    private Long id;

    private String nome;
    private int idade;
    private LocalDate dtnasc;

    // Construtor padrão vazio (OBRIGATÓRIO para o JPA funcionar)
    public Usuario() {
    }

    // Getters e Setters (Métodos para ler e alterar os dados)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public LocalDate getDtnasc() { return dtnasc; }
    public void setDtnasc(LocalDate dtnasc) { this.dtnasc = dtnasc; }
}