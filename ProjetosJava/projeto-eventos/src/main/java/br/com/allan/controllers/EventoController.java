package br.com.allan.controllers;

import br.com.allan.models.Convidado;
import br.com.allan.models.Evento;
import br.com.allan.repositories.ConvidadoRepository;
import br.com.allan.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("evento/index");
        Iterable<Evento> eventos = eventoRepository.findAll();
        modelAndView.addObject("eventos", eventos);
        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "evento/formEvento";
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Evento evento) {
        ModelAndView modelAndView = new ModelAndView("/evento/formEvento");
        eventoRepository.save(evento);
        modelAndView.addObject("mensagem", "Evento cadastrado com sucesso.");
        return modelAndView;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView exibir(@PathVariable long codigo) {
        Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("evento/detalhesEvento");
        modelAndView.addObject("evento", evento);
        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        modelAndView.addObject("convidados", convidados);
        return modelAndView;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String cadastrarConvidado(@PathVariable long codigo, Convidado convidado,
                                     BindingResult result) {
//        if (result.hasErrors()) {
//            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
//            return "redirect:/{codigo}";
//        }
        Evento evento = eventoRepository.findByCodigo(codigo);
        convidado.setEvento(evento);
        convidadoRepository.save(convidado);
//        attributes.addFlashAttribute("mensagem", "Convidaddo adicionado com Sucesso!");
        return "redirect:/eventos/{codigo}";
    }

    @RequestMapping("/deletar/{codigo}")
    public String deletar(@PathVariable long codigo) {
        Evento evento = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(evento);
        return "redirect:/eventos";

    }

    @RequestMapping("/convidado/deletar/{rg}")
    public String deletarConvidado(@PathVariable String rg) {
        Convidado convidado = convidadoRepository.findByRg(rg);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong = evento.getCodigo();
        String codigo = "" + codigoLong;
        return "redirect:/eventos/" + codigo;


    }

}