/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aprenderbrincando.View.Ranking;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import static aprenderbrincando.Config.*;
import static aprenderbrincando.Controller.ControllerExecucao.getTa;
import aprenderbrincando.Model.Dao.RankingDAO;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import aprenderbrincando.Model.Vo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author vinio
 */
public class Ranking extends JDialog {

    private JPanel pnlTopo;
    private JLabel lblTitulo;
    private JButton btnSair;

    private JPanel pnlCorpo;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo;
    private JTable tblTabela;
    private JButton btnOk;

    public Ranking(JFrame tela) {
        super(tela, true);
        desenharFrame();
    }

    public static void exibirRanking(JFrame frm) {
        Ranking r = new Ranking(frm);
        r.setVisible(true);
    }

    private void desenharFrame() {
        setCursor(CURSOR);
        setSize(convertTamanho(50, 80));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true);
        desenharCorpo();
        desenharTopo();
        setAlwaysOnTop(true);
    }

    private void desenharTopo() {
        pnlTopo = new JPanel();
        pnlTopo.setPreferredSize(convertTamanho(4, 8));
        pnlTopo.setBackground(new Color(172, 36, 193));
        pnlTopo.setLayout(new BorderLayout());
        pnlTopo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        lblTitulo = new JLabel(" Ranking");
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setFont(FONTE_PADRAO);
        pnlTopo.add(lblTitulo, BorderLayout.WEST);

        btnSair = new JButton("X ");
        btnSair.setFont(FONTE_PADRAO);
        btnSair.setForeground(Color.BLACK);
        btnSair.setOpaque(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setBorder(null);
        btnSair.setFocusPainted(false);
        btnSair.setHorizontalTextPosition(JButton.LEFT);
        btnSair.addKeyListener(getTa());
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        pnlTopo.add(btnSair, BorderLayout.EAST);

        add(pnlTopo, BorderLayout.NORTH);
    }

    private void desenharCorpo() {
        pnlCorpo = new JPanel(new BorderLayout());
        pnlCorpo.setBackground(new Color(222, 143, 234));
        add(pnlCorpo, BorderLayout.CENTER);

        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.setOpaque(false);
        desenharTabela();
        barraRolagem.setOpaque(false);
        pnlTabela.add(barraRolagem, BorderLayout.CENTER);
        pnlCorpo.add(pnlTabela, BorderLayout.CENTER);

        JPanel pnlOk = new JPanel(new GridLayout(1, 3));
        pnlOk.setOpaque(false);
        btnOk = new JButton("OK");
        btnOk.setFont(FONTE_PADRAO);
        btnOk.setForeground(Color.BLACK);
        btnOk.setBackground(new Color(172, 36, 193));
        btnOk.setBorder(new LineBorder(Color.BLACK, 2));
        btnOk.addKeyListener(getTa());
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        pnlOk.setPreferredSize(convertTamanho(4, 10));
        JPanel pnlL1 = new JPanel(new BorderLayout(50, 0));
        pnlL1.add(new JLabel(" "), BorderLayout.NORTH);
        pnlL1.add(new JLabel(" "), BorderLayout.SOUTH);
        pnlL1.add(new JLabel(" "), BorderLayout.EAST);
        pnlL1.add(new JLabel(" "), BorderLayout.WEST);
        pnlL1.add(btnOk, BorderLayout.CENTER);
        pnlL1.setOpaque(false);
        pnlOk.add(new JLabel());
        pnlOk.add(pnlL1);
        pnlOk.add(new JLabel());
        pnlCorpo.add(pnlOk, BorderLayout.SOUTH);
    }

    private void desenharTabela() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Pontos");
        modelo.addColumn("Nível");
        modelo.addColumn("Erros");
        modelo.addColumn("Acertos");
        preencher(modelo);
        tblTabela = new JTable(modelo);
        tblTabela.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ESCAPE) {
                    sair();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        barraRolagem = new JScrollPane(tblTabela);
    }

    private void preencher(DefaultTableModel dtm) {
        dtm.setNumRows(0);
        RankingDAO dao = new RankingDAO();

        for (RankingVO r : dao.listar()) {
            dtm.addRow(new Object[]{r.getNome(),
                r.getPontuacao(), r.getNivel(), r.getErros(), r.getAcertos()});
        }

    }

    private void sair() {
        setVisible(false);
    }

}
