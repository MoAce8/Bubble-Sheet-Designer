package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Line extends Shape {

    public Line() {
        this(0, 0, 0, 0);
    }

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        //To change body of generated methods, choose Tools | Templates.
        Color oldColor=g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);

        g.drawLine(getP1().x, getP1().y, getP2().x, getP2().y);

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

//        if (((y2 - y1) / (x2 - x1) == (p.y - y1) / (p.x - x1)
//                && (p.x >= x1 && p.x <= x2) && (p.y >= y1 && p.y <= y2))) {
        if((p.x >= x1 && p.x <= x2) && (p.y >= y1 && p.y <= y2)){
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
    public void correctPoints() {
    }
    
    
    
@Override
    public String toSVG() {
        String colorHex = String.format("#%02X%02X%02X", 
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String l1svg = String.format(""
                + "<line "
                + "x1=\"%d\" "
                + "y1=\"%d\" "
                + "x2=\"%d\" "
                + "y2=\"%d\" "
                + "stroke=\"%s\" "
                + "stroke-width=\"2\"/>",
                getP1().x,getP1().y,
                getP2().x,getP2().y,
                colorHex);
        return l1svg;
    }
    
    public static Shape loadSVG(org.w3c.dom.Node node) {
        Line line = new Line();
        NamedNodeMap map = node.getAttributes();
        Node x1Node = map.getNamedItem("x1");
        Node y1Node = map.getNamedItem("y1");
        Node x2Node = map.getNamedItem("x2");
        Node y2Node = map.getNamedItem("y2");
        Node stroke = map.getNamedItem("stroke");
        Node stroke_width = map.getNamedItem("stroke-width");

        line.getP1().x = Integer.parseInt(x1Node.getNodeValue());
        line.getP1().y = Integer.parseInt(y1Node.getNodeValue());
        line.getP2().x = Integer.parseInt(x2Node.getNodeValue());
        line.getP2().y = Integer.parseInt(y2Node.getNodeValue());
        line.setDrawColor(Color.decode(stroke.getNodeValue()));

        //System.out.println(line.toSVG());
        return line;
    }
}
