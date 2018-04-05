package aprenderbrincando.View.Execucao;

import static aprenderbrincando.Config.CURSOR;
import aprenderbrincando.View.Animacoes;
import aprenderbrincando.View.BotaoFormula;
import aprenderbrincando.View.Telas;
import static aprenderbrincando.View.Manipuladores.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Vinicius Berto
 */
public class Execucao extends Telas {

    private PnlTopoExecucao topo;
    private PnlMeioExecucao meio;
    private PnlLateralExecucao lateral;

    public Execucao() {
        setCursor(CURSOR);
        alteraImagemFundo(tratarImagen("Fundo-Meio-Execucao.png", getSize(), Image.SCALE_SMOOTH));

        topo = new PnlTopoExecucao();
        meio = new PnlMeioExecucao();
        lateral = new PnlLateralExecucao();

        adicionar(topo, BorderLayout.NORTH);
        adicionar(lateral, BorderLayout.EAST);
        adicionar(meio, BorderLayout.CENTER);
    }

    public PnlTopoExecucao getTopo() {
        return topo;
    }

    public PnlMeioExecucao getMeio() {
        return meio;
    }

    public void ocultarMenuLateral() {
        Animacoes.ocultarEsquerdaDireita(lateral, meio);
    }

    public void adicionarMenuLateral(String nome, String formula) {
        lateral.adicionar(nome, formula);
    }


}
