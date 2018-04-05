package aprenderbrincando.View.Execucao;

import static aprenderbrincando.Config.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author vinio
 */
public class PnlLateralExecucao extends JPanel {

    JPanel pnlFormulas, pnlNomes;

    public PnlLateralExecucao() {
        setPreferredSize(TAM_LATERAL_EXE);
        setLayout(new GridLayout(2, 1));
        setOpaque(false);
        setBackground(Color.GREEN);

        JPanel pnlDivisor = new JPanel();
        pnlNomes = new JPanel();
        pnlFormulas = new JPanel();
        JPanel pnlVazio = new JPanel();

        pnlDivisor.setLayout(new BorderLayout());
        pnlDivisor.setOpaque(false);

        pnlVazio.setOpaque(false);

        pnlFormulas.setLayout(new GridLayout(10, 1,2,0));
        pnlDivisor.add(pnlFormulas, BorderLayout.EAST);
        pnlFormulas.setOpaque(false);

        pnlNomes.setLayout(new GridLayout(10, 1,2,0));
        pnlDivisor.add(pnlNomes, BorderLayout.CENTER);
        pnlNomes.setOpaque(false);

        pnlDivisor.setBackground(Color.WHITE);
        add(pnlDivisor);

        add(pnlVazio);
    }

    public void adicionar(String nome, String formula) {
        JLabel lblNome = new JLabel(nome);
        JLabel lblFormula = new JLabel(formula);

        lblFormula.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblFormula.setFont(FONTE_PADRAO.deriveFont(15f));
        lblFormula.setOpaque(true);
        lblFormula.setBackground(new Color(221, 118, 242));

        lblNome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblNome.setFont(FONTE_PADRAO.deriveFont(15f));
        lblNome.setOpaque(true);
        lblNome.setBackground(new Color(161, 0, 193));
        
        if ((pnlFormulas.getComponents().length + 1) % 2 == 0) {
            lblFormula.setBackground(new Color(161, 0, 193));
            lblNome.setBackground(new Color(221, 118, 242));

        }
        
        pnlFormulas.add(lblFormula);
        pnlNomes.add(lblNome);
    }

}
