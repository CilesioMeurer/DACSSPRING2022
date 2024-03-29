package br.univille.dacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2022.dto.PacienteDTO;
import br.univille.coredacs2022.entity.Paciente;
import br.univille.dacs2022.mapper.PacienteMapper;
import br.univille.coredacs2022.repository.PacienteRepository;
import br.univille.dacs2022.service.PacienteService;

@Service
public class PacienteServiceImpl 
    implements PacienteService{
    
    @Autowired
    private PacienteRepository repository;
    
    private PacienteMapper mapper 
        = Mappers.getMapper(PacienteMapper.class);

    
    @Override
    public List<PacienteDTO> getAll() {
        return mapper.mapListPaciente(repository.findAll());
    }


    @Override
    public PacienteDTO save(PacienteDTO paciente) {
        Paciente pacienteEntity = mapper.mapPacienteDTO(paciente);
        pacienteEntity = repository.save(pacienteEntity);
        return mapper.mapPaciente(pacienteEntity);
    }


    @Override
    public PacienteDTO findById(long id) {
        Optional<Paciente> pacienteEntity = 
            repository.findById(id);

        if (pacienteEntity.isPresent()){
            return mapper.mapPaciente(pacienteEntity.get());
        }

        return new PacienteDTO();
    }


    @Override
    public PacienteDTO delete(long id) {
        Optional<Paciente> pacienteEntity = 
            repository.findById(id);

        if (pacienteEntity.isPresent()){
            Paciente paciente = pacienteEntity.get();
            repository.delete(paciente);
            return mapper.mapPaciente(paciente);
        }
        return null;
    }
    
}