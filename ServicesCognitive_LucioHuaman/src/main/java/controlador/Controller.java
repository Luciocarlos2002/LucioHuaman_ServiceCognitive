package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import dao.Services;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Atributos;

@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    private String url;
    boolean bt= false; 
    private Services dao = new Services();
    Atributos atributo = new Atributos();

    public void analizar() throws Exception {
        try {
            atributo = dao.ObtenerURL(url);
            atributo.setUrl(url);
            bt = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Analizando rostro", "Completo..."));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "URL", "Incorrecto"));
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            atributo = new Atributos();
            bt = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Limpiando", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Services getDao() {
        return dao;
    }

    public void setDao(Services dao) {
        this.dao = dao;
    }

    public Atributos getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributos atributo) {
        this.atributo = atributo;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }
    
}
