package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Circle extends Shape {

    public Circle() {
        super(0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int r = Math.abs(getP1().x - getP2().x);
        
        g.drawOval(x, y, r, r);
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillOval(x, y, r, r);
        } else {
            g.setColor(getTheColor());
        }
        if (isSelected()) {
        Color olddColor = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(x, y, r, r);
        g.drawRect(x - 5, y - 5, 11, 11);
        g.fillRect(x - 5, y - 5, 11, 11);
        g.drawRect(x - 5, y + r - 5, 11, 11);
        g.fillRect(x - 5, y + r - 5, 11, 11);
        g.drawRect(x + r - 5, y + r - 5, 11, 11);
        g.fillRect(x + r - 5, y + r - 5, 11, 11);
        g.drawRect(x + r - 5, y - 5, 11, 11);
        g.fillRect(x + r - 5, y - 5, 11, 11);
        g.drawRect(x - 5, y + r / 2 - 5, 11, 11);
        g.fillRect(x - 5, y + r / 2 - 5, 11, 11);
        g.drawRect(x + r / 2 - 5, y - 5, 11, 11);
        g.fillRect(x + r / 2 - 5, y - 5, 11, 11);
        g.drawRect(x + r - 5, y + r / 2 - 5, 11, 11);
        g.fillRect(x + r - 5, y + r / 2 - 5, 11, 11);
        g.drawRect(x + r / 2 - 5, y + r - 5, 11, 11);
        g.fillRect(x + r / 2 - 5, y + r - 5, 11, 11);
        g.setColor(olddColor);
        }
//        g.drawOval(x, y, w, w);
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
        String circlesvg = String.format(""
                + "<circle "
                + "w=\"%d\" "
                + "h=\"%d\" "
                + "cx=\"%d\" "
                + "cy=\"%d\" "
                + "r=\"%d\" "
                + "fill=\"white\" "
                + "stroke=\"%s\" "
                + "stroke-width=\"2\"/>",
                w, w, x + w / 2, y + w / 2, w / 2,colorHex);
        return circlesvg;
    }
 public static Shape loadSVG(org.w3c.dom.Node node){
        Circle Cir = new Circle();
                NamedNodeMap map = node.getAttributes();
        Node widthNode = map.getNamedItem("width");
        Node heightNode = map.getNamedItem("height");
        Node cxNode = map.getNamedItem("cx");
        Node cyNode = map.getNamedItem("cy");
        Node r = map.getNamedItem("r");
        Node stroke  = map.getNamedItem("stroke");
        Node stroke_width  = map.getNamedItem("stroke-width");
        Node fill  = map.getNamedItem("fill");
        
         Cir.getP1().x = Integer.parseInt(cxNode.getNodeValue()) - Integer.parseInt(r.getNodeValue());
        Cir.getP1().y = Integer.parseInt(cyNode.getNodeValue()) - Integer.parseInt(r.getNodeValue());
        Cir.getP2().x = Integer.parseInt(cxNode.getNodeValue()) + Integer.parseInt(r.getNodeValue());
        Cir.getP2().y = Integer.parseInt(cyNode.getNodeValue()) + Integer.parseInt(r.getNodeValue());
        Cir.setDrawColor(Color.decode(stroke.getNodeValue()));
        return Cir;
    }

}