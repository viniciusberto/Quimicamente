package Testes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author vinio
 */
public class ListaLateral extends ImagePanel implements Runnable {

    private List<String> nomes = new ArrayList<>();
    private List<String> formulas = new ArrayList<>();
    private int y = 40;

    public ListaLateral(String imagem, Rectangle bound) {
        super(imagem, bound);
        setOpaque(false);
    }

    public void add(String nome, String formula) {
        nomes.add(nome);
        formulas.add(formula);
    }

    public void listar() {
        for (int i = 0; i < nomes.size(); i++) {
            JLabel lblNome = new JLabel();
            lblNome.setText(formulas.get(i));
            lblNome.setFont(new Font("Arial", Font.BOLD, 16));
            lblNome.setBackground(new Color(0, 93, 112));
            lblNome.setBounds(230, y + i * 40, 250, 35);
            lblNome.setForeground(Color.YELLOW);
            lblNome.setOpaque(true);
            add(lblNome, "");

            JLabel lblFormula = new JLabel();
            lblFormula.setText(nomes.get(i));
            lblFormula.setFont(new Font("Arial", Font.BOLD, 16));
            lblFormula.setBackground(new Color(0, 93, 112));
            lblFormula.setForeground(Color.YELLOW);
            lblFormula.setBounds(20, y + i * 40, 230, 35);
            lblFormula.setOpaque(true);
            add(lblFormula, "");

//            LinhaHorizontal linha = new LinhaHorizontal(new Rectangle(20, y + i * 42, 330, 10));
//            linha.setOpaque(true);
//            add(linha, "");
        }

    }

    public void ocultar() {
        if (getWidth() > 0) {
            Thread tr = new Thread(this);
            tr.start();
        }
    }

    @Override
    public void run() {
        for (int i = getBounds().width; i >= 0; i = i - 2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListaLateral.class.getName()).log(Level.SEVERE, null, ex);
            }
            setBounds(getBounds().x + 2, getBounds().y, i, getBounds().height);
            System.out.println(getBounds());

        }
        setVisible(false);
    }

}
