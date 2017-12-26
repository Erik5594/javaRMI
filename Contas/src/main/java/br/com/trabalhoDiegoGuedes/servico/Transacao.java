package br.com.trabalhoDiegoGuedes.servico;

import br.com.trabalhoDiegoGuedes.Dto.ContaDto;
import br.com.trabalhoDiegoGuedes.Dto.MovimentacaoDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Transacao extends Remote{

    boolean criarConta(ContaDto contaDto) throws RemoteException;

    ContaDto consultarConta(ContaDto contaDto) throws RemoteException;

    boolean criarMovimentacao(MovimentacaoDto movimentacaoDto) throws RemoteException;
}
