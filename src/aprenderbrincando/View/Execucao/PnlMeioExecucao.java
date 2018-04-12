package aprenderbrincando.View.Execucao;

/**
 * @author Vinicius Berto
 */

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PnlMeioExecucao extends JPanel{

    public PnlMeioExecucao() {
        setBackground(Color.RED);
        setBorder(BorderFactory.createMatteBorder(3, 2, 3, 0, Color.BLACK));
        setLayout(null);
        setOpaque(false);
    }

}
