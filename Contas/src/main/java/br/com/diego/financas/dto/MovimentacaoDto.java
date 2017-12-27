package br.com.diego.financas.dto;

import br.com.diego.financas.modelo.TipoTransacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MovimentacaoDto implements Serializable{
    private Integer id;
    private BigDecimal valor;
    private Date dataMovimentacao;
    private TipoTransacao tipo;

    private String descricao;
    private ContaDto conta;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContaDto getConta() {
        return conta;
    }

    public void setConta(ContaDto conta) {
        this.conta = conta;
    }
}
