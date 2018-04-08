/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aprenderbrincando;

import static aprenderbrincando.Config.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author vinicius
 */
public class Recursos {

    private static Recursos RECURSOS = new Recursos();
    private static Toolkit TOO = Toolkit.getDefaultToolkit();

    /**
     * Método responsável por redimensionar imagens de acordo com as proporções
     * passadas pelos parametros;
     *
     * @param imagem Caminho da imagem a ser redimencionada
     * @param tamanho Dimensão/Formato da imagem
     * @param escala Forma como a imagem será redimensionada utilidada da clase
     * Image
     * @return retorna um ImageIcon
     */
    public static ImageIcon obterImagem(String imagem, Dimension tamanho, int escala) {
        ImageIcon img = new ImageIcon(RECURSOS.getClass().getResource(DIR_IMAGENS + imagem+".png"));
        int altura = tamanho.height;
        int largura = tamanho.width;

        return new ImageIcon(img.getImage().getScaledInstance(largura, altura, escala));
    }

    public static Cursor obterCursor(String nome) {
        return  TOO.createCustomCursor(new ImageIcon(RECURSOS.getClass().getResource(DIR_CURSOR+nome+".png")).getImage(), new Point(0, 0), "Ponteiro");
        
    }
    
    public static Font obterFonte(String nome,int estilo, float tamanho){
            Font fnt = new Font("Arial", Font.PLAIN, 15);
            InputStream is = RECURSOS.getClass().getResourceAsStream(DIR_FONTE + nome+".ttf");
        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (FontFormatException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fnt.deriveFont(tamanho);
    }

}
