package Testes;

import static Testes.FundoRepetido.HORIZONTAL;
import static Testes.FundoRepetido.VERTICAL;
import aprenderbrincando.Controller.ControllerExecucao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Telinha extends JFrame implements Runnable {

    private ControllerExecucao ctrlExec;

    private JLabel lblFormula;
    private JLabel lblNivel;
    private JLabel lblPontos;

    public static String LOCAL_IMAGENS = "F:\\";

    private static final Toolkit TOO = Toolkit.getDefaultToolkit();
    private static final Dimension DM = TOO.getScreenSize();
    private final JLabel planodeFundo = new JLabel(new ImageIcon("F:\\FundoColorido.png"));//new ImageIcon(new ImageIcon("F:\\FundoColorido.png").getImage().getScaledInstance(DM.width, DM.height, Image.SCALE_SMOOTH)));
    private ImageIcon imgFundo;
    private ListaLateral pnlListaLateral;

    public Telinha(ControllerExecucao ctrlExec) {
        this.ctrlExec = ctrlExec;
        configurar();
        desenharElementos();
    }

    private void desenharElementos() {
        ImagePanel topoCentroFormula = new ImagePanel("Fundo-Formula", new Rectangle((DM.width / 2) - 158, 0, 316, 121));
        lblFormula = new JLabel("Pirofosfato de Estrôncio");
        lblFormula.setHorizontalAlignment(JLabel.CENTER);
        lblFormula.setFont(new Font("Calibri", Font.BOLD, 23));
        lblFormula.setForeground(new Color(255, 216, 0));
        lblFormula.setBounds(29, 40, 258, 63);
        topoCentroFormula.add(lblFormula, "");
        this.add(topoCentroFormula, "");

        JPanel topoEsquerda = new FundoRepetido("Topo", new Rectangle(56, 0, (DM.width / 2) - 158 - 56, 56), HORIZONTAL);
        this.add(topoEsquerda, "");

        JPanel topoDireita = new FundoRepetido("Topo", new Rectangle((DM.width / 2) + 158, 0, (DM.width / 2) - 158 - 148, 56), HORIZONTAL);
        this.add(topoDireita, "");

        ImagePanel topoDireitaPlacar = new ImagePanel("Fundo-Placar", new Rectangle(DM.width - 148, 0, 148, 121));
        lblNivel = new JLabel("Nível: 8");
        lblNivel.setHorizontalAlignment(JLabel.CENTER);
        lblNivel.setFont(new Font("Calibri", Font.BOLD, 18));
        lblNivel.setForeground(new Color(255, 216, 0));
        lblNivel.setBounds(0, 27, 119, 33);
        topoDireitaPlacar.add(lblNivel, "");

        lblPontos = new JLabel("Pontos: 999");
        lblPontos.setHorizontalAlignment(JLabel.CENTER);
        lblPontos.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPontos.setForeground(new Color(255, 216, 0));
        lblPontos.setBounds(15, 70, 119, 33);
        topoDireitaPlacar.add(lblPontos, "");
        this.add(topoDireitaPlacar, "");

        pnlListaLateral = new ListaLateral("Fundo-Barra-Lateral", new Rectangle(DM.width - 316, (DM.height / 2) - 245, 316, 489));
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.add("Pirofosfato de Estrôncio", "| Sr2P2O7");
        pnlListaLateral.listar();

        JButton btn = new JButton("Testes");
        btn.setBounds(100, 100, 75, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlListaLateral.ocultar();
            }
        });
        add(btn, "");

        ImagePanel pnlTopoEsquerda = new ImagePanel("Topo-Esquerda", new Rectangle(0, 0, 56, 65));
        this.add(pnlTopoEsquerda, "");

        JPanel pnlLateralEsquerda = new FundoRepetido("Left", new Rectangle(0, 65, 26, DM.height - 130), VERTICAL);
        this.add(pnlLateralEsquerda, "");

        JPanel pnlLateralDireita = new FundoRepetido("Right", new Rectangle(DM.width - 26, 121, 26, DM.height - 186), VERTICAL);
        this.add(pnlLateralDireita, "");

        JPanel pnlBottom = new FundoRepetido("Bottom", new Rectangle(56, DM.height - 56, DM.width - 112, 56), HORIZONTAL);
        this.add(pnlBottom, "");

        JPanel pnlBottomLeft = new ImagePanel("Bottom-Left", new Rectangle(0, DM.height - 65, 56, 65));
        this.add(pnlBottomLeft, "");

        JPanel pnlBottomRight = new ImagePanel("Bottom-Right", new Rectangle(DM.width - 56, DM.height - 65, 56, 65));
        this.add(pnlBottomRight, "");

        this.add(pnlListaLateral, "");
        this.repaint();
    }

    public static void main(String[] args) {
        Telinha t = new Telinha(null);
    }

    public Component add(Component cpmnt, String str) {
        return planodeFundo.add(cpmnt);
    }

    private void configurar() {
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
//        int i;
//        if (!(pnlListaLateral.getBounds().width <= 0)) {
//            i = 316;
//            while (i > 0) {
//                pnlListaLateral.setBounds(pnlListaLateral.getX() + 2, pnlListaLateral.getY(),
//                        pnlListaLateral.getWidth() - 2, pnlListaLateral.getHeight());
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Telinha.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                i = i - 2;
//                this.repaint();
//            }
//            pnlListaLateral.setVisible(false);
//        } else {
//            i = 0;
//            pnlListaLateral.setVisible(true);
//            while (i <= 316) {
//                pnlListaLateral.setBounds(pnlListaLateral.getX() - 2, pnlListaLateral.getY(),
//                        pnlListaLateral.getWidth() + 2, pnlListaLateral.getHeight());
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Telinha.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                i = i + 2;
//                this.repaint();
//            }
//
//        }
    }

}
