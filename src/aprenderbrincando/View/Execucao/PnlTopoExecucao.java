package aprenderbrincando.View.Execucao;

import static aprenderbrincando.Config.TAM_NORTE_EXE;
import static aprenderbrincando.Config.convertTamanho;
import static aprenderbrincando.Recursos.obterImagem;
import static aprenderbrincando.View.Manipuladores.estilizarLabels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlTopoExecucao extends JPanel{

    private JPanel pnlEsquerdaTopo;
    private JPanel pnlCentroTopo;
    private JPanel pnlDireitaTopo;

    private JLabel lblFormula;
    private JLabel lblPontos;
    private JLabel lblNivel;
    private JLabel lblTempo = new JLabel("Tempo");

    public PnlTopoExecucao() {
        JLabel fundo = new JLabel(obterImagem("Fundo-Topo-Execucao", TAM_NORTE_EXE, Image.SCALE_SMOOTH));
        setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.BLACK));
        setBackground(Color.WHITE);
        setPreferredSize(TAM_NORTE_EXE);
        setLayout(new BorderLayout());

        pnlEsquerdaTopo = new JPanel();
        pnlEsquerdaTopo.setPreferredSize(convertTamanho(20, 15));
        pnlEsquerdaTopo.setOpaque(false);
        lblNivel = new JLabel();
        lblNivel.setText(estilizarLabels("Nível"));
        
        pnlEsquerdaTopo.add(lblNivel);

        pnlCentroTopo = new JPanel();
        lblFormula = new JLabel();
        lblFormula.setText(estilizarLabels("Fórmula Atual"));
        pnlCentroTopo.add(lblFormula);
        pnlCentroTopo.setOpaque(false);

        pnlDireitaTopo = new JPanel();
        pnlDireitaTopo.setPreferredSize(convertTamanho(20, 3));
        pnlDireitaTopo.setOpaque(false);
        lblPontos = new JLabel();
        lblPontos.setText(estilizarLabels("Pontos"));
        pnlDireitaTopo.add(lblPontos);

        add(fundo, BorderLayout.CENTER);

        fundo.setLayout(new BorderLayout());
        fundo.add(pnlEsquerdaTopo, BorderLayout.WEST);
        fundo.add(pnlDireitaTopo, BorderLayout.EAST);
        fundo.add(pnlCentroTopo, BorderLayout.CENTER);
    }

    public JLabel getLblFormula() {
        return lblFormula;
    }

    public JLabel getLblPontos() {
        return lblPontos;
    }

    public JLabel getLblNivel() {
        return lblNivel;
    }

    public JLabel getLblHora() {
        return lblTempo;
    }

}