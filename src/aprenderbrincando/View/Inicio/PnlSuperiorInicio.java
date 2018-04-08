package aprenderbrincando.View.Inicio;

import aprenderbrincando.Config;
import static aprenderbrincando.Recursos.obterCursor;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Berto
 */
public class PnlSuperiorInicio extends JPanel {
    
    public PnlSuperiorInicio() {
        setPreferredSize(Config.convertTamanho(100, 20));
        setOpaque(false);
        setCursor(obterCursor("Cursor"));
    }
    
}