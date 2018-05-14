package aprenderbrincando.View;

/**
 * @author Vinicius Berto
 */
import aprenderbrincando.Config;
import aprenderbrincando.Controller.Observado;
import static aprenderbrincando.Recursos.obterCursor;
import static aprenderbrincando.Recursos.obterImagem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Mensagens extends JDialog {

    private static Mensagens msg;

    public static final int MSG_ACERTO = 0;
    public static final int MSG_ERRO_FORMULA = 1;
    public static final int MSG_INFORMACAO = 2;
    public static final int MSG_ERRO = -1;

    public static final int BTN_SIM = 3;
    public static final int BTN_NAO = 4;
    public static final int BTN_CANCELAR = 8;
    public static final int BTN_OK = 9;
    public static final float BTN_SIM_NAO = 10;
    public static final float BTN_SIM_NAO_CANCELAR = 11;

    public static final int DLG_CONFIRMACAO = 5;
    public static final int DLG_ENTRADA = 6;

    private JPanel pnlTopo;
    private JPanel pnlCorpo;
    private JPanel pnlBotoes;
    private JPanel pnlEntrada;

    private JLabel lblTitulo;
    private JLabel lblMensagem;
    private JLabel lblImagem;

    private JButton btnOk;
    private JButton btnSair;
    private JButton btnNao;
    private JButton btnSim;
    private JButton btnCancelar;

    private JTextField txtEntrada;

    private Color corTopo;
    private Color corFonte;
    private Color corFundo;
    private Color corBotao;
    private Color corFonteBotao;

    private String emoji;
    private final String titulo;
    private final String mensagem;

    private final Font fontePadrao = new Font("Arial Black", Font.BOLD, Config.convertTamanhoFonte(45));
    private Font fonteTitulo;
    private Font fonteMensagem;
    private Font fonteMensagemInformacao;
    private Font fonteBotao;
    private Font fonteCaixaTexto;

    private final int dialogo = 3;

    private int resposta_int = BTN_NAO;
    private String resposta_str = "";

    private Observado observado;

    private JFrame cpmnt;

    private Mensagens(JFrame cpmnt, String titulo, String mensagem) {
        super(cpmnt, true);
        this.mensagem = mensagem;
        this.titulo = titulo;
    }

    private void desenharCorpo(int tipo) {
        pnlCorpo = new JPanel();
        pnlEntrada = new JPanel();
        pnlBotoes = new JPanel();
        lblMensagem = new JLabel();
        lblImagem = new JLabel();
        btnOk = new JButton();
        btnCancelar = new JButton();
        btnSim = new JButton();
        btnNao = new JButton();
        txtEntrada = new JTextField();

        pnlCorpo.setBackground(corFundo);
        pnlBotoes.setBackground(corFundo);
        pnlEntrada.setBackground(corFundo);

        if (tipo == MSG_INFORMACAO || tipo == DLG_ENTRADA || tipo == DLG_CONFIRMACAO) {
            lblMensagem.setFont(fonteMensagemInformacao);
        } else {
            lblMensagem.setFont(fonteMensagem);
        }

        txtEntrada.setFont(fonteCaixaTexto);
        txtEntrada.setBackground(corTopo);
        txtEntrada.setBorder(null);
        txtEntrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnOk.doClick();
                }
            }

        });

        lblMensagem.setForeground(corFonte);
        lblMensagem.setHorizontalAlignment(JLabel.CENTER);
        lblMensagem.setText(mensagem);

        if (emoji != null) {
            lblImagem.setPreferredSize(new Dimension(114, 114));
            lblImagem.setHorizontalAlignment(JLabel.CENTER);
            lblImagem.setIcon(obterImagem(emoji, new Dimension(114, 114), Image.SCALE_SMOOTH));
        }

        if (tipo == MSG_ERRO_FORMULA || tipo == MSG_ACERTO || tipo == MSG_INFORMACAO) {
            pnlBotoes.setLayout(new GridLayout(3, 3));
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(btnOk);
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
        }

        btnSim.setBackground(corBotao);
        btnSim.setFont(fonteBotao);
        btnSim.setText("Sim");
        btnSim.setBorder(new LineBorder(corFonteBotao, 2));
        btnSim.setForeground(corFonteBotao);
        btnSim.addActionListener((ActionEvent e) -> {
            resposta_int = BTN_SIM;
            sair();
        });

        btnNao.setBackground(corBotao);
        btnNao.setFont(fonteBotao);
        btnNao.setText("Não");
        btnNao.setBorder(new LineBorder(corFonteBotao, 2));
        btnNao.setForeground(corFonteBotao);
        btnNao.addActionListener((ActionEvent e) -> {
            resposta_int = BTN_NAO;
            sair();
        });

        btnOk.setBackground(corBotao);
        btnOk.setFont(fonteBotao);
        btnOk.setText("OK");
        btnOk.setBorder(new LineBorder(corFonteBotao, 2));
        btnOk.setForeground(corFonteBotao);
        btnOk.addActionListener((ActionEvent e) -> {
            resposta_int = BTN_OK;
            resposta_str = txtEntrada.getText();
            sair();
        });

        btnCancelar.setBackground(corBotao);
        btnCancelar.setFont(fonteBotao);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new LineBorder(corFonteBotao, 2));
        btnCancelar.setForeground(corFonteBotao);
        btnCancelar.addActionListener((ActionEvent e) -> {
            resposta_int = BTN_CANCELAR;
            sair();
        });

        this.add(pnlCorpo, BorderLayout.CENTER);

    }

    private void construir(int tipo) {
        desenharFrame();
        desenharTopo();
        desenharCorpo(tipo);
    }

    public static int exibirDialogo(JFrame cpmnt, String titulo, String mensagem, float botoes) {
        msg = null;
        msg = new Mensagens(cpmnt, titulo, mensagem);
        msg.dlgConfirmacao(botoes);
        msg.setLocationRelativeTo(null);
        msg.setVisible(true);
        return msg.resposta_int;
    }

    public static int exibirDialogo(JFrame cpmnt, String titulo, String mensagem, int tipo, float botoes) {
        msg = null;
        msg = new Mensagens(cpmnt, titulo, mensagem);
        msg.dlgConfirmacao(botoes);
        msg.setLocationRelativeTo(null);
        msg.setVisible(true);
        return msg.resposta_int;
    }

    public static String exibirDialogoEntrada(JFrame frame, String titulo, String mensagem) {
        msg = null;
        msg = new Mensagens(frame, titulo, mensagem);
        msg.dlgEntrada();
        msg.setLocationRelativeTo(null);
        msg.setVisible(true);
        return msg.resposta_str;
    }

    public static void exibirMensagem(JFrame cpmnt, String titulo, String mensagem, int tipo) {
        exibirMensagem(cpmnt, null, titulo, mensagem, tipo);
    }

    public static void exibirMensagem(JFrame frame, Object observado, String titulo, String mensagem, int tipo) {
        msg = null;
        msg = new Mensagens(frame, titulo, mensagem);

        if (observado != null) {
            msg.observado = (Observado) observado;
        } else {
            msg.observado = null;
        }

        switch (tipo) {
            case MSG_ERRO_FORMULA:
                msg.msgErroFormula();
                break;
            case MSG_ACERTO:
                msg.msgAcerto();
                break;
            case MSG_INFORMACAO:
                msg.msgInformacao();
                break;
            case MSG_ERRO:
                msg.msgErro();
                break;
        }
        msg.setLocationRelativeTo(null);
        msg.setVisible(true);
    }

    private void msgErroFormula() {
        corTopo = new Color(227, 0, 0);
        corFonte = corTopo;
        corFundo = Color.BLACK;
        corBotao = new Color(127, 0, 0);
        emoji = "Emoji-Erro";
        construir(MSG_ERRO_FORMULA);
        mensagem();
    }

    private void msgErro() {
        corTopo = new Color(227, 0, 0);
        corFonte = corTopo;
        corFundo = Color.BLACK;
        corBotao = new Color(127, 0, 0);
        construir(MSG_INFORMACAO);
        mensagemInformacao();
    }

    private void msgAcerto() {
        corTopo = new Color(84, 153, 59);
        corFonte = corTopo;
        corFundo = Color.BLACK;
        corBotao = new Color(66, 119, 46);
        emoji = "Emoji-Acerto";
        construir(MSG_ACERTO);
        mensagem();
    }

    private void msgInformacao() {
        corTopo = new Color(255, 185, 1);
        corFonte = Color.BLACK;
        corFundo = new Color(255, 242, 181);
        corBotao = new Color(229, 165, 0);
        construir(MSG_INFORMACAO);
        mensagemInformacao();
        repaint();
    }

    private void dlgConfirmacao(float botoes) {
        corTopo = new Color(73, 179, 255);
        corFonte = corTopo;
        corFundo = Color.BLACK;
        corBotao = new Color(48, 116, 165);
        construir(DLG_CONFIRMACAO);
        dialogoConfirmacao(botoes);
        repaint();
    }

    private void dlgEntrada() {
        corTopo = new Color(73, 179, 255);
        corFonte = corTopo;
        corFundo = Color.BLACK;
        corBotao = new Color(48, 116, 165);
        construir(DLG_ENTRADA);
        dialogoEntrada();
        repaint();
    }

    private void mensagem() {
        pnlCorpo.setLayout(new java.awt.GridLayout(3, 1));
        pnlCorpo.add(lblMensagem);
        pnlCorpo.add(lblImagem);
        pnlCorpo.add(pnlBotoes);
        repaint();
    }

    private void mensagemInformacao() {
        pnlCorpo.setLayout(new BorderLayout());
        pnlCorpo.add(lblMensagem, BorderLayout.CENTER);
        pnlCorpo.add(pnlBotoes, BorderLayout.SOUTH);
        repaint();
    }

    private void desenharFrame() {
        setSize(Config.convertTamanho(49, 72));
        setAlwaysOnTop(true);
        setLayout(new java.awt.BorderLayout());
        setUndecorated(true);
        fonteMensagem = fontePadrao.deriveFont((float) Config.convertTamanhoFonte(45));
        fonteMensagemInformacao = fontePadrao.deriveFont((float) Config.convertTamanhoFonte(35));
        fonteBotao = fontePadrao.deriveFont((float) Config.convertTamanhoFonte(30));
        fonteTitulo = fontePadrao.deriveFont((float) Config.convertTamanhoFonte(30));
        fonteCaixaTexto = fontePadrao.deriveFont((float) Config.convertTamanhoFonte(30));
        setCursor(obterCursor("Cursor"));
    }

    private void desenharTopo() {

        pnlTopo = new JPanel();

        lblTitulo = new JLabel();
        btnSair = new JButton();

        pnlTopo.setPreferredSize(new Dimension(5, 36));
        pnlTopo.setLayout(new BorderLayout());
        pnlTopo.setBackground(corTopo);

        lblTitulo.setFont(fonteTitulo);
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setText("  " + titulo);
        pnlTopo.add(lblTitulo, BorderLayout.WEST);

        btnSair.setText("X");
        btnSair.setFont(fonteBotao);
        btnSair.setFocusCycleRoot(false);
        btnSair.setFocusPainted(false);
        btnSair.setBackground(new Color(0, 0, 0, 0));
        btnSair.setOpaque(false);
        btnSair.setBorderPainted(false);
        btnSair.setForeground(Color.BLACK);
        btnSair.setContentAreaFilled(false);
        btnSair.addActionListener((ActionEvent e) -> {
            sair();
        });

        pnlTopo.add(btnSair, BorderLayout.EAST);
        this.add(pnlTopo, BorderLayout.NORTH);
    }

    private void dialogoConfirmacao(float botoes) {

        setSize(Config.convertTamanho(49, 39));
        pnlCorpo.setBackground(corFundo);
        pnlCorpo.setLayout(new BorderLayout());
        pnlCorpo.add(lblMensagem, BorderLayout.CENTER);

        if (botoes == BTN_SIM_NAO) {
            pnlBotoes.setLayout(new GridLayout(3, 3, 0, 0));
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            JPanel pnlCentro = new JPanel();
            pnlCentro.setLayout(new GridLayout(1, 2, 10, 0));
            pnlCentro.add(btnSim);
            pnlCentro.add(btnNao);
            pnlCentro.setOpaque(false);
            pnlBotoes.add(pnlCentro);
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());

        } else if (botoes == BTN_SIM_NAO_CANCELAR) {
            pnlBotoes.setLayout(new GridLayout(3, 3, 10, 0));
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            JPanel pnlCentro = new JPanel();
            pnlCentro.setLayout(new GridLayout(1, 3, 10, 0));
            btnSim.setFont(fontePadrao.deriveFont(15f));
            btnNao.setFont(fontePadrao.deriveFont(15f));
            btnCancelar.setFont(fontePadrao.deriveFont(15f));
            btnCancelar.setMinimumSize(new Dimension(80,30));
            pnlCentro.add(btnSim);
            pnlCentro.add(btnNao);
            pnlCentro.add(btnCancelar);
            pnlCentro.setOpaque(false);
            pnlBotoes.add(pnlCentro);
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
            pnlBotoes.add(new JLabel());
        }

        pnlCorpo.add(pnlBotoes, BorderLayout.SOUTH);
        repaint();
    }

    private void dialogoEntrada() {
        setSize(Config.convertTamanho(49, 49));
        pnlCorpo.setBackground(corFundo);
        pnlCorpo.setLayout(new BorderLayout());
        pnlCorpo.add(lblMensagem, BorderLayout.NORTH);

        pnlEntrada.setLayout(new GridLayout(3, 3));
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(txtEntrada);
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());
        pnlEntrada.add(new JLabel());

        pnlCorpo.add(pnlEntrada, BorderLayout.CENTER);
        pnlBotoes.setLayout(new GridLayout(3, 4, 10, 0));
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(btnOk);
        pnlBotoes.add(btnCancelar);
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());
        pnlBotoes.add(new JLabel());

        pnlCorpo.add(pnlBotoes, BorderLayout.SOUTH);
        repaint();
    }

    private void sair() {
        if (observado != null) {
            observado.notificar();
        }
        setVisible(false);
    }

}
