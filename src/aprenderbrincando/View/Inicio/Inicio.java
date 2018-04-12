package aprenderbrincando.View.Inicio;

/**
 * @author Vinicius Berto
 */

import aprenderbrincando.*;
import static aprenderbrincando.Controller.ControllerExecucao.*;
import static aprenderbrincando.Recursos.obterCursor;
import static aprenderbrincando.Recursos.obterImagem;
import aprenderbrincando.View.Telas;
import java.awt.BorderLayout;
import java.awt.Image;

public final class Inicio extends Telas {

//    private PnlSuperiorInicio pnlSuperior;
    private PnlCentroInicio pnlCentro;

    public Inicio() {
        this.setTitle("Quimicamente");
        setCursor(obterCursor("Cursor"));
        alteraImagemFundo(obterImagem("Fundo-Inicio", Config.TAM_TELA, Image.SCALE_SMOOTH));
        addKeyListener(getTa());
        inicializarComponentes();
        requestFocus();
    }

    public void inicializarComponentes() {
        pnlCentro = new PnlCentroInicio();
        adicionar(pnlCentro, BorderLayout.CENTER);
    }

//    public PnlSuperiorInicio getSuperior() {
//        return pnlSuperior;
//    }

    public PnlCentroInicio getCentro() {
        return pnlCentro;
    }
}
