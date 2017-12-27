package br.com.diego.financas.client;

import br.com.diego.financas.util.Util;
import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.dto.MovimentacaoDto;
import br.com.diego.financas.model.Server;
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
