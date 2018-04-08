package aprenderbrincando;

/**
 * Trocar por XML
 *
 * @author Vinicius Berto
 */
import aprenderbrincando.Controller.Partida;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.round;

public class Config {

    public static String URL_BANCO;

    /**
     * Ref para o método convertTamanhoLA
     */
    public static int LARGURA = 1;
    public static int ALTURA = 2;

    /*Valores Estáticos*/
    public static int PONTOS_ACERTO;
    public static int PONTOS_ERRO;
    public static int TEMPO_BOTAO;
    public static Partida PARTIDA_ATUAL;
    public static boolean PAUSA;

    /*Telas*/
    private static final Toolkit TOO = Toolkit.getDefaultToolkit();
    public static final Dimension TAM_TELA = new Dimension(1280, 1024);//TOO.getScreenSize();
    public static boolean WIDESCREEM;
    public static Dimension TAM_NORTE_EXE, TAM_SUL_EXE, TAM_CENTRO_EXE, TAM_LATERAL_EXE;
    public static int LINHAS, COLUNAS;
    public static boolean[][] MATRIZ;
    public static int TAM_FONTE_BTN_FORMULA;
    public static int TAM_FONTE_LBL_EXECUCAO;
    public static int QUANTIDADE_BOTOES;

    public static String DIR_IMAGENS;
    public static String DIR_CURSOR;
    public static String DIR_FONTE;

    public Config() {

        /*Banco de dados*/
        String caminho = /*getClass().getResource("").toString() + */"Save/";
        //caminho = caminho.replace("file:", "");
        //caminho = caminho.replace("/AprenderBrincando.jar!", "");
        //caminho = caminho.replace("aprenderbrincando/", "");
        
        File fl = new File(caminho);
        
        if (!fl.exists()) {
            fl.mkdirs();
            
            InputStream is = null;
            OutputStream os = null;
            try {
                is = getClass().getResourceAsStream("/aprenderbrincando/Model/Database/Banco.db");
                os = new FileOutputStream(caminho+"saves.dat");
                File templateFile = File.createTempFile(caminho+"saves", "dat");
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        URL_BANCO = caminho+"saves.dat";

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

    /**
     *
     * convertTamanho converte porcentagem em valores inteiros referentes ao
     * tamanho da tela.
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

    public static void verificarEscalaDaTela() {
        Double valor = (double) TAM_TELA.width / TAM_TELA.height;
        if (valor >= 1.6) {
            Config.WIDESCREEM = true;
        }
    }

    public static void setTAM_FONTE_BTN_FORMULA() {
        Double c1;
        if (TAM_TELA.height > 900 && TAM_TELA.width < 1500) {
            c1 = (double) 18 * TAM_TELA.height;
        } else {
            c1 = (double) 24 * TAM_TELA.height;
        }

        c1 = (double) c1 / 1024;
        Config.TAM_FONTE_BTN_FORMULA = round(c1.floatValue());
    }

    public static int convertTamanhoFonte(int tamanho) {
        Double c1;
        c1 = (double) tamanho * TAM_TELA.height;
        c1 = (double) c1 / 1366;
        return round(c1.floatValue());
    }

    public static void setTAM_FONTE_LBL_EXECUCAO() {
        Double c1;
        c1 = (double) 20 * TAM_TELA.height;
        c1 = (double) c1 / 1024;
        Config.TAM_FONTE_LBL_EXECUCAO = round(c1.floatValue());
    }

    /**
     * inicializarMATRIZ popula a matriz com valores false.
     *
     * @param linhas
     * @param colunas
     */
    public static void inicializarMATRIZ(int linhas, int colunas) {
        MATRIZ = null;
        MATRIZ = new boolean[linhas][colunas];
        //System.out.println("L x C: " + linhas + "x" + colunas);
        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                MATRIZ[l][c] = false;
            }
        }

    }

    public static void alterarLinhasColunas(int linhas, int colunas) {
        LINHAS = linhas;
        COLUNAS = colunas;
        TAM_CENTRO_EXE = new Dimension(colunas, linhas);
    }

    public static void setTEMPO_BOTOES() {
        TEMPO_BOTAO = 5000 / QUANTIDADE_BOTOES;
    }

}
