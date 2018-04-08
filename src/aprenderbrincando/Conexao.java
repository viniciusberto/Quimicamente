package aprenderbrincando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static aprenderbrincando.Config.*;
import aprenderbrincando.View.Mensagens;

/**
 *
 * @author Vinicius
 */

public class Conexao {

    private final Connection connection = null;
   
    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + URL_BANCO;
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            Mensagens.exibirMensagem(null,"Erro!", "<html>Falha na conexão com o banco de dados!<p>Erro:"+e.getMessage()+"</p></html>", Mensagens.MSG_ERRO);
        }
        return conn;
    }

}
