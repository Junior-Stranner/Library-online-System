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

    @GetMapping("/loginUsuario")
    public String login() {
        return "loginUsuario";
    }

    @PostMapping("/verificaLogin")
    public String verifica(Usuario usuario) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) repository.findAll();
        for (Usuario usuario1 : usuarios) {
            if (usuario1.getSenha().equalsIgnoreCase(usuario.getSenha())
                    && usuario1.getEmail().equalsIgnoreCase(usuario.getEmail()))
                ;
        }
        return "redirect:/listaLivro";
    }

    @PostMapping("/listaUsuario")
    public String listaUsuario(Usuario usuario) {
        repository.save(usuario);
        return "redirect:/listaUsuario";
    }

    @GetMapping("/listaUsuario")
    public ModelAndView listaUsuario() {
        ModelAndView mv = new ModelAndView("listaUsuario");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) repository.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/excluir{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);

        return "redirect:/listaUsuario";

    }

    @GetMapping("/editar{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("login");
        Usuario usuario = new Usuario();
        usuario = repository.findById(id).get();
        mv.addObject("usuario", usuario);
        return mv;

    }
}
