package aprenderbrincando.View.Tips;

/**
 * @author Vinicius Berto
 */
import java.awt.Component;

public class Tips {

    public Tips() {

    }

    public static void exibirDica(Component tela, Component cmpnt) {
        System.out.println("Tela:"
                + "\n    [SIZE]"
                + "\n        W: " + tela.getWidth()
                + "\n        H: " + tela.getHeight()
                + "\nComponente:"
                + "\n    [SIZE]"
                + "\n        W: " + cmpnt.getWidth()
                + "\n        H: " + cmpnt.getHeight()
                + "\n    [LOCATION]"
                + "\n        X: " + cmpnt.getParent().getBounds()
                + "\n        Y: " + cmpnt.getLocation().y);
    }

}
