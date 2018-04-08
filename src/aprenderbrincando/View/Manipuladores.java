package aprenderbrincando.View;

import aprenderbrincando.Config;
import static aprenderbrincando.Config.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import static java.lang.Math.round;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author IFMS
 */
public class Manipuladores {

    /**
     * Variável para o método centralizar, com essa váriavel o método
     * centralizar. Realizará a funcao de centralizar um objeto em um container
     * utilizado o Flow Layout.
     */
    public static final int FLOW = 1;

    /**
     * Variável para o método centralizar, com essa váriavel o método
     * centralizar. Realizará a funcao de centralizar um objeto em um container
     * utilizado o Grid Bag Layout.
     */
    public static final int GRID_BAG = 2;
    /**
     * Variável para o método centralizar, com essa váriavel o método
     * centralizar. Realizará a funcao de centralizar um objeto em um container
     * utilizado o layout Nulo.
     */
    public static final int NULL = 3;
    

//    public static ImageIcon tratarImagen(String imagem, Dimension tamanho, int escala) {
//        JButton btn = new JButton();
//        System.out.println(DIR_IMAGENS+imagem);
//        ImageIcon img = new ImageIcon(btn.getClass().getResource(DIR_IMAGENS+imagem));
//        int altura = tamanho.height;
//        int largura = tamanho.width;
//
//        return new ImageIcon(img.getImage().getScaledInstance(largura, altura, escala));
//    }

    /**
     * Método responsável por centralizar um componente dentro de outro:
     *
     * @perso Componente container;
     * @item Componente a ser centralizado;
     * @tipo O tipo de layout manager que será utilizado para centralizar o
     * componente: 1 = Flow Layout 2 = Grid BagLayout
     */
    public static void centralizar(JComponent perso, JComponent item, int tipo) {
        switch (tipo) {
            case 1:
                perso.setLayout(new FlowLayout(FlowLayout.CENTER));
                item.setPreferredSize(convertTamanho(30, 17));
                item.setMinimumSize(convertTamanho(30, 17));
                break;
            case 2:
                JComponent personalizar;
                personalizar = perso;
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints constraints = new GridBagConstraints();
                personalizar.setLayout(layout);
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;
                layout.setConstraints(item, constraints);
                break;

        }
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
