package br.com.diego.financas.server;

import br.com.diego.financas.dao.ContaDao;
import br.com.diego.financas.dao.MovimentacaoDao;
import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.dto.MovimentacaoDto;
import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.servico.Transacao;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TransacaoServer extends UnicastRemoteObject implements Transacao{

    public TransacaoServer() throws RemoteException{
        super();
    }

    public boolean criarConta(ContaDto contaDto) throws RemoteException {
        Conta conta = new Conta();
        conta.setTitular(contaDto.getTitular());
        conta.setNumero(contaDto.getNumero());
        conta.setBanco(contaDto.getBanco());
        conta.setAgencia(contaDto.getAgencia());

        ContaDao dao = new ContaDao();
        return dao.criarConta(conta);
    }

    public ContaDto consultarConta(ContaDto contaDto) throws RemoteException {
        Conta conta = new Conta();
        conta.setTitular(contaDto.getTitular());
        conta.setNumero(contaDto.getNumero());
        conta.setBanco(contaDto.getBanco());
        conta.setAgencia(contaDto.getAgencia());

        ContaDao dao = new ContaDao();
        return dao.consultarConta(conta);
    }

    public boolean criarMovimentacao(MovimentacaoDto movimentacaoDto) throws RemoteException {
        ContaDto contaDto = movimentacaoDto.getConta();
        Conta conta = new Conta();
        conta.setAgencia(contaDto.getAgencia());
        conta.setBanco(contaDto.getBanco());
        conta.setNumero(contaDto.getNumero());
        conta.setTitular(contaDto.getTitular());

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setConta(conta);
        movimentacao.setDataMovimentacao(movimentacaoDto.getDataMovimentacao());
        movimentacao.setDescricao(movimentacaoDto.getDescricao());
        movimentacao.setTipo(movimentacaoDto.getTipo());
        movimentacao.setValor(movimentacaoDto.getValor());

        MovimentacaoDao dao = new MovimentacaoDao();
        return dao.criarMovimentacao(movimentacao);
    }

    public static void main(String args[]){
        try{
            TransacaoServer obj = new TransacaoServer();

            Registry registry = LocateRegistry.createRegistry(2001);
            registry.rebind("Servidor", obj);
        } catch (RemoteException e){
            System.out.println("Ocorreu um erro: "+ e.getMessage());
        }
    }
}
