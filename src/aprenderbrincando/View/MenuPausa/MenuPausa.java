package aprenderbrincando.View.MenuPausa;

/**
 * @author Vinicius Berto
 */
import aprenderbrincando.Config;
import static aprenderbrincando.Config.TAM_TELA;
import aprenderbrincando.Recursos;
import aprenderbrincando.View.Mensagens;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author vinicius
 */
public class MenuPausa extends JDialog implements ActionListener, KeyListener {

    /**
     * Retorno sair do menu de pausa finaliza a aplicação
     */
    public static final int SAIR = 1;

    /**
     * Retorna sem alterar as informações e continua a partida
     */
    public static final int CANCELAR = 2;

    /**
     * Finaliza a partida e salva os dados atuais do jogador no ranking
     */
    public static final int ENCERRAR = 3;

    private static MenuPausa mp;

    private int resposta;

    public MenuPausa(JFrame frm) {
        super(frm, true);
        setUndecorated(true);
        setLayout(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setSize(TAM_TELA);
        setLocationRelativeTo(null);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

            }
        });
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0, 0, 0, 120));

        JPanel pnlCentroVertical = new JPanel();
        pnlCentroVertical.setLayout(null);
        pnlCentroVertical.setBackground(Color.black);
        pnlCentroVertical.setPreferredSize(new Dimension(Config.convertTamanhoLA(25, Config.ALTURA) * 3, getSize().height));
        pnlCentroVertical.setOpaque(false);

        JPanel pnlCentroHorizantal = new JPanel();
        pnlCentroHorizantal.setSize(new Dimension(Config.convertTamanhoLA(25, Config.ALTURA) * 3, Config.convertTamanhoLA(25, Config.ALTURA)));
        pnlCentroHorizantal.setBackground(new Color(56, 69, 79));
        pnlCentroHorizantal.setBorder(new LineBorder(new Color(100, 192, 125, 150), 5, true));

        int altVertical = getSize().height;
        int altHorizontal = (int) pnlCentroHorizantal.getSize().getHeight();
        altVertical = altVertical / 2;
        altHorizontal = altHorizontal / 2;
        int top = altVertical - altHorizontal;
        pnlCentroHorizantal.setLocation(0, top);

        pnlCentroHorizantal.setLayout(null);

        BotaoAcao btnCancelar = new BotaoAcao();
        btnCancelar.setName("cancelar");
        btnCancelar.setSize(pnlCentroHorizantal.getHeight() - 20, pnlCentroHorizantal.getHeight() - 20);
        btnCancelar.setLocation(10, 10);
        btnCancelar.pOriginal = btnCancelar.getLocation();
        btnCancelar.tOriginal = btnCancelar.getSize();
        btnCancelar.setIcon(Recursos.obterImagem("cancelar-normal", btnCancelar.getSize(), Image.SCALE_SMOOTH));
        btnCancelar.setRolloverIcon(Recursos.obterImagem("cancelar-hover", btnCancelar.getSize(), Image.SCALE_SMOOTH));
        btnCancelar.addActionListener(this);

        BotaoAcao btnSair = new BotaoAcao();
        btnSair.setName("sair");
        btnSair.setSize(pnlCentroHorizantal.getHeight() - 20, pnlCentroHorizantal.getHeight() - 20);
        btnSair.setLocation(30 + btnCancelar.getWidth(), 10);
        btnSair.pOriginal = btnSair.getLocation();
        btnSair.tOriginal = btnSair.getSize();
        btnSair.setIcon(Recursos.obterImagem("sair-normal", btnSair.getSize(), Image.SCALE_SMOOTH));
        btnSair.setRolloverIcon(Recursos.obterImagem("sair-hover", btnSair.getSize(), Image.SCALE_SMOOTH));
        btnSair.addActionListener(this);

        BotaoAcao btnEncerrar = new BotaoAcao();
        btnEncerrar.setName("encerrar");
        btnEncerrar.setSize(pnlCentroHorizantal.getHeight() - 20, pnlCentroHorizantal.getHeight() - 20);
        btnEncerrar.setLocation(btnSair.getX() + 20 + btnSair.getWidth(), 10);
        btnEncerrar.pOriginal = btnEncerrar.getLocation();
        btnEncerrar.tOriginal = btnEncerrar.getSize();
        btnEncerrar.setIcon(Recursos.obterImagem("encerrar-normal", btnEncerrar.getSize(), Image.SCALE_SMOOTH));
        btnEncerrar.setRolloverIcon(Recursos.obterImagem("encerrar-hover", btnEncerrar.getSize(), Image.SCALE_SMOOTH));
        btnEncerrar.addActionListener(this);

        pnlCentroHorizantal.add(btnCancelar);
        pnlCentroHorizantal.add(btnSair);
        pnlCentroHorizantal.add(btnEncerrar);

        pnlCentroVertical.add(pnlCentroHorizantal);
        add(pnlCentroVertical);

    }

    public static int exibirMenuPausa(JFrame frm) {
        mp = new MenuPausa(frm);
        mp.setVisible(true);
        return mp.resposta;
    }

    public void sair() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BotaoAcao btn = (BotaoAcao) e.getSource();
        if (btn.getName().equals("sair")) {
            if (Mensagens.exibirDialogo(null, "Tem certeza?", "<html><p>Se você sair agora, </p><p>perderá todo o progresso.</p></html>",Mensagens.BTN_SIM_NAO) == Mensagens.BTN_SIM) {
                System.exit(0);
            } else {
                sair();
            }
        } else if (btn.getName().equals("encerrar")) {
            this.resposta = ENCERRAR;
            sair();
        } else if (btn.getName().equals("cancelar")) {
            this.resposta = CANCELAR;
            sair();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
