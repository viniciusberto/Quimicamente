package aprenderbrincando.View;

import aprenderbrincando.Controller.ControllerExecucao;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import static aprenderbrincando.Recursos.obterImagem;
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
        setIcon(obterImagem("Botao", escalaBotao(), Image.SCALE_SMOOTH));
        setRolloverEnabled(true);
        setRolloverIcon(obterImagem("Botao-Hover", escalaBotao(), Image.SCALE_SMOOTH));
        setPressedIcon(obterImagem("Botao-Clicked", escalaBotao(), Image.SCALE_SMOOTH));
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
