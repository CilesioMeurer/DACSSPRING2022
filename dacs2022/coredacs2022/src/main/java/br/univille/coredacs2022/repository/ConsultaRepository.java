package br.univille.coredacs2022.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.coredacs2022.entity.Consulta;

@Repository
public interface ConsultaRepository
    extends JpaRepository<Consulta,Long>{
        List<Consulta> findByData(@Param("data") Date data);
    
}
