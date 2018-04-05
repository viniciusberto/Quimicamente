package aprenderbrincando.View.Inicio;

import aprenderbrincando.Config;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Berto
 */
public class PnlSuperiorInicio extends JPanel {
    
    public PnlSuperiorInicio() {
        setPreferredSize(Config.convertTamanho(100, 20));
        setOpaque(false);
        setCursor(Config.CURSOR);
    }
    
}