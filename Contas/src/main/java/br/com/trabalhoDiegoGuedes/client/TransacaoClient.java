package br.com.trabalhoDiegoGuedes.client;

import br.com.trabalhoDiegoGuedes.Util.Util;
import br.com.trabalhoDiegoGuedes.Dto.ContaDto;
import br.com.trabalhoDiegoGuedes.Dto.MovimentacaoDto;
import br.com.trabalhoDiegoGuedes.model.Server;
import br.com.diego.financas.servico.Transacao;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TransacaoClient implements Transacao{

    private Server servidor;

    public TransacaoClient(){
        pegarServidor();
    }

    private void pegarServidor(){
        try{
            servidor = Util.pegarDadosServidor();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean criarConta(ContaDto conta) throws RemoteException{
        try{
            Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
            Transacao obj = (Transacao) registry.lookup("Servidor");
        return obj.criarConta(conta);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage());
        }

    }

    public ContaDto consultarConta(ContaDto conta) throws RemoteException{
        try{
            Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
            Transacao obj = (Transacao) registry.lookup("Servidor");
            return obj.consultarConta(conta);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public boolean criarMovimentacao(MovimentacaoDto movimentacao) throws RemoteException{
        try{
            Registry registry = LocateRegistry.getRegistry(servidor.getIp(), servidor.getPorta());
            Transacao obj = (Transacao) registry.lookup("Servidor");
            return obj.criarMovimentacao(movimentacao);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
