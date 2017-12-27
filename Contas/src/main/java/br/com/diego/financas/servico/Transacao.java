package br.com.diego.financas.servico;

import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.dto.MovimentacaoDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Transacao extends Remote{

    boolean criarConta(ContaDto contaDto) throws RemoteException;

    ContaDto consultarConta(ContaDto contaDto) throws RemoteException;

    boolean criarMovimentacao(MovimentacaoDto movimentacaoDto) throws RemoteException;
}
