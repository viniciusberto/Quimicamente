package aprenderbrincando.View.Inicio;

import aprenderbrincando.*;
import static aprenderbrincando.Controller.ControllerExecucao.*;
import static aprenderbrincando.View.Manipuladores.*;
import aprenderbrincando.View.Telas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

/**
 *
 * @author Vinicius Berto
 */
public final class Inicio extends Telas {

    private GridLayout gdLayout;
    private FlowLayout flLayout;
    private PnlSuperiorInicio superior;
    private PnlCentroInicio esquerdo;

    public Inicio() {
        this.setTitle("Quimicamente");
        setCursor(Config.CURSOR);
        alteraImagemFundo(tratarImagen("Fundo-Inicio.png", Config.TAM_TELA, Image.SCALE_SMOOTH));
        addKeyListener(getTa());
        inicializarComponentes();
        requestFocus();
    }

    public void inicializarComponentes() {
        superior = new PnlSuperiorInicio();
        esquerdo = new PnlCentroInicio();
        adicionar(superior, BorderLayout.NORTH);
        adicionar(esquerdo, BorderLayout.CENTER);
    }

    public PnlSuperiorInicio getSuperior() {
        return superior;
    }

    public PnlCentroInicio getEsquerdo() {
        return esquerdo;
    }
}
