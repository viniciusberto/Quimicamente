package aprenderbrincando;

/**
 * @author Vinicius Berto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static aprenderbrincando.Config.*;
import aprenderbrincando.View.Mensagens;

public class Conexao {

    private final Connection connection = null;

    /**
     * Classe responsável por criar uma conexão com banco de dados.
     *
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + URL_BANCO;
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            String div = "";

            while (msg.length() > 40) {
                div += msg.substring(0, 40) + "<br>";
                msg = msg.substring(40, msg.length());
            }

            div += msg;

            Mensagens.exibirMensagem(null, "Erro!", "<html>"
                    + "<p>Falha na conexão com o banco de dados!</p>"
                    + "<p><span><br>Erro:" + div + "</span></p>"
                    + "</html>", Mensagens.MSG_ERRO);
        }
        return conn;
    }

}
