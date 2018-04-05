package aprenderbrincando.View;

import aprenderbrincando.Config;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 *
 * @author vinio
 */
public class Animacoes extends Thread {

    public static final int OCULTAR_ESQUERDA_DIREITA = 1;

    private static JComponent pnlSome;
    private static JComponent pnlAumenta;

    public Animacoes() {
    }

    public static void ocultarEsquerdaDireita(JComponent pnlOcultar, JComponent pnlAumentar) {
        pnlSome = pnlOcultar;
        pnlAumenta = pnlAumentar;
        Thread tr = new Animacoes();
        tr.start();
    }

    @Override
    public void run() {
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
        pnlAumenta.setSize(Config.TAM_TELA.width,pnlAumenta.getHeight());
        Config.alterarLinhasColunas(pnlAumenta.getHeight(), Config.TAM_TELA.width);
        pnlSome.setVisible(false);
    }

}
