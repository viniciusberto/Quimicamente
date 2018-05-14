package aprenderbrincando.View.Execucao;

/**
 * @author Vinicius Berto
 */

import static aprenderbrincando.Recursos.obterCursor;
import static aprenderbrincando.Recursos.obterImagem;
import aprenderbrincando.View.Animacoes;
import aprenderbrincando.View.MenuPausa.MenuPausa;
import aprenderbrincando.View.Telas;
import java.awt.*;

public class Execucao extends Telas {

    private PnlTopoExecucao topo;
    private PnlMeioExecucao meio;
    private PnlLateralExecucao lateral;

    public Execucao() {
        setCursor(obterCursor("Cursor"));
        alteraImagemFundo(obterImagem("FundoLimpo", getSize(), Image.SCALE_SMOOTH));

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
