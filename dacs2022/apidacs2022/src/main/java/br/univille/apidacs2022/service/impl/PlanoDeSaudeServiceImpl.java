package br.univille.apidacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.PlanoDeSaudeService;
import br.univille.coredacs2022.entity.PlanoDeSaude;
import br.univille.coredacs2022.repository.PlanoDeSaudeRepository;

@Service
public class PlanoDeSaudeServiceImpl 
    implements PlanoDeSaudeService{
    
    @Autowired
    private PlanoDeSaudeRepository repository;
    
    @Override
    public List<PlanoDeSaude> getAll() {
        return repository.findAll();
    }

    @Override
    public PlanoDeSaude save(PlanoDeSaude planodesaude) {
        repository.save(planodesaude);
        return planodesaude;
    }

    @Override
    public PlanoDeSaude findById(long id) {
        Optional<PlanoDeSaude> planodesaude = repository.findById(id);
        if(planodesaude.isPresent()){
            return planodesaude.get();
        }
        return null;
    }
    
    @Override
    public PlanoDeSaude delete(long id) {
        Optional<PlanoDeSaude> planodesaude = repository.findById(id);
        if(planodesaude.isPresent()){
            var pac = planodesaude.get();
            repository.delete(pac);
            return pac;
        }
        return null;
    }

    @Override
    public List<PlanoDeSaude> getByName(String nome) {
        return repository.findByNomeIgnoreCaseContaining(nome);
    }

}