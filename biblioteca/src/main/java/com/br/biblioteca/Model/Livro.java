package com.br.biblioteca.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String genero;
    private int paginas;
    private Date anoPublicacao;
    private boolean status;

    public Livro(int id, String titulo, String genero, int paginas, Date anoPublicacao, boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.paginas = paginas;
        this.anoPublicacao = anoPublicacao;
        this.status = status;
    }

    public Livro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Date getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Date anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean setStatus(boolean status) {
        return this.status = status;
    }

    public void reservar() {
    }

}
