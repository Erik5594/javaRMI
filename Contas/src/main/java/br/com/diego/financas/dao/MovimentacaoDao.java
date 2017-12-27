package br.com.diego.financas.dao;

import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.util.JPAUtil;

import javax.persistence.EntityManager;

public class MovimentacaoDao {

    public boolean criarMovimentacao(Movimentacao movimentacao){
        EntityManager manager = new JPAUtil().getEM();

        manager.getTransaction().begin();
        manager.persist(movimentacao);
        return true;
    }
}
