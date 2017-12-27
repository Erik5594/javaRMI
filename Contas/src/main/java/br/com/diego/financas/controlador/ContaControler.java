package br.com.diego.financas.controlador;

import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.client.TransacaoClient;
import br.com.diego.financas.servico.Transacao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.rmi.RemoteException;

@ManagedBean
@ViewScoped
public class ContaControler {
    private ContaDto conta = new ContaDto();
    private Transacao transacao = new TransacaoClient();

    public void salvar(){
        if(validacoes(this.conta)){
            try {
                if (transacao.criarConta(conta)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Criar Conta: Conta Criada!", ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Criar Conta: Houve uma falhar ao tentar criar Conta. Por favor tente mais tarde!", ""));
                }
            }catch (RemoteException e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Criar Conta: "+e.getMessage(), ""));
            }
        }
    }

    private boolean validacoes(ContaDto conta){
        boolean retorno = true;
        if(conta == null){
            retorno = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Criar Conta: Nenhum dados da Conta preenchido!", ""));
        }else{
            if(conta.getTitular() == null || "".equals(conta.getTitular())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Criar Conta: Nome Titular não foi preenchido!", ""));
            }

            if(conta.getAgencia() == null || "".equals(conta.getAgencia())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Criar Conta: Número da Agência não foi preenchido!", ""));
            }

            if(conta.getBanco() == null || "".equals(conta.getBanco())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Criar Conta: Banco não foi preenchido!", ""));
            }

            if(conta.getNumero() == null || "".equals(conta.getNumero())){
                retorno = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Criar Conta: Número do Banco não foi preenchido!", ""));
            }
        }

        return retorno;
    }

    public ContaDto getConta() {
        return conta;
    }

    public void setConta(ContaDto conta) {
        this.conta = conta;
    }
}
