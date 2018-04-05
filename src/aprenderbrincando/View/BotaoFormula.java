package aprenderbrincando.View;

import aprenderbrincando.Controller.ControllerExecucao;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import javax.swing.JButton;
import static aprenderbrincando.View.Manipuladores.*;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Vinicius
 */
public class BotaoFormula extends JButton {

    public BotaoFormula(String texto, String nome) {
        super(texto);
        alterarPropriedades(nome);
    }

    private void alterarPropriedades(String nome) {
        setName(nome);
        setIcon(tratarImagen("Botao.png", escalaBotao(), Image.SCALE_SMOOTH));
        setRolloverEnabled(true);
        setRolloverIcon(tratarImagen("Botao-Hover.png", escalaBotao(), Image.SCALE_SMOOTH));
        setPressedIcon(tratarImagen("Botao-Clicked.png", escalaBotao(), Image.SCALE_SMOOTH));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(null);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        Point pos = gerarPosicao();
        marcarPosicaoMatriz(pos);
        setBounds(pos.y, pos.x, escalaBotao().width, escalaBotao().height);
        addActionListener(ControllerExecucao.getTa());
        addKeyListener(getTa());
    }

}
