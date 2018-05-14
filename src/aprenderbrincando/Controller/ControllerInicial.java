package aprenderbrincando.Controller;

import aprenderbrincando.Config;
import aprenderbrincando.View.Inicio.Inicio;
import aprenderbrincando.View.Manipuladores;
import aprenderbrincando.View.Splash;

/**
 *
 * @author IFMS
 */
public class ControllerInicial {

    private static Splash splash;
    private Inicio telaInicial;

    public static void main(String[] args) {
        new Config();
        ControllerInicial ci = new ControllerInicial();
        ci.exibirSplash();
        ci.exibirTelaInicial();
    }

    public void exibirSplash() {
        splash = new Splash();
        splash.setVisible(true);
        Manipuladores.aguardar(3000);
        splash.setVisible(false);
        splash = null;
    }

    public void exibirTelaInicial() {
        setTelaInicial(new Inicio(this));
        this.telaInicial.setVisible(true);
    }

    public Inicio getTelaInicial() {
        return telaInicial;
    }

    public void setTelaInicial(Inicio telaInicial) {
        this.telaInicial = telaInicial;
    }

}
