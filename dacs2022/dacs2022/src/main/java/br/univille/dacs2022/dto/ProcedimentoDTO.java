package br.univille.dacs2022.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProcedimentoDTO{
    private long id;
    @NotBlank(message = "O campo não pode ser deixado em branco")
    @NotNull
    @Column(length = 1000)
    private String descricao;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String toString(){
        return getDescricao();
    }
    
}
