package aprenderbrincando.View;

/**
 * @author Vinicius Berto
 */
import aprenderbrincando.Config;
import aprenderbrincando.View.MenuPausa.BotaoAcao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Animacoes extends Thread {

    public static final int OCULTAR_ESQUERDA_DIREITA = 1;

    public static final int AUMENTAR_ESCALA = 2;

    public static final int DIMINUIR_ESCALA = 3;

    private static int tipo;
    private static Thread thread;

    private static boolean ocupado = false;

//    Ocultar Esquerda direita
    private static JComponent pnlSome;
    private static JComponent pnlAumenta;
    

    public Animacoes() {
    }

    public static void ocultarEsquerdaDireita(JComponent pnlOcultar, JComponent pnlAumentar) {
        thread = null;
        pnlSome = pnlOcultar;
        pnlAumenta = pnlAumentar;
        tipo = OCULTAR_ESQUERDA_DIREITA;
        thread = new Animacoes();
        thread.start();
    }

    public static void aumentarEscala(Component cmpnt, int valor) {


    }

    public static void diminuirEscala(Component cmpnt, int valor) {

    }

    public synchronized void executarAcao() {
        switch (tipo) {
            case OCULTAR_ESQUERDA_DIREITA:
                int alturaFixa = pnlAumenta.getHeight();
                int topoFixo = pnlAumenta.getY();
                for (int I = pnlSome.getWidth(); I >= 0; I--) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    pnlSome.setSize(new Dimension(I, alturaFixa));
                    pnlAumenta.setSize(new Dimension(pnlAumenta.getWidth() + 1, alturaFixa));
                    pnlSome.setLocation(pnlAumenta.getWidth(), topoFixo);
                    pnlAumenta.repaint();
                    pnlSome.repaint();
                }
                pnlAumenta.setSize(Config.TAM_TELA.width, pnlAumenta.getHeight());
                Config.alterarLinhasColunas(pnlAumenta.getHeight(), Config.TAM_TELA.width);
                pnlSome.setVisible(false);
                break;

            case AUMENTAR_ESCALA:

                break;

            case DIMINUIR_ESCALA:

                break;
        }
    }

    @Override
    public void run() {
        executarAcao();
    }

}
