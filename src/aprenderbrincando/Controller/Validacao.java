package aprenderbrincando.Controller;

import static aprenderbrincando.Config.*;
import aprenderbrincando.Model.Dao.RankingDAO;
import aprenderbrincando.Model.Vo.RankingVO;
import aprenderbrincando.View.Execucao.Execucao;
import aprenderbrincando.View.Mensagens;

/**
 *
 * @author Vinicius Berto
 */
public class Validacao {

    private Partida partida;
    private Execucao tela;
    private Nivel nivel;
    private ControllerExecucao ce;

    public Validacao(Partida partida, Execucao tela, Nivel nivel, ControllerExecucao ce) {
        this.partida = partida;
        this.tela = tela;
        this.nivel = nivel;
        this.ce = ce;
    }

    public void validar(String resposta) {
        if (resposta.equals(partida.getFormulaAtual().getFormula())) {
            acerto();
        } else {
            erro();
        }
    }

    private void acerto() {
        partida.setPontuacao(partida.getPontuacao() + PONTOS_ACERTO);
        partida.setQtdAcertos(partida.getQtdAcertos() + 1);
        partida.setTotalAcertos();
        partida.setFormulaAtual(nivel.formulaAtual());
        partida.notificar();

        if (partida.getMaiorPontuacao() < partida.getPontuacao()) {
            partida.setMaiorPontuacao(partida.getPontuacao());
        }

        if (passarNivel()) {
            if (nivel.getId() == 2) {
                ce.getTelaExecucao().ocultarMenuLateral();
            }
            ce.removerBotoes();
            Mensagens.exibirMensagem(ce.getTelaExecucao(), "Parabéns", "Você passou para o nível " + nivel.getId(), Mensagens.MSG_INFORMACAO);
            PAUSA = true;
            nivel.gerarAtualizarListaAtual();
            inicializarMATRIZ(LINHAS, COLUNAS);
            ce.adicionarBotoes(nivel.criarBotoes());
            setTEMPO_BOTOES();
            ce.getTbtn().alterarLista(nivel.getListaBotoes());
            partida.setFormulaAtual(nivel.formulaAtual());
            tela.getMeio().repaint();
            PAUSA = false;

        } else {
            if (verificarFim()) {
                Mensagens.exibirMensagem(tela, "Parabéns", "Você acertou!", Mensagens.MSG_ACERTO);
            }
        }

    }

    private void erro() {
        partida.setPontuacao(partida.getPontuacao() - PONTOS_ERRO);
        if (verificarFim()) {
            Mensagens.exibirMensagem(tela, "Que pena", "Você errou!", Mensagens.MSG_ERRO);
            partida.setQtdErros(partida.getQtdErros() + 1);
        }
    }

    private boolean verificarFim() {
        boolean ver = true;
        if (partida.getPontuacao() <= 0) {
            partida.setPontuacao(0);
            partida.setQtdErros(partida.getQtdErros() + 1);
//            ce.salvar();
            PAUSA = true;
            ce.removerBotoes();
            Mensagens.exibirMensagem(tela, nivel, "Que pena, " + partida.getNomeJogador() + "!", "<html><center><p> Fim do jogo sua pontuação zerou!</p></center>"
                    + "<p></p><p>Maior pontuação: " + partida.getMaiorPontuacao()
                    + "</p><p>Nível: " + nivel.getId()
                    + "</p><p>Erros: " + partida.getQtdErros()
                    + "</p><p>Acertos: " + partida.getTotalAcertos()
                    + "</p></html>",
                    Mensagens.MSG_INFORMACAO);
            ce.encerrarPartida();
            ver = false;

        } else if (nivel.getId() == 8 && partida.getQtdAcertos() == 4) {
            // ce.salvar();
            PAUSA = true;
            ce.removerBotoes();
            Mensagens.exibirMensagem(tela, nivel, "Parabéns", "<html><center><p> Você chegou ao fim do jogo!</p></center>"
                    + "<p></p><p>Pontuação final: " + partida.getMaiorPontuacao()
                    + "</p><p>Nível: " + nivel.getId()
                    + "</p><p>Erros: " + partida.getQtdErros()
                    + "</p><p>Acertos: " + partida.getTotalAcertos()
                    + "</p></html>",
                    Mensagens.MSG_INFORMACAO);
            ce.encerrarPartida();
            ver = false;
        }
        return ver;
    }



    private boolean passarNivel() {
        boolean resp = false;

        if (partida.getQtdAcertos() == 4 && nivel.getId() != 8) {
            PONTOS_ERRO = PONTOS_ERRO + nivel.getId();
            partida.setQtdAcertos(0);
            nivel.incrementaId();
            resp = true;
            partida.notificar();
        }

        return resp;
    }

}
