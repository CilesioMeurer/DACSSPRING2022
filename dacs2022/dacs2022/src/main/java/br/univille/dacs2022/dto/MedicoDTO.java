package br.univille.dacs2022.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class MedicoDTO {
    private long id;
    @NotBlank(message = "O campo não pode ser deixado em branco")
    @NotNull
    private String nome;
    private String CRM;
    private List<ProcedimentoDTO> listaProcedimentos = new ArrayList<>();
    private long procedimentoId;
    private ProcedimentoDTO procedimento;

    public ProcedimentoDTO getProcedimento() {
        return procedimento;
    }
    public void setProcedimento(ProcedimentoDTO procedimento) {
        this.procedimento = procedimento;
    }
    public long getProcedimentoId() {
        return procedimentoId;
    }
    public void setProcedimentoId(long procedimentoId) {
        this.procedimentoId = procedimentoId;
    }
    public List<ProcedimentoDTO> getListaProcedimentos() {
        return listaProcedimentos;
    }
    public void setListaProcedimentos(List<ProcedimentoDTO> listaProcedimentos) {
        this.listaProcedimentos = listaProcedimentos;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCRM() {
        return CRM;
    }
    public void setCRM(String cRM) {
        CRM = cRM;
    }

    

}
