/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.register;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type to "image/jpeg"
        response.setContentType("image/jpeg");
        
        // Create a buffered image
        int width = 100;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Create a graphics context for the buffered image
        Graphics2D g = bufferedImage.createGraphics();
        
        // Set the background color
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        
        // Set the font
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        // Generate the random code
        String code = getRandomString(5);
        
        // Draw the code on the buffered image
        for (int i = 0; i < code.length(); i++) {
            g.setColor(getRandomColor(0, 100));
            g.drawString(code.charAt(i) + "", (i * 20) + 10, 25);
        }
        
        // Store the code in the session
        request.getSession().setAttribute("captcha", code);
        
        // Draw some random lines
        g.setColor(getRandomColor(100, 200));
        for (int i = 0; i < 5; i++) {
            int x1 = getRandomNumber(0, width);
            int y1 = getRandomNumber(0, height);
            int x2 = getRandomNumber(0, width);
            int y2 = getRandomNumber(0, height);
            g.drawLine(x1, y1, x2, y2);
        }
        
        // Dispose the graphics context
        g.dispose();
        
        // Write the buffered image to the output stream
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpeg", out);
        out.flush();
    }
    
    private Color getRandomColor(int min, int max) {
        Random random = new Random();
        int red = min + random.nextInt(max - min);
        int green = min + random.nextInt(max - min);
        int blue = min + random.nextInt(max - min);
        return new Color(red, green, blue);
    }
    
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }
    
    private String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomNumber = getRandomNumber(0, 26);
            char character = (char) (randomNumber + 'a');
            builder.append(character);
        }
        return builder.toString();
    }
}

