package aprenderbrincando.View;

import aprenderbrincando.Config;
import static aprenderbrincando.Config.convertTamanho;
import static aprenderbrincando.Config.convertTamanhoLA;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author vinicius
 */
public class Splash2 extends JFrame {
    
    public static void main(String[] args) {
        new Splash2();
    }
    
    public Splash2() {
        JPanel pnlGeral = new JPanel();
        pnlGeral.setBackground(Color.red);
        pnlGeral.setOpaque(true);
        pnlGeral.setBounds(0, 0, convertTamanhoLA(40, Config.LARGURA), convertTamanhoLA(40, Config.LARGURA));
        this.setLayout(new BorderLayout());
        this.setSize(convertTamanho(40, 40));
        this.add(pnlGeral, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
