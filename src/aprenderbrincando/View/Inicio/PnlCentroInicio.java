package aprenderbrincando.View.Inicio;

/**
 * @author Vinicius Berto
 */

import aprenderbrincando.Config;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import static aprenderbrincando.Recursos.obterCursor;
import static aprenderbrincando.Recursos.obterImagem;
import aprenderbrincando.View.BotaoTransparente;
import aprenderbrincando.View.Manipuladores;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;

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
        pnlIniciar.setBackground(Color.red);

        pnlRanking = new JPanel(null);
        pnlRanking.setOpaque(false);
        pnlRanking.setBackground(Color.white);

        pnlSair = new JPanel(null);
        pnlSair.setOpaque(false);
        pnlSair.setBackground(Color.yellow);

        btnIniciar = new BotaoTransparente("Iniciar");
        Manipuladores.centralizarFlow(pnlIniciar, btnIniciar);
        btnIniciar.setIcon(obterImagem("Botao-Iniciar", Config.convertTamanho(30, 17), Image.SCALE_SMOOTH));
        btnIniciar.addActionListener(getTa());
        btnIniciar.addKeyListener(getTa());
        
        btnRanking = new BotaoTransparente("Ranking");
        Manipuladores.centralizarFlow(pnlRanking, btnRanking);
        btnRanking.setIcon(obterImagem("Botao-Ranking", Config.convertTamanho(30, 17), Image.SCALE_SMOOTH));
        btnRanking.addActionListener(getTa());
        btnRanking.addKeyListener(getTa());
        
        btnSair = new BotaoTransparente("Sair");
        Manipuladores.centralizarFlow(pnlSair, btnSair);
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
