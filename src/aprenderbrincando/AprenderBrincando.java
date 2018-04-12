package aprenderbrincando;

import aprenderbrincando.Controller.ControllerExecucao;
import aprenderbrincando.Controller.TratadoraAction;
import aprenderbrincando.View.Manipuladores;
import aprenderbrincando.View.MenuPausa.MenuPausa;
import aprenderbrincando.View.Splash;

/**
 * Classe responsável por inicializar a aplicação
 * 
 * @author Vinícius Berto
 */
public class AprenderBrincando {

    private static Splash s;

    public static void main(String[] args) {
        ControllerExecucao ctrl = new ControllerExecucao();
        ctrl.setTa(new TratadoraAction(ctrl));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                s = new Splash();

            }
        });
        Manipuladores.aguardar(3000);
        s.setVisible(false);
        s = null;
        ctrl.setTa(new TratadoraAction(ctrl));
        ctrl.carregarInicio();
       // Tips.exibirDica(ctrl.getTelaInicial(), ctrl.getTelaInicial().getCentro().getBtnIniciar());
    }
}
