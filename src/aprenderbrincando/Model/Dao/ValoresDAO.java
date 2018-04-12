package aprenderbrincando.Model.Dao;

/**
 * @author Vinicius Berto
 */

import static aprenderbrincando.Config.TAM_FONTE_BTN_FORMULA;
import aprenderbrincando.Conexao;
import aprenderbrincando.Model.Vo.Valores;
import aprenderbrincando.View.Mensagens;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ValoresDAO {

    public List<Valores> listagem() {

        List<Valores> lista = new ArrayList<>();
        Connection conn = new Conexao().getConnection();

        Statement stmt;
        try {

            stmt = conn.createStatement();

            String sql = "SELECT * FROM valores";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Valores valores = new Valores();
                valores.setId(rs.getInt("id"));
                valores.setFormula(rs.getString("formula"));
                valores.setNome(rs.getString("nome"));
                valores.setBtnTexto(textoBotao(rs.getString("formula")));
                lista.add(valores);
            }
        } catch (SQLException ex) {
            Mensagens.exibirMensagem(null, "Erro!", "<html><p>Não foi possivel caregar "
                    + "os dados do banco!</p><p>Erro: " + ex.getMessage() + "</p></html>", TAM_FONTE_BTN_FORMULA);

        }
        return lista;
    }

    private String textoBotao(String string) {
        String formula = string;
        for (int i = 10; i > 1; i--) {
            formula = formula.replace("" + i, "<sub>" + i + "</sub>");
        }
        formula = "<html>"
                + "<style>"
                + "span{"
                + "font-weight:bold;"
                + "font-size:" + TAM_FONTE_BTN_FORMULA + "pt;"
                + "font-family:'Roboto CN','Calibri','Sans Serif';"
                + "text-shadow: 2px 2px 1px BLACK;"
                + "color:rgb(255,228,23);"
                + "}"
                + "</style>"
                + "<span>" + formula + "</span></html>";

        return formula;
    }

}
