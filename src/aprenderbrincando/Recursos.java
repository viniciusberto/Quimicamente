package aprenderbrincando;

import static aprenderbrincando.Config.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Classe responsável por recuperar os arquivos de recursos da aplicação
 *
 * @author vinicius
 */
public class Recursos {

    private static final Recursos RECURSOS = new Recursos();
    private static final Toolkit TOO = Toolkit.getDefaultToolkit();

    /**
     * Método responsável por obter uma imagem dos recursos da aplicação e
     * redimensionar de acordo com as proporções passadas pelos parametros;
     *
     * @param imagem Caminho da imagem a ser redimencionada
     * @param tamanho Dimensão/Formato da imagem
     * @param escala Forma como a imagem será redimensionada pode se
     * Image.SCALE_REPLICATE, Image.SCALE_SMOOTH, Image.SCALE_FAST e
     * Image.SCALE_DEFAULT o ideal é utilizar o Image.SCALE_SMOOTH pois
     * redimensiona com melhor qualidade
     * @return retorna um ImageIcon
     */
    public static ImageIcon obterImagem(String imagem, Dimension tamanho, int escala) {
        ImageIcon img = new ImageIcon(RECURSOS.getClass().getResource(DIR_IMAGENS + imagem + ".png"));
        int altura = tamanho.height;
        int largura = tamanho.width;

        return new ImageIcon(img.getImage().getScaledInstance(largura, altura, escala));
    }

    /**
     * Método responsável por obter um cursor de mouse dos recursos da
     * aplicação.
     *
     * @param nome nome do cursor a ser utilizado sem extensão.
     * @return retorna um Cursor
     */
    public static Cursor obterCursor(String nome) {
        return TOO.createCustomCursor(new ImageIcon(RECURSOS.getClass().getResource(DIR_CURSOR + nome + ".png")).getImage(), new Point(0, 0), "Ponteiro");
    }

    /**
     * Obtem uma fonte dos recursos da aplicação
     *
     * @param nome Nome do arquivo de fonte
     * @param estilo Estilo da fonte pode ser Font.BOLD, Font.ITALIC, Font.PLAIN
     * etc...
     * @param tamanho Tamanho da fonte
     * @return Uma fonte
     */
    public static Font obterFonte(String nome, int estilo, float tamanho) {
        Font fnt = new Font("Arial", Font.PLAIN, 15);
        InputStream is = RECURSOS.getClass().getResourceAsStream(DIR_FONTE + nome + ".ttf");

        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fnt.deriveFont(tamanho);
    }

    /**
     * Copia um arquivo de um local para outro através do parametros
     *
     * @param caminhoOrigem Path origem de onde o arquivo será copiado;
     * @param nomeOrigem Nome do arquivo a ser copiado com a extensão e o
     * (ponto)"." Exemplo: ".jar";
     * @param caminhoDestino Path de destino onde o arquivo será colado;
     * @param nomeDestino Nome de destino do arquivo a ser colado sem a
     * extensão;
     * @param formatoDestino Extensão sem o (ponto) "." do arquivo a ser colado
     * Exemplo: "jar";
     * @return
     */
    public static String copiarArquivo(String caminhoOrigem, String nomeOrigem, String caminhoDestino, String nomeDestino, String formatoDestino) {
        String retorno = null;
        File fl;
        fl = new File(caminhoDestino);
        JButton btn = new JButton();
        if (!fl.exists()) {
            fl.mkdirs();
            InputStream is;
            OutputStream os;
            try {
                is = btn.getClass().getResourceAsStream(caminhoOrigem + nomeOrigem);
                os = new FileOutputStream(caminhoDestino + nomeDestino + "." + formatoDestino);
                File templateFile = File.createTempFile(caminhoDestino + nomeDestino, formatoDestino);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.close();
                retorno = caminhoDestino + nomeDestino + "." + formatoDestino;
                if (nomeDestino.contains("jdbc") && Config.JAR_FILE != null) {
                    try {
                        Process Processo = Runtime.getRuntime().exec("java -jar " + Config.JAR_FILE);
                    } catch (IOException MensagemdeErro) {
                        System.out.println(MensagemdeErro);
                    }
                    System.exit(0);
                }
            } catch (FileNotFoundException ex) {
                //Implementar sistema de log
            } catch (IOException | NullPointerException ex) {
                //Implementar sistema de log
            }
        } else {
            retorno = caminhoDestino + nomeDestino + "." + formatoDestino;
        }
        return retorno;
    }

}
