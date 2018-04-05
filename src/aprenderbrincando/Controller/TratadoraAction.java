package aprenderbrincando.Controller;

import aprenderbrincando.View.Ranking.Ranking;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

/**
 *
 * @author Vinicius Berto
 */
public class TratadoraAction implements ActionListener, KeyListener {

    private ControllerExecucao ce;

    public TratadoraAction(ControllerExecucao controllerExecucao) {
        this.ce = controllerExecucao;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        switch (btn.getName()) {
            case "Iniciar":
                ce.iniciarPartida();
                break;
            case "Ranking":
                Ranking.exibirRanking(ce.getTelaInicial());
                break;
            case "Sair":
                ce.getTelaInicial().sair();
                break;

            default:
                ce.validarResposta(btn.getName());
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String src = e.getSource().getClass().toString();
        if (e.getKeyCode() == VK_ESCAPE) {
            if (src.indexOf("Transparente") > 0) {
                ce.getTelaInicial().sair();
            } else if (src.indexOf("JButton") > 0) {
                JButton btn = (JButton) e.getSource();
                if (btn.getText().equals("X ")) {
                    btn.doClick();
                }
            }else if(src.indexOf("BotaoFormula")>0){
                ce.getTelaExecucao().sair();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
