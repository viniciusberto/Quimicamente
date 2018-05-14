package Testes;

import static Testes.Telinha.LOCAL_IMAGENS;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class ImagePanel extends JPanel {

    private ImageIcon imgFundo;
    private JLabel planoDeFundo = new JLabel();
    private Rectangle bound;

    public ImagePanel(String imagem, Rectangle bound) {
        this.imgFundo = new ImageIcon(LOCAL_IMAGENS + imagem + ".png");
        this.bound = bound;
        desenhar();
    }

    protected void desenhar() {
        setBounds(bound);
       // setOpaque(false);
        setLayout(null);
        setSize(this.bound.width, this.bound.height);
        setPreferredSize(new Dimension(this.bound.width, this.bound.height));
        planoDeFundo.setBounds(0, 0, bound.width, bound.height);
        planoDeFundo.setIcon(imgFundo);
        super.add(planoDeFundo);
    }

    public Component add(Component cmpnt, String local) {
        return planoDeFundo.add(cmpnt);
    }
}
