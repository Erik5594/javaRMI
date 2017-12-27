package br.com.diego.financas.dao;

import br.com.diego.financas.dto.ContaDto;
import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ContaDao {

    public boolean criarConta(Conta conta){
        EntityManager manager = new JPAUtil().getEM();

        manager.getTransaction().begin();
        manager.persist(conta);
        return true;
    }

    public ContaDto consultarConta(Conta conta){
        EntityManager manager = new JPAUtil().getEM();
        manager.getTransaction().begin();

        Query query = manager.createQuery("select c from Conta c where c.numero =:pnumero and c.agencia = :pagencia");
        query.setParameter("pnumero", conta.getNumero());
        query.setParameter("pagencia", conta.getAgencia());
        List<Conta> lista = query.getResultList();

        ContaDto contaDto = new ContaDto();
        for (Conta cont : lista) {
            contaDto.setAgencia(cont.getAgencia());
            contaDto.setBanco(cont.getBanco());
            contaDto.setNumero(cont.getNumero());
            contaDto.setTitular(cont.getTitular());
        }

        return contaDto;
    }

}
