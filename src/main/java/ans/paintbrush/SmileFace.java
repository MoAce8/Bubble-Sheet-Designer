
package ans.paintbrush;

import java.awt.Graphics;
import java.awt.Point;


public class SmileFace extends Shape {
    public SmileFace(){
        super(0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        
        int x=Math.min(getP1().x, getP2().x);
        int y=Math.min(getP1().y, getP2().y);
        int w=Math.abs(getP1().x-getP2().x);
        int h=Math.abs(getP1().y-getP2().y);
        g.drawOval(x, y, w, w);
        g.drawOval(x+w/4, y+w/4, w/20, w/20);
           
        g.drawOval(x+w*3/4, y+w/4, w/20, w/20);
        g.fillOval(x+w/4, y+w/4, w/20, w/20);
        g.fillOval(x+w*3/4, y+w/4, w/20, w/20);
        g.drawArc(x+w/4, y+w/4, w/2,w/2 , 0, -180); 
        g.setColor(getTheColor());
    }

    @Override
    public boolean IsPointInsideShape(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean IsPointInsideRect(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

          @Override
    public String toSVG() {
        return "";
    }
    
}
