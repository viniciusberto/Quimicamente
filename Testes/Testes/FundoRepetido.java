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

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    BufferedImage b;
    Rectangle2D rect;
    Dimension tm;

    public FundoRepetido(String imagem, Rectangle bound, int orientacao) {
        try {
            b = ImageIO.read(new File(LOCAL_IMAGENS + imagem + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(FundoRepetido.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tm = new Dimension(bound.width, bound.height);
        setBounds(bound);
        if (orientacao == 0) {
            rect = new Rectangle(0, 0, 1, bound.height);
        } else {
            rect = new Rectangle(0, 0, bound.width, 1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        TexturePaint p = new TexturePaint(b, rect);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(p);
        g2.fillRect(0, 0, tm.width, tm.height);
    }
}
