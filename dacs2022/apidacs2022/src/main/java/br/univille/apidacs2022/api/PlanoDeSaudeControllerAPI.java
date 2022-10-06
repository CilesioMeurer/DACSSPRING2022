package br.univille.apidacs2022.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.univille.apidacs2022.service.PlanoDeSaudeService;
import br.univille.coredacs2022.entity.PlanoDeSaude;
import br.univille.coredacs2022.repository.PlanoDeSaudeRepository;

@RestController
@RequestMapping("/api/v1/planodesaude")
public class PlanoDeSaudeControllerAPI {
    
    @Autowired
    private PlanoDeSaudeService service;

    @GetMapping
    public ResponseEntity<List<PlanoDeSaude>> getAll(){
        var listaPlanoDeSaudes = service.getAll();
        return new 
            ResponseEntity<List<PlanoDeSaude>>
                (listaPlanoDeSaudes,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanoDeSaude> 
            getById(@PathVariable("id") long id){

        var planodesaude = service.findById(id);
        return new ResponseEntity<PlanoDeSaude>
                (planodesaude,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanoDeSaude> 
        insertPlanoDeSaude(@RequestBody PlanoDeSaude planodesaude){
        if(planodesaude.getId() == 0){
            service.save(planodesaude);
            return new ResponseEntity<PlanoDeSaude>(planodesaude,HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PlanoDeSaude>> 
                    getByNome(@PathVariable("nome") String nome){
        var listaPlanoDeSaudes = service.getByName(nome);
        return new 
            ResponseEntity<List<PlanoDeSaude>>
                (listaPlanoDeSaudes,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDeSaude>
            update(@PathVariable("id") long id, 
                   @RequestBody PlanoDeSaude planodesaude){
        
        var planodesaudeAntigo = service.findById(id);
        if(planodesaudeAntigo == null){
            return ResponseEntity.notFound().build();
        }

        planodesaudeAntigo.setNome(planodesaude.getNome());
        // planodesaudeAntigo.setSexo(planodesaude.getSexo());
        // planodesaudeAntigo.setDataNascimento(planodesaude.getDataNascimento());
        // planodesaudeAntigo.setCidade(planodesaude.getCidade());
        // planodesaudeAntigo.setListaPlanos(planodesaude.getListaPlanos());
        
        service.save(planodesaudeAntigo);
        
        return new ResponseEntity<PlanoDeSaude>(planodesaudeAntigo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanoDeSaude>
            delete(@PathVariable("id") long id){
        
        var planodesaudeAntigo = service.findById(id);
        if(planodesaudeAntigo == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(planodesaudeAntigo.getId());
        
        return new ResponseEntity<PlanoDeSaude>(planodesaudeAntigo,HttpStatus.OK);
    }
    
}