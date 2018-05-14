package Testes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Telinha extends JFrame implements Runnable {

    public static String LOCAL_IMAGENS = "F:\\";

    private static final Toolkit TOO = Toolkit.getDefaultToolkit();
    public static final Dimension DM = TOO.getScreenSize();
    JLabel planodeFundo = new JLabel(new ImageIcon("F:\\FundoColorido.png"));//new ImageIcon(new ImageIcon("F:\\FundoColorido.png").getImage().getScaledInstance(DM.width, DM.height, Image.SCALE_SMOOTH)));
    ImageIcon imgFundo;
    private static ImagePanel pnlListaLateral;
    private static Telinha t;

    public Telinha() {
        desenhar();
    }

    public static void main(String[] args) {
        t = new Telinha();
        ImagePanel topoCentroFormula = new ImagePanel("Fundo-Formula", new Rectangle((DM.width / 2) - 158, 0, 316, 121));
        JLabel lblFormula = new JLabel("Pirofosfato de Estrôncio");
        lblFormula.setHorizontalAlignment(JLabel.CENTER);
        lblFormula.setFont(new Font("Calibri", Font.BOLD, 23));
        lblFormula.setForeground(new Color(255, 216, 0));
        lblFormula.setBounds(29, 40, 258, 63);
        topoCentroFormula.add(lblFormula, "");
        t.add(topoCentroFormula, "");

        JPanel topoEsquerda = new FundoRepetido(new Rectangle(0, 0, (DM.width / 2) - 158, 56));
        t.add(topoEsquerda, "");

        JPanel topoDireita = new FundoRepetido(new Rectangle((DM.width / 2) + 158, 0, (DM.width / 2) - 158 - 148, 56));
        t.add(topoDireita, "");

        ImagePanel topoDireitaPlacar = new ImagePanel("Fundo-Placar", new Rectangle(DM.width - 148, 0, 148, 121));
        JLabel lblNivel = new JLabel("Nível: 8");
        lblNivel.setHorizontalAlignment(JLabel.CENTER);
        lblNivel.setFont(new Font("Calibri", Font.BOLD, 18));
        lblNivel.setForeground(new Color(255, 216, 0));
        lblNivel.setBounds(0, 27, 119, 33);
        topoDireitaPlacar.add(lblNivel, "");

        JLabel lblPontos = new JLabel("Pontos: 999");
        lblPontos.setHorizontalAlignment(JLabel.CENTER);
        lblPontos.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPontos.setForeground(new Color(255, 216, 0));
        lblPontos.setBounds(15, 70, 119, 33);
        topoDireitaPlacar.add(lblPontos, "");

        t.add(topoDireitaPlacar, "");

        pnlListaLateral = new ImagePanel("Fundo-Barra-Lateral", new Rectangle(DM.width - 319, (DM.height / 2) - 245, 319, 490));
        t.add(pnlListaLateral, "");

        JButton btn = new JButton("Teste");
        btn.setBounds(100, 200, 70, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread tr = new Thread(t);
                tr.start();
            }
        });
        t.add(btn, "");

        t.repaint();
    }

    public Component add(Component cpmnt, String str) {
        return planodeFundo.add(cpmnt);
    }

    private void desenhar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DM);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setTitle("Testes");
        super.add(planodeFundo);
        setVisible(true);
    }

    @Override
    public void run() {
        int i;
        if (!(pnlListaLateral.getBounds().width <= 0)) {
            i = 319;
            while (i > 0) {
                pnlListaLateral.setBounds(pnlListaLateral.getX() + 2, pnlListaLateral.getY(),
                        pnlListaLateral.getWidth() - 2, pnlListaLateral.getHeight());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Telinha.class.getName()).log(Level.SEVERE, null, ex);
                }
                i = i - 2;
                this.repaint();
            }
            pnlListaLateral.setVisible(false);
        } else {
            i = 0;
            pnlListaLateral.setVisible(true);
            while (i <= 318) {
                pnlListaLateral.setBounds(pnlListaLateral.getX() - 2, pnlListaLateral.getY(),
                        pnlListaLateral.getWidth() + 2, pnlListaLateral.getHeight());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Telinha.class.getName()).log(Level.SEVERE, null, ex);
                }
                i = i + 2;
                this.repaint();
            }

        }
    }
}
