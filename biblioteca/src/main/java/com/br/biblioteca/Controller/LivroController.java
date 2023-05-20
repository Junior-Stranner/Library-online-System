package com.br.biblioteca.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.br.biblioteca.Model.Livro;
import com.br.biblioteca.Repository.LivroRepository;

@Controller
public class LivroController {

    List<Livro> livros = new ArrayList<>();

    @Autowired
    LivroRepository repository;

    @GetMapping("/homeLivro")
    public String homeLivro() {
        return "homeLivro";
    }

    @PostMapping("/homeLivro")
    public String cadastro(Livro livro) {
        repository.save(livro);
        return "redirect:/listaLivro";
    }

    @GetMapping("/listaLivro")
    public ModelAndView listaLivro() {
        ModelAndView mv = new ModelAndView("listaLivro");
        ArrayList<Livro> livros = new ArrayList<>();
        livros = (ArrayList<Livro>) repository.findAll();
        mv.addObject("livros", livros);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/listaLivro";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("homeLivro");
        Livro livro = new Livro();
        livro = repository.findById(id).get();
        mv.addObject("livro", livro);
        return mv;
    }

    @PatchMapping
    @Transactional
    public boolean reservar(Livro livro) {
        if (livro.getId() > 0 && livro.getStatus()) {
            livro.reservar();
            repository.save(livro);
            return livro.setStatus(true);
        } else
            return livro.setStatus(false);
    }
}
