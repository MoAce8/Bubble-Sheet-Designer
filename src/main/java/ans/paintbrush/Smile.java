
package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Smile extends Shape {
    public Smile(){
        super(0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x=Math.min(getP1().x, getP2().x);
        int y=Math.min(getP1().y, getP2().y);
        int w=Math.abs(getP1().x-getP2().x);
        int h=Math.abs(getP1().y-getP2().y);
        g.drawOval(x, y, w, w);
        
        g.drawOval(x+w/4, y+w/4, w/20, w/20);
        g.drawOval(x+w*3/4, y+w/4, w/20, w/20);
        g.fillOval(x+w/4, y+w/4, w/20, w/20);
        g.fillOval(x+w*3/4, y+w/4, w/20, w/20);
        g.drawArc(x+w/5, y+w/5, w*5/8,w*5/8 , 0, -180); 
        g.setColor(oldColor);
    }
    @Override
    public boolean IsPointInsideShape(Point p) {
        int x1 = Math.min(getP1().x, getP2().x);
        int y1 = Math.min(getP1().y, getP2().y);

        int x2 = Math.max(getP1().x, getP2().x);
        int y2 = Math.max(getP1().y, getP2().y);

        if ((p.x >= x1 && p.x <= x2) && (p.y >= y1 && p.y <= y2)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean IsPointInsideRect(Point p) {
        int x1 = Math.min(getP1().x, getP2().x);
        int y1 = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        if ((p.x >= x1 - 5 && p.x <= x1 + 5) && (p.y >= y1 - 5 && p.y <= y1 + 5)
                || (p.x >= x1 + w - 5 && p.x <= x1 + w + 5) && (p.y >= y1 - 5 && p.y <= y1 + 5)
                || (p.x >= x1 - 5 && p.x <= x1 + 5) && (p.y >= y1 + h - 5 && p.y <= y1 + h + 5)
                || (p.x >= x1 + w - 5 && p.x <= x1 + w + 5) && (p.y >= y1 + h - 5 && p.y <= y1 + h + 5)
                || (p.x >= x1 + w / 2 - 5 && p.x <= x1 + w / 2 + 5) && (p.y >= y1 - 5 && p.y <= y1 + 5)
                || (p.x >= x1 + w / 2 - 5 && p.x <= x1 + w / 2 + 5) && (p.y >= y1 + h - 5 && p.y <= y1 + h + 5)
                || (p.x >= x1 - 5 && p.x <= x1 + 5) && (p.y >= y1 + h / 2 - 5 && p.y <= y1 + h / 2 + 5)
                || (p.x >= x1 + w - 5 && p.x <= x1 + w + 5) && (p.y >= y1 + h / 2 - 5 && p.y <= y1 + h / 2 + 5)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toSVG() {
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        String svgString = String.format("<rect width=\"%d\" height=\"%d\" x=\"%d\" y=\"%d\" fill=\"white\" stroke-width=\"1\" stroke=\"#%s\" /> ",
                w, h, x, y, Integer.toHexString(getDrawColor().getRGB())
        );
        return svgString;
    }
    
}
