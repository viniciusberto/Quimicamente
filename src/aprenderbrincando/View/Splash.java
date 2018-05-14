package aprenderbrincando.View;

import static aprenderbrincando.Config.convertTamanho;
import aprenderbrincando.Recursos;
import static aprenderbrincando.Recursos.obterImagem;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Exibe uma tela de splash com o logo da aplicação
 * 
 * @author vinicius
 */
public class Splash extends JFrame {

    private JLabel lblFundo;
    private JProgressBar pb;

    public Splash() {
        setCursor(Recursos.obterCursor("Cursor"));
        executar();
    }

    private void executar() {
        lblFundo = new JLabel(obterImagem("Splash", convertTamanho(40, 40), Image.SCALE_SMOOTH));
        lblFundo.setBounds(0, 0, convertTamanho(40, 40).width, convertTamanho(40, 40).height);
        this.setSize(convertTamanho(40, 40));
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.add(lblFundo);
        this.setBackground(new Color(0, 0, 0, 0));
    }
}
