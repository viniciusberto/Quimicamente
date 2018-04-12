package aprenderbrincando.Controller;

/**
 * @author Vinicius Berto
 */

import java.util.ArrayList;
import java.util.List;

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
