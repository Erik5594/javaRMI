package br.com.diego.financas.controlador;

import br.com.diego.financas.util.Consts;
import br.com.diego.financas.util.Util;
import br.com.diego.financas.model.Server;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.File;

@ManagedBean
@ViewScoped
public class ServerControler {
    private Server servidor = new Server();
    private boolean criar;
    private boolean ok;

    public void lerProperties(){
        try {
            File diretorio = new File(Consts.DIR_PROPIEDADES);
            if(diretorio.exists()){
                servidor = Util.pegarDadosServidor();
                if(servidor != null && servidor.getIp() != null
                        && !"".equals(servidor.getIp()) && servidor.getPorta() > 999){
                    ok = true;
                }
            }else{
                criar = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String criarProperties(){
        try {
            File diretorio = new File(Consts.DIR_PROPIEDADES);
            diretorio.mkdirs();
            Util.criarArquivoConfig(diretorio, servidor);
            return "menu.xhtml";
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public Server getServidor() {
        return servidor;
    }

    public void setServidor(Server servidor) {
        this.servidor = servidor;
    }

    public boolean isCriar() {
        return criar;
    }

    public void setCriar(boolean criar) {
        this.criar = criar;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
