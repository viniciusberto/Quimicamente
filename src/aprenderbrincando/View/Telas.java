package aprenderbrincando.View;

/**
 *
 * @author Vinicius Berto
 */
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static aprenderbrincando.Config.*;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import java.awt.BorderLayout;
import java.awt.Component;

public abstract class Telas extends JFrame {

    private JLabel background;

    public Telas() {
        if (background == null) {
            background = new JLabel();
        }
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
    }

    public void adicionar(Component cmpnt, Object o) {
        background.add(cmpnt, o);
    }

    public void alteraImagemFundo(ImageIcon img) {
        background.setIcon(img);
    }

    public void sair() {
        PAUSA = true;
        
        if (Mensagens.exibirDialogoConfirmacao(this, "Sair", "Deseja "
                + "realmente sair do jogo?") == Mensagens.BTN_SIM) {
            System.exit(0);
        }
        
        PAUSA = false;
    }

}
