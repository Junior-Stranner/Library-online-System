package com.br.biblioteca.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.biblioteca.Model.Livro;
import com.br.biblioteca.Repository.LivroRepository;

import jakarta.transaction.Transactional;

@Controller
public class LivroController {

    List<Livro> livros = new ArrayList<>();

    @Autowired
    LivroRepository repository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String cadastro(Livro livro) {
        repository.save(livro);
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("livros");
        livros = (ArrayList<Livro>) repository.findAll();
        mv.addObject("livros", livros);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/lista";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("home");
        Livro livro = new Livro();
        livro = repository.findById(id).get();
        mv.addObject("livro", livro);
        return mv;
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<Object> reservar(Livro livro){
        livro.reservar();
        repository.save(livro);
        return ResponseEntity.noContent().build();

    }
}
