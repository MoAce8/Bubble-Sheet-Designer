package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class RoundRect extends Shape {

    public RoundRect() {
        super(0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
          Color oldColor=g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        g.drawRoundRect(x, y, w, h, 30, 30);
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillRoundRect(x, y, w, h, 30, 30);
        } else {
            g.setColor(getTheColor());
        }
        g.drawRoundRect(x, y, w, h, 30, 30);

        if (isSelected()) {
             drawSelectBorder(g);
        }
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
        String colorHex = String.format("#%02X%02X%02X", 
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String RoundRect  = String.format(""
                + "<rect "
                + "width=\"%d\" "
                + "height=\"%d\" "
                + "x=\"%d\" "
                + "y = \"%d\" "
                + "rx=\"%d\" "
                + "ry=\"%d\" "
                + "fill=\"white\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />",                 
                    w,h,x,y,h/5,h/5,colorHex
                );
        return RoundRect ;
    }
     public static Shape loadSVG(org.w3c.dom.Node node){
        RoundRect roundrect = new RoundRect();
        NamedNodeMap map = node.getAttributes();
        Node widthNode = map.getNamedItem("width");
        Node heightNode = map.getNamedItem("height");
        Node xNode = map.getNamedItem("x");
        Node yNode = map.getNamedItem("y");
        Node stroke  = map.getNamedItem("stroke");
        Node stroke_width  = map.getNamedItem("stroke-width");
        Node fill  = map.getNamedItem("fill");
        
        roundrect.getP1().x = Integer.parseInt(xNode.getNodeValue());
        roundrect.getP1().y = Integer.parseInt(yNode.getNodeValue());
        roundrect.getP2().x = roundrect.getP1().x + Integer.parseInt(widthNode.getNodeValue());
        roundrect.getP2().y = roundrect.getP1().y + Integer.parseInt(heightNode.getNodeValue());
        roundrect.setDrawColor(Color.decode(stroke.getNodeValue()));
        
        return roundrect;
    }
    
}
//        String svgString = String.format("<rect width=\"%d\" height=\"%d\" x=\"%d\" y=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"white\" stroke-width=\"1\" stroke=\"black\" /> ",
//                w, h, x, y,rx,ry, Integer.toHexString(getDrawColor().getRGB())
//        );
//        return svgString;
//    }
//}
