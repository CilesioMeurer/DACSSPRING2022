package br.univille.dacs2022.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dacs2022.dto.MedicoDTO;
import br.univille.dacs2022.entity.Procedimento;
import br.univille.dacs2022.service.MedicoService;
import br.univille.dacs2022.service.ProcedimentoService;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService service;
    @Autowired
    private ProcedimentoService procedimentoService;

    @GetMapping
    public ModelAndView index(){
        List<MedicoDTO> listaMedicos =
            service.getAll();

        return new ModelAndView("medico/index",
                            "listaMedicos",listaMedicos);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var medico = new MedicoDTO();
        var listaProcedimentos = procedimentoService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("medico",medico);
        dados.put("listaProcedimentos",listaProcedimentos);
        return new ModelAndView("medico/form",dados);
    }

    @PostMapping(params="save")
    public ModelAndView save(@Valid @ModelAttribute ("medico")
                            MedicoDTO medico,
                            BindingResult bindingResult){
            var procedimentoDTO = procedimentoService.findById(medico.getProcedimentoId());
            medico.setProcedimento(procedimentoDTO);
            if(bindingResult.hasErrors()){
                var listaProcedimentos = procedimentoService.getAll();
                HashMap<String,Object> dados = new HashMap<>();
                dados.put("listaProcedimentos",listaProcedimentos);
                return new ModelAndView("medico/form",dados);
            }
            service.save(medico);
            return new ModelAndView("redirect:/medico");
        }

    @PostMapping(params="incprocedimento")
    public ModelAndView incluirPlano(@Valid @ModelAttribute("medico") 
                                MedicoDTO medico,
                                BindingResult bindingResult){
        var idProcedimentoSelect = medico.getProcedimentoId();
        var ProcedimentoSelect = procedimentoService.getById(idProcedimentoSelect);
        medico.getListaProcedimentos().add(ProcedimentoSelect);

        var listaProcedimentos = procedimentoService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("medico",medico);
        dados.put("listaProcedimentos",listaProcedimentos);

        return new ModelAndView("paciente/form",dados);
    }
    @PostMapping(params="removeitem")
    public ModelAndView removerPlano(@Valid @ModelAttribute("medico") 
                                MedicoDTO medico,
                                @RequestParam(name = "removeitem") int index,
                                BindingResult bindingResult){
        medico.getListaProcedimentos().remove(index);

        var listaProcedimentos = procedimentoService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("medico",medico);
        dados.put("listaProcedimentos",listaProcedimentos);

        return new ModelAndView("medico/form",dados);
    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        MedicoDTO medico = service.findById(id);
        var listaProcedimentos = procedimentoService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("medico",medico);
        dados.put("listaProcedimentos",listaProcedimentos);
        return new ModelAndView("medico/form",dados);
    }
    @GetMapping(path = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        service.delete(id);
        return new ModelAndView("redirect:/medico");
    }

}