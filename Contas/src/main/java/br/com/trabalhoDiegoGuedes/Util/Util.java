package br.com.trabalhoDiegoGuedes.Util;

import br.com.trabalhoDiegoGuedes.model.Server;

import java.io.*;
import java.util.Properties;

public class Util {

    public static Server pegarDadosServidor() throws Exception{
        Server server = new Server();
        Properties prop = new Properties();
        File diretorio = new File(Consts.DIR_PROPIEDADES);
        if(diretorio.exists()){
            File arquivo = new File(diretorio.getPath() + Consts.NOME_ARQ_CONFIG_SERVIDOR);
            if(arquivo.exists()){
                FileInputStream file = new FileInputStream(arquivo);
                prop.load(file);
                server.setIp(prop.getProperty(Consts.ARQ_URL_SERVIDOR));
                int porta = 0;
                try {
                    porta = Integer.parseInt(prop.getProperty(Consts.ARQ_PORTA));
                }catch (NumberFormatException e){
                    porta = 0;
                }
                server.setPorta(porta);
            }else{
                criarArquivoConfig(diretorio, server);
            }
        }else{
            if(diretorio.mkdirs()){
                criarArquivoConfig(diretorio, server);
            }
        }
        return server;
    }

    public static void criarArquivoConfig(File diretorio, Server servidor) throws IOException {
        FileWriter arq = new FileWriter(diretorio.getPath() + Consts.NOME_ARQ_CONFIG_SERVIDOR, true);
        PrintWriter gravarArquivo = new PrintWriter(arq);
        gravarArquivo.println(Consts.ARQ_URL_SERVIDOR + " = "+servidor.getIp());
        gravarArquivo.println(Consts.ARQ_PORTA + " = "+String.valueOf(servidor.getPorta()));
        gravarArquivo.close();
        arq.close();
    }

}
