package aprenderbrincando.Controller;

/**
 * @author Vinicius Berto
 */

import aprenderbrincando.Model.Vo.Valores;

public class Partida extends Observado {

    //Valores estatísticos
    private String nomeJogador;
    private int pontuacao;
    private int qtdAcertos;
    private int qtdErros;
    private int tempo;
    private Valores formulaAtual;
    private int maiorPontuacao;
    private int totalAcertos;

    public Partida(Observador ob, Valores fa, String nome) {
        super(ob);
        this.nomeJogador = nome;
        this.pontuacao = 10;
        this.qtdAcertos = 0;
        this.qtdErros = 0;
        this.tempo = 1;
        this.formulaAtual = fa;
        this.maiorPontuacao = 10;
        this.totalAcertos = 0;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        notificar();
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        this.qtdAcertos = qtdAcertos;
        notificar();
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo / 1000;
        notificar();
    }

    public Valores getFormulaAtual() {
        return formulaAtual;
    }

    public void setFormulaAtual(Valores formulaAtual) {
        this.formulaAtual = formulaAtual;
        notificar();
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
        notificar();
    }

    public int getMaiorPontuacao() {
        return maiorPontuacao;
    }

    public void setMaiorPontuacao(int maiorPontuacao) {
        this.maiorPontuacao = maiorPontuacao;
        notificar();
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public void setQtdErros(int qtdErros) {
        this.qtdErros = qtdErros;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos() {
        this.totalAcertos++;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }
    

    
}
