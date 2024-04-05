/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author PC
 */
public class ImageBooks {
    public static Image reSize(Image originImage, int width, int height){
        Image resultImage = originImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resultImage;
    }
    
    public static byte[] arrayBooks(Image image, String type) throws IOException{
        BufferedImage bfi1 = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bfi1.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bfi1, type, baos);
        byte[] imageByte = baos.toByteArray();
        
        return imageByte;
    }
    
    public static Image createImageKim(byte[] data, String type) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        BufferedImage bfi2 = ImageIO.read(bais);
        
        Image image = bfi2.getScaledInstance(bfi2.getWidth(), bfi2.getHeight(), Image.SCALE_SMOOTH);
        return image;
    }
}
