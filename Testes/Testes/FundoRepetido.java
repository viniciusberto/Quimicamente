package Testes;

import static Testes.Telinha.LOCAL_IMAGENS;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class FundoRepetido extends JPanel {

    BufferedImage b;
    Rectangle2D rect;
    Dimension tm;

    public FundoRepetido(Rectangle bound) {
        try {
            b = ImageIO.read(new File(LOCAL_IMAGENS + "Topo.png"));
        } catch (IOException ex) {
            Logger.getLogger(FundoRepetido.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tm = new Dimension(bound.width, bound.height);
        setBounds(bound);
        rect = new Rectangle(0, 0, 1, bound.height);
    }

    @Override
    public void paintComponent(Graphics g) {
        TexturePaint p = new TexturePaint(b, rect);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(p);
        g2.fillRect(0, 0, tm.width, tm.height);
    }
}
