package aprenderbrincando.Model.Dao;

import aprenderbrincando.Conexao;
import aprenderbrincando.Model.Vo.RankingVO;
import aprenderbrincando.View.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinícius Berto
 */
public class RankingDAO {

    public List<RankingVO> listar() {
        List<RankingVO> lista = new ArrayList<>();
        Connection conn = new Conexao().getConnection();
        Statement stmt;
        try {
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ranking";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RankingVO ranking = new RankingVO();
                ranking.setCod(rs.getInt("cod"));
                ranking.setNome(rs.getString("nome"));
                ranking.setPontuacao(rs.getInt("pontuacao"));
                ranking.setNivel(rs.getInt("nivel"));
                ranking.setErros(rs.getInt("erros"));
                ranking.setAcertos(rs.getInt("acertos"));
                lista.add(ranking);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Mensagens.exibirMensagem(null, "Erro!", "Falha ao carregar os dados do banco!<html><p>Erro: "+ex.getMessage()+"</p></html>", Mensagens.MSG_ERRO);
        }

        return lista;
    }

    public void salvar(RankingVO ranking) {
        Connection conn = new Conexao().getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ranking (nome,pontuacao,nivel,erros,acertos) VALUES (?,?,?,?,?)");
            ps.setString(1, ranking.getNome());
            ps.setInt(2, ranking.getPontuacao());
            ps.setInt(3, ranking.getNivel());
            ps.setInt(4, ranking.getErros());
            ps.setInt(5, ranking.getAcertos());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException se) {
            Mensagens.exibirMensagem(null, "Erro!", "<html><p>Falha ao salvar ranking!</p><p>Erro: " + se.getMessage() + "</p></html>", Mensagens.MSG_ERRO);
        }

    }

}
