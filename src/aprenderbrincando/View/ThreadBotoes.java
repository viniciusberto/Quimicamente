package aprenderbrincando.View;

/**
 * @author Vinicius Berto
 */

import aprenderbrincando.Config;
import static aprenderbrincando.Config.TEMPO_BOTAO;
import static aprenderbrincando.View.Manipuladores.*;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class ThreadBotoes extends Thread {

    List<BotaoFormula> lista;
    JFrame frm;
    JFrame frm2;

    public ThreadBotoes(JFrame frm, JFrame frm2, List<BotaoFormula> lista) {
        this.lista = lista;
        this.frm = frm;
        this.frm2 = frm2;
    }

    public void alterarFrame(JFrame frm, JFrame frm2) {
        this.frm = frm;
        this.frm2 = frm2;
    }

    public void alterarLista(List<BotaoFormula> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < lista.size(); i++) {
                if (!Config.PAUSA) {
                    try{
                        reposicionarBotao(lista.get(i), TEMPO_BOTAO);
                    }catch(NullPointerException ne){
                        Logger.getLogger(ne.getMessage());
                    }
                } else {
                    aguardar(60);
                    if (frm2.isVisible()) {
                        frm2.repaint();
                    } else {
                        frm.repaint();
                    }
                }
            }
        }
    }
}
