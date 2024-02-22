package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Oval extends Shape {

//    private Color Color;
    public Oval() {
        super(0, 0, 0, 0);
    }

    public Oval(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
          Color oldColor=g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillOval(x, y, w, h);
        } else {
            g.setColor(getTheColor());
        }
        g.drawOval(x, y, w, h);

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
        String ellipsesvg = String.format(""
                + "<ellipse "
                + "cx=\"%d\" "
                + "cy=\"%d\" "
                + "rx=\"%d\" "
                + "ry=\"%d\" "
                + "fill=\"white\" "
                + "stroke=\"%s\" "
                + "stroke-width=\"2\"/>",
                  x + w / 2, y + h / 2, w / 2, h / 2,colorHex);
        return ellipsesvg;
    }
        public static Shape loadSVG(org.w3c.dom.Node node) {
           Oval oval = new Oval();
        NamedNodeMap map = node.getAttributes();
        Node widthNode = map.getNamedItem("width");
        Node heightNode = map.getNamedItem("height");
        Node cxNode = map.getNamedItem("cx");
        Node cyNode = map.getNamedItem("cy");
        Node rxNode = map.getNamedItem("rx");
        Node ryNode = map.getNamedItem("ry");
        Node stroke = map.getNamedItem("stroke");
        Node stroke_width = map.getNamedItem("stroke-width");
        Node fill = map.getNamedItem("fill");

        oval.getP1().x = Integer.parseInt(cxNode.getNodeValue()) - Integer.parseInt(rxNode.getNodeValue());
        oval.getP1().y = Integer.parseInt(cyNode.getNodeValue()) - Integer.parseInt(ryNode.getNodeValue());
        oval.getP2().x = Integer.parseInt(cxNode.getNodeValue()) + Integer.parseInt(rxNode.getNodeValue());
        oval.getP2().y = Integer.parseInt(cyNode.getNodeValue()) + Integer.parseInt(ryNode.getNodeValue());
        return oval;
        }
}
