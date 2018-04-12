package aprenderbrincando.View;

/**
 * @author Vinicius Berto
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static aprenderbrincando.Config.*;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import aprenderbrincando.Recursos;
import java.awt.BorderLayout;
import java.awt.Component;

public abstract class Telas extends JFrame {

    private JLabel background;

    public Telas() {
        if (background == null) {
            background = new JLabel();
        }
        setCursor(Recursos.obterCursor("Cursor"));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setSize(TAM_TELA);
        setLocationRelativeTo(null);
        background.setLayout(new BorderLayout());
        background.setBounds(0, 0, getSize().width, getSize().height);
        add(background, new Object());
        addKeyListener(getTa());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void adicionar(Component cmpnt, Object o) {
        background.add(cmpnt, o);
    }

    public void alteraImagemFundo(ImageIcon img) {
        background.setIcon(img);
    }

    public void sair() {      
        if (Mensagens.exibirDialogo(this, "Sair", "Deseja "
                + "realmente sair do jogo?",Mensagens.BTN_SIM_NAO) == Mensagens.BTN_SIM) {
            System.exit(0);
        }
    }

}
