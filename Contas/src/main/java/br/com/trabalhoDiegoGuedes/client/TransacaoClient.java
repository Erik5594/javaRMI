package br.com.trabalhoDiegoGuedes.client;

import br.com.trabalhoDiegoGuedes.Dto.ContaDto;
import br.com.trabalhoDiegoGuedes.Dto.MovimentacaoDto;
import br.com.trabalhoDiegoGuedes.model.Server;
import br.com.trabalhoDiegoGuedes.servico.Transacao;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TransacaoClient {

    private Server servidor;

    public TransacaoClient(){
        pegarServidor();
    }

    private void pegarServidor(){

    }

    public boolean criarConta(ContaDto conta) throws Exception{
        Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
        Transacao obj = (Transacao) registry.lookup("Servidor");
        return obj.criarConta(conta);
    }

    public ContaDto buscarConta(ContaDto conta) throws Exception{
        Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
        Transacao obj = (Transacao) registry.lookup("Servidor");
        return obj.consultarConta(conta);
    }

    public boolean criarMovimentacao(MovimentacaoDto movimentacao) throws Exception{
        Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
        Transacao obj = (Transacao) registry.lookup("Servidor");
        return obj.criarMovimentacao(movimentacao);
    }
}
