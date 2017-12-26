package br.com.trabalhoDiegoGuedes.Util;

import br.com.trabalhoDiegoGuedes.Dto.ContaDto;
import br.com.trabalhoDiegoGuedes.Dto.MovimentacaoDto;

public class UtilMocks {

    public static ContaDto consultarConta(ContaDto conta){
        ContaDto contaDto = new ContaDto();
        contaDto.setBanco("Santader");
        contaDto.setTitular("Erik Queiroz de Oliveira");
        contaDto.setAgencia(conta.getAgencia());
        contaDto.setNumero(conta.getNumero());
        return contaDto;
    }

    public static boolean criarConta(ContaDto contaDto){
        return true;
    }

    public static boolean movimentarConta(MovimentacaoDto movimentacaoDto){
        return true;
    }
}
