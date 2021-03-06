package br.com.diego.financas.controlador;

import br.com.diego.financas.client.TransacaoClient;
import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.dto.MovimentacaoDto;
import br.com.diego.financas.modelo.TipoTransacao;
import br.com.diego.financas.servico.Transacao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;

@ManagedBean
@ViewScoped
public class MovimentacaoControler {

    private MovimentacaoDto movimentacaoDto = new MovimentacaoDto();
    private ContaDto contaDto = new ContaDto();
    private String tipoMovimentacao;
    boolean consulta = true;
    private Transacao transacao = new TransacaoClient();

    public MovimentacaoControler(){
        System.out.println("Construiu MovimentacaoControler");
    }

    public void buscarConta(){
        if(validacoesConta()){
            try {
                contaDto = transacao.consultarConta(contaDto);
                if (contaDto != null) {
                    movimentacaoDto.setConta(contaDto);
                    consulta = false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: "+e.getMessage(), ""));
            }
        }
    }

    private boolean validacoesConta(){
        boolean retorno = true;
        if(contaDto == null){
            retorno = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Não foi preenchido dados da conta!", ""));
        }else{
            if(contaDto.getAgencia() == null || "".equals(contaDto.getAgencia())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Não foi preenchido a Agência!", ""));
            }
            if(contaDto.getNumero() == null || "".equals(contaDto.getNumero())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Não foi preenchido o Número da Conta!", ""));
            }
        }
        return retorno;
    }

    public void movimentar(){
        completarMovimentacao();
        if(validacoes()) {
            try {
                if (transacao.criarMovimentacao(this.movimentacaoDto)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Movimentação: Movimentação realizada com sucesso!", ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Houve uma falha ao tentar gravar Movimentação. Por favor tente mais tarde!", ""));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: "+e.getMessage(), ""));
            }
        }
    }

    private void completarMovimentacao(){
        this.movimentacaoDto.setDataMovimentacao(new Date());
        this.movimentacaoDto.setTipo("0".equals(tipoMovimentacao) ? TipoTransacao.ENTRADA : "1".equals(tipoMovimentacao) ? TipoTransacao.SAIDA:null);
    }

    private boolean validacoes(){
        boolean retorno = true;
        if(movimentacaoDto == null){
            retorno = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Nenhum dados da Movimentação foi preenchido!", ""));
        }else{
            if(movimentacaoDto.getConta() == null){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: Nenhuma Conta selecionada!", ""));
            }

            if(movimentacaoDto.getDescricao() == null || "".equals(movimentacaoDto.getDescricao())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: É obrigátorio informar a descrição da movimentação!", ""));
            }

            if(movimentacaoDto.getTipo() == null){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: É obrigátorio informar o Tipo da Transação!", ""));
            }

            if(movimentacaoDto.getValor() == null || movimentacaoDto.getValor().compareTo(BigDecimal.ZERO) < 1){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Movimentação: O valor deve ser maior que 0!", ""));
            }
        }
        return  retorno;
    }

    public MovimentacaoDto getMovimentacaoDto() {
        return movimentacaoDto;
    }

    public void setMovimentacaoDto(MovimentacaoDto movimentacaoDto) {
        this.movimentacaoDto = movimentacaoDto;
    }

    public ContaDto getContaDto() {
        return contaDto;
    }

    public void setContaDto(ContaDto contaDto) {
        this.contaDto = contaDto;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }
}
