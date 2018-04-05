/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aprenderbrincando.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Observado {
    private List<Observador> listaObservadores;

    public Observado(Observador ob) {
        this.listaObservadores = new ArrayList<>();
        listaObservadores.add(ob);
    }
    
   public void addObservador(Observador ob){
       this.listaObservadores.add(ob);
   };

    public List<Observador> getListaObservadores() {
        return listaObservadores;
    }
    
    public void notificar(){
        for (Observador observador : listaObservadores) {
            observador.atualizar(this);
        }        
    };
}
