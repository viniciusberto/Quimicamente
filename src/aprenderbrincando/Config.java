package aprenderbrincando;

/**
 * @author Vinicius Berto
 */

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import static java.lang.Math.round;

public class Config {

    /**
     * Localização do diretório de imagens dentro dos recursos da aplicação.
     */
    public static String DIR_IMAGENS;

    /**
     * Localização do diretório de cursores dentro dos recursos da aplicação.
     */
    public static String DIR_CURSOR;

    /**
     * Localização do diretório de fontes dentro dos recursos da aplicação.
     */
    public static String DIR_FONTE;

    /**
     * String que contém a localização do banco de dados.
     */
    public static String URL_BANCO;

    /**
     * String que contém o path da aplicação.
     */
    public static String DIR_APPLET;

    /**
     * String que contém o nome do arquivo binário da aplicação.
     */
    public static String JAR_FILE;

    /**
     * Referência para o método convertTamanhoLA int LARGURA = 1.
     */
    public static int LARGURA = 1;

    /**
     * Referência para a altura no método converte tamanhoLA int ALTURA = 2.
     */
    public static int ALTURA = 2;

    /**
     * Pontos que o jogador receberá toda vez que acertar.
     */
    public static int PONTOS_ACERTO;

    /**
     * Pontos que o jogador irá perder toda vez que errar uma fórmula.
     */
    public static int PONTOS_ERRO;

    /**
     * Tempo em que cada botão permanecerá na tela.
     */
    public static int TEMPO_BOTAO;

    /**
     * Define o status do reposicionamento dos botões na tela Quando = true: Os
     * botões não se movimentão na tela.
     */
    public static boolean PAUSA;

    private static final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static final GraphicsDevice[] GDS = GE.getScreenDevices();
    private static final GraphicsDevice GD = GDS[0];
    private static final DisplayMode dm = GD.getDisplayMode();

    /**
     * Resolução da tela em px.
     */
    public static final Dimension TAM_TELA = new Dimension(dm.getWidth(), dm.getHeight());

    /**
     * Serve para verificar a proporção da tela.
     */
    public static boolean WIDESCREEM;

    /**
     * Define o tamanho dos componentes da tela de execução.
     */
    public static Dimension TAM_NORTE_EXE, TAM_CENTRO_EXE, TAM_LATERAL_EXE;

    /**
     * Define a quantidade de linha e colunas da matriz de boolean que
     * representa os pixels da tela utilizada para evitar a sobreposição dos
     * botões de formula durante o reposicionamento.
     */
    public static int LINHAS, COLUNAS;

    /**
     * Matriz de boolean que representa os pixels da tela utilizada para evitar
     * a sobreposição dos botões de formula durante o reposicionamento.
     */
    public static boolean[][] MATRIZ;

    /**
     * Tamanho da fonte dos botões de fórmula.
     */
    public static int TAM_FONTE_BTN_FORMULA;

    /**
     * Tamanho da fonte dos labels de status da tela de execução ou seja, os que
     * exibem pontuação, fórmula atual e nível.
     */
    public static int TAM_FONTE_LBL_EXECUCAO;

    /**
     * Armazena a quantidade de botões que estão sendo exibidos na tela.
     */
    public static int QUANTIDADE_BOTOES;

    public Config() {
        DIR_APPLET = getClass().getResource("").toString();
        nomeArquivoJAR();
        
        /*Banco de dados*/
        String caminhoBanco = null;
        String resourceBanco = "/aprenderbrincando/Model/Database/";
        String resourceBancoFile = "Banco.db";

        String caminhoLib = null;
        String resourceLib = "/aprenderbrincando/Assets/lib/";
        String resourceLibFile = "sqlite-jdbc-3.20.0.jar";

        if (getClass().getResource("").toString().indexOf("build") > 0) {
            caminhoBanco = getClass().getResource("").toString() + "Save/";
            caminhoBanco = caminhoBanco.replace("file:", "");
            caminhoBanco = caminhoBanco.replace("/AprenderBrincando.jar!", "");
        } else {
            caminhoBanco = "Save/";
            caminhoLib = "lib/";
            Recursos.copiarArquivo(resourceLib, resourceLibFile, caminhoLib, "sqlite-jdbc-3.20.0", "jar");
        }
        URL_BANCO = Recursos.copiarArquivo(resourceBanco, resourceBancoFile, caminhoBanco, "saves", "dat");

        QUANTIDADE_BOTOES = 10;
        WIDESCREEM = false;
        PAUSA = false;

        /*Camada Controller*/
        PONTOS_ACERTO = 10;
        PONTOS_ERRO = 10;
        setTEMPO_BOTOES();

        /*Telas*/
        TAM_NORTE_EXE = new Dimension(convertTamanho(100, 4));
        TAM_CENTRO_EXE = new Dimension(convertTamanho(80, 96));
        TAM_LATERAL_EXE = new Dimension(convertTamanho(20, 96));
        LINHAS = TAM_CENTRO_EXE.height;
        COLUNAS = TAM_CENTRO_EXE.width;
        inicializarMATRIZ(LINHAS, COLUNAS);
        setTAM_FONTE_BTN_FORMULA();
        setTAM_FONTE_LBL_EXECUCAO();
        verificarEscalaDaTela();

        if (WIDESCREEM) {
            DIR_IMAGENS = "/aprenderbrincando/Assets/Imagens/16-9/";
        } else {
            DIR_IMAGENS = "/aprenderbrincando/Assets/Imagens/4-3/";
        }
        DIR_CURSOR = "/aprenderbrincando/Assets/Cursores/";
        DIR_FONTE = "/aprenderbrincando/Assets/Fontes/";

    }

    private void nomeArquivoJAR() {
        int INICIO = 0, FIM = 0;

        if (!DIR_APPLET.contains("build")) {
            INICIO = FIM = DIR_APPLET.indexOf(".jar");

            while (!DIR_APPLET.substring(INICIO, INICIO + 1).equals("/")) {
                INICIO--;
                if (INICIO == 0) {
                    break;
                }
            }
            JAR_FILE = DIR_APPLET.substring(INICIO + 1, FIM) + ".jar";
        } else {
            JAR_FILE = null;
        }
    }

    /**
     * convertTamanho converte porcentagem em valores inteiros referentes ao
     * tamanho e proporção da tela.
     *
     * @param valor1 porcentagem da largura da tela;
     * @param valor2 porcentagem da altura da tela;
     * @return Dimension
     */
    public static Dimension convertTamanho(int valor1, int valor2) {
        Double c1, c2, aux;
        if (Config.WIDESCREEM) {
            aux = (double) 1.33 * valor1;
            aux = (double) aux / 1.77;
            valor1 = round(aux.floatValue());

            aux = null;

            aux = (double) 1.33 * valor2;
            aux = (double) aux / 1.77;
            valor2 = round(aux.floatValue());
        }

        c1 = (double) valor1 / 100;
        c1 = (double) c1 * TAM_TELA.width;

        c2 = (double) valor2 / 100;
        c2 = (double) c2 * TAM_TELA.height;
        return new Dimension(round(c1.floatValue()), round(c2.floatValue()));
    }

    /**
     *
     * convertTamanhoLA converte porcentagem em valores inteiros referentes a
     * largura ou altura da tela.
     *
     * @param valor porcentagem da largura ou altura da tela;
     * @param ref referência para cálculo;
     * @return int
     */
    public static int convertTamanhoLA(int valor, int ref) {
        Double c1, c2, aux;
        if (Config.WIDESCREEM) {
            aux = (double) 1.3 * valor;
            aux = (double) aux / 1.7;
            valor = round(aux.floatValue());
        }
        c1 = (double) valor / 100;
        if (ref == 1) {
            c1 = (double) c1 * TAM_TELA.width;
        } else {
            c1 = (double) c1 * TAM_TELA.height;
        }
        return round(c1.floatValue());
    }

    /**
     * Faz a verificação da escala da tela através da proporção.
     */
    public static void verificarEscalaDaTela() {
        Double valor = (double) TAM_TELA.width / TAM_TELA.height;
        if (valor >= 1.6) {
            Config.WIDESCREEM = true;
        }
    }

    /**
     * Define o tamanho da fonte dos botões de fórmula da tela de execução de
     * acordo com a resolução da tela.
     */
    public static void setTAM_FONTE_BTN_FORMULA() {
        Double c1;
        if (TAM_TELA.height > 900 && TAM_TELA.width < 1500) {
            c1 = (double) 18 * TAM_TELA.height;
        } else {
            c1 = (double) 22 * TAM_TELA.height;
        }

        c1 = (double) c1 / 1024;
        Config.TAM_FONTE_BTN_FORMULA = round(c1.floatValue());
    }

    /**
     * Altera o tamanho da fonte recebido pelo paramentro de acordo com a
     * resolução da tela
     *
     * @param tamanho tamanho da fonte a ser alterado
     * @return retorna o tamanho proporciona a resolução da tela
     */
    public static int convertTamanhoFonte(int tamanho) {
        Double c1;
        c1 = (double) tamanho * TAM_TELA.height;
        c1 = (double) c1 / 1366;
        return round(c1.floatValue());
    }

    /**
     * Define o tamanho da fonte dos labels de status da tela de execução de
     * acordo com a resolução da tela.
     */
    public static void setTAM_FONTE_LBL_EXECUCAO() {
        Double c1;
        c1 = (double) 20 * TAM_TELA.height;
        c1 = (double) c1 / 1024;
        Config.TAM_FONTE_LBL_EXECUCAO = round(c1.floatValue());
    }

    /**
     * Popula a matriz de pixels com valores false.
     *
     * @param linhas
     * @param colunas
     */
    public static void inicializarMATRIZ(int linhas, int colunas) {
        MATRIZ = null;
        MATRIZ = new boolean[linhas][colunas];
        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                MATRIZ[l][c] = false;
            }
        }

    }

    /**
     * Altera a quantidade de linhas e colunas da matriz de pixels através dos
     * parametros
     *
     * @param linhas
     * @param colunas
     */
    public static void alterarLinhasColunas(int linhas, int colunas) {
        LINHAS = linhas;
        COLUNAS = colunas;
        TAM_CENTRO_EXE = new Dimension(colunas, linhas);
    }

    /**
     * Define o tempo em que cada botão irá ficar na tela o tempo padrão é 5s
     * por botão.
     */
    public static void setTEMPO_BOTOES() {
        TEMPO_BOTAO = 5000 / QUANTIDADE_BOTOES;
    }

    /**
     *Simplemente escreve uma string no console e encerra a aplicação
     * este método serve para auxiliar no processo de desenvolvimento
     * facilitando na coleta de detalhes sobre erros.
     * 
     * @param exibir
     */
    public static void DebugFunction(Object exibir) {
        System.out.println("");
        System.out.println("");
        System.out.println(exibir);
        System.out.println("");
        System.out.println("");
        System.exit(0);
    }

}
