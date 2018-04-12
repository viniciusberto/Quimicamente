package aprenderbrincando.View.MenuPausa;

import aprenderbrincando.View.Animacoes;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 *
 * @author Vinícius Berto
 */
public class BotaoAcao extends JButton {

    public Point pOriginal;
    public Dimension tOriginal;

    public BotaoAcao() {
        inserirActions();
    }

    public BotaoAcao(String texto) {
        super(texto);
        inserirActions();
    }

    private void inserirActions() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(null);
        setRolloverEnabled(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                Animacoes.diminuirEscala((Component) e.getSource(), 20);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Animacoes.aumentarEscala((Component) e.getSource(), 20);
            }
        });
    }

}
