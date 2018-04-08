package aprenderbrincando.View.Inicio;

import aprenderbrincando.Config;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import static aprenderbrincando.Recursos.obterCursor;
import static aprenderbrincando.Recursos.obterImagem;
import aprenderbrincando.View.BotaoTransparente;
import aprenderbrincando.View.Manipuladores;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Berto
 */
public class PnlCentroInicio extends JPanel {

    private JPanel pnlIniciar;
    private JPanel pnlRanking;
    private JPanel pnlSair;

    private JButton btnIniciar;
    private JButton btnRanking;
    private JButton btnSair;

    public PnlCentroInicio() {
        GridLayout gdLayout = new GridLayout(6, 1);
        gdLayout.setVgap(1);
        gdLayout.setRows(4);
        setLayout(gdLayout);

        JPanel pnlVazio = new JPanel();
        pnlVazio.setOpaque(false);
        pnlVazio.addKeyListener(getTa());

        pnlIniciar = new JPanel(null);
        pnlIniciar.setOpaque(false);

        pnlRanking = new JPanel(null);
        pnlRanking.setOpaque(false);

        pnlSair = new JPanel(null);
        pnlSair.setOpaque(false);

        btnIniciar = new BotaoTransparente("Iniciar");
        Manipuladores.centralizar(pnlIniciar, btnIniciar, Manipuladores.FLOW);
        btnIniciar.setIcon(obterImagem("Botao-Iniciar", Config.convertTamanho(30, 17), Image.SCALE_SMOOTH));
        btnIniciar.addActionListener(getTa());
        btnIniciar.addKeyListener(getTa());
        
        btnRanking = new BotaoTransparente("Ranking");
        Manipuladores.centralizar(pnlRanking, btnRanking, Manipuladores.FLOW);
        btnRanking.setIcon(obterImagem("Botao-Ranking", Config.convertTamanho(30, 17), Image.SCALE_SMOOTH));
        btnRanking.addActionListener(getTa());
        btnRanking.addKeyListener(getTa());
        
        btnSair = new BotaoTransparente("Sair");
        Manipuladores.centralizar(pnlSair, btnSair, Manipuladores.FLOW);
        btnSair.setIcon(obterImagem("Botao-Sair", Config.convertTamanho(30, 17), Image.SCALE_SMOOTH));
        btnSair.addActionListener(getTa());
        btnSair.addKeyListener(getTa());
        
        pnlRanking.add(btnRanking);
        pnlIniciar.add(btnIniciar);
        pnlSair.add(btnSair);

        add(pnlVazio);
        add(pnlIniciar);
        add(pnlRanking);
        add(pnlSair);
        setOpaque(false);
        setCursor(obterCursor("Cursor"));
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnRanking() {
        return btnRanking;
    }
}
