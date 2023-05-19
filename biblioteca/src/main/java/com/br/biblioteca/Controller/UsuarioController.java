package com.br.biblioteca.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.biblioteca.Model.Usuario;
import com.br.biblioteca.Repository.UsuarioRepository;


@Controller
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<>();

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String lista(Usuario usuario) {
        repository.save(usuario);
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("lista");
        usuarios = (ArrayList<Usuario>) repository.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/exluir{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);

        return "redirect:/lista";

    }

    @GetMapping("/editar{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("lista");
        Usuario usuario = new Usuario();
        usuario = repository.findById(id).get();
        mv.addObject("usuario", usuario);
        return mv;

    }

  
}
