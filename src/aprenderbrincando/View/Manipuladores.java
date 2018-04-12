package aprenderbrincando.View;

/**
 * @author Vinicius Berto
 */
import static aprenderbrincando.Config.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import static java.lang.Math.round;
import java.util.Random;
import javax.swing.JComponent;

public class Manipuladores {

    /**
     * Método responsável por centralizar um componente dentro de outro
     * utilizando FlowLayout
     *
     * @perso Componente container;
     * @item Componente a ser centralizado;
     */
    public static void centralizarFlow(JComponent perso, JComponent item) {
        perso.setLayout(new FlowLayout(FlowLayout.CENTER));
        item.setPreferredSize(convertTamanho(30, 17));
        item.setMinimumSize(convertTamanho(30, 17));
    }

    public static String estilizarLabels(String string) {
        return "<html>"
                + "<style>"
                + "span{"
                + "font-weight:bold;"
                + "font-size:" + TAM_FONTE_LBL_EXECUCAO + "pt;"
                + "font-family:'Roboto','Calibri','Roboto CN','Sans Serif';"
                + "color:rgb(255,228,23);"
                + "}"
                + "</style>"
                + "<span>" + string + "</span></html>";
    }

    public static void aguardar(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {

        }
    }

    public static void reposicionarBotao(BotaoFormula btn, int tempo) {
        aguardar(tempo);
        limparPosicaoMatriz(new Point(btn.getLocation().y, btn.getLocation().x));
        Point pos = gerarPosicao();
        btn.setLocation(new Point(pos.y, pos.x));
        marcarPosicaoMatriz(pos);
    }

    public static Point gerarPosicao() {
        Random ran = new Random();
        Point ponto;

        int linhas = LINHAS - (escalaBotao().height);
        int colunas = COLUNAS - (escalaBotao().width);

        do {
            ponto = new Point(ran.nextInt(linhas), ran.nextInt(colunas));

        } while (!verificarNaMatriz(ponto));
        return ponto;
    }

    private static boolean verificarNaMatriz(Point ponto) {
        boolean resposta = true;
        try {
            for (int l = 0; l < escalaBotao().height; l++) {
                for (int c = 0; c < escalaBotao().width; c++) {
                    if (MATRIZ[ponto.x + l][ponto.y + c]) {
                        resposta = false;
                    }
                }
            }
        } catch (IndexOutOfBoundsException ie) {
            //System.out.println("Erro: "+ponto);
            resposta = false;
        }
        return resposta;
    }

    public static Dimension escalaBotao() {
        Double c1;
        int larguraTela;
        if (WIDESCREEM) {
            c1 = 100.0;
            larguraTela = 1366;
        } else {
            c1 = 100.0;
            larguraTela = 1024;
        }
        c1 = (double) c1 * TAM_TELA.width;
        c1 = (double) c1 / larguraTela;
        return new Dimension(round(c1.floatValue()), round(c1.floatValue()));
    }

    private static void limparPosicaoMatriz(Point ponto) {
        for (int l = 0; l < escalaBotao().height; l++) {
            for (int c = 0; c < escalaBotao().width; c++) {
                MATRIZ[ponto.x + l][ponto.y + c] = false;
            }
        }
    }

    public static void marcarPosicaoMatriz(Point ponto) {
        for (int l = 0; l < escalaBotao().height; l++) {
            for (int c = 0; c < escalaBotao().width; c++) {
                MATRIZ[ponto.x + l][ponto.y + c] = true;
            }
        }
    }

}
