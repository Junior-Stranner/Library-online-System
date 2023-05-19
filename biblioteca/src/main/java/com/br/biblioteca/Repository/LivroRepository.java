package com.br.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.biblioteca.Model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
