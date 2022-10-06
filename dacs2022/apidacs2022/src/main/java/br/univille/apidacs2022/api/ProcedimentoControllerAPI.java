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

import br.univille.apidacs2022.service.ProcedimentoService;
import br.univille.coredacs2022.entity.Procedimento;
import br.univille.coredacs2022.repository.ProcedimentoRepository;

@RestController
@RequestMapping("/api/v1/procedimento")
public class ProcedimentoControllerAPI {
    
    @Autowired
    private ProcedimentoService service;

    @GetMapping
    public ResponseEntity<List<Procedimento>> getAll(){
        var listaProcedimentos = service.getAll();
        return new 
            ResponseEntity<List<Procedimento>>
                (listaProcedimentos,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Procedimento> 
            getById(@PathVariable("id") long id){

        var procedimento = service.findById(id);
        return new ResponseEntity<Procedimento>
                (procedimento,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Procedimento> 
        insertProcedimento(@RequestBody Procedimento procedimento){
        if(procedimento.getId() == 0){
            service.save(procedimento);
            return new ResponseEntity<Procedimento>(procedimento,HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Procedimento>> 
                    getByDescricao(@PathVariable("descricao") String descricao){
        var listaProcedimentos = service.getByName(descricao);
        return new 
            ResponseEntity<List<Procedimento>>
                (listaProcedimentos,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimento>
            update(@PathVariable("id") long id, 
                   @RequestBody Procedimento procedimento){
        
        var procedimentoAntigo = service.findById(id);
        if(procedimentoAntigo == null){
            return ResponseEntity.notFound().build();
        }

        procedimentoAntigo.setDescricao(procedimento.getDescricao());
        // procedimentoAntigo.setSexo(procedimento.getSexo());
        // procedimentoAntigo.setDataNascimento(procedimento.getDataNascimento());
        // procedimentoAntigo.setCidade(procedimento.getCidade());
        // procedimentoAntigo.setListaPlanos(procedimento.getListaPlanos());
        
        service.save(procedimentoAntigo);
        
        return new ResponseEntity<Procedimento>(procedimentoAntigo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Procedimento>
            delete(@PathVariable("id") long id){
        
        var procedimentoAntigo = service.findById(id);
        if(procedimentoAntigo == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(procedimentoAntigo.getId());
        
        return new ResponseEntity<Procedimento>(procedimentoAntigo,HttpStatus.OK);
    }
    
}