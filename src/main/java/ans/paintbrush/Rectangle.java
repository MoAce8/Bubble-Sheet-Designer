package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Rectangle extends Shape {

    public Rectangle() {
        super(0, 0, 0, 0);
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);

    }
    
    Color color = null;

    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        g.drawRect(x, y, w, h);
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillRect(x, y, w, h);
        } else {
            g.setColor(getTheColor());
            g.drawRect(x, y, w, h);
        }

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

        //    <rect width="80" height="350" x="30" y = "30" rx="20" ry="20"  fill="white" stroke-width="1" stroke="#EA923E" />
        String colorHex = String.format("#%02X%02X%02X",
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String fillColor = "none";
        if (isFilled()) {
            fillColor = colorHex;
        }
        String rect = String.format(""
                + "<rect "
                + "width=\"%d\" "
                + "height=\"%d\" "
                + "x=\"%d\" "
                + "y = \"%d\" "
                + "fill=\"%s\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />",
                w, h, x, y, fillColor, colorHex
        );
        return rect;
    }

    public static Shape loadSVG(org.w3c.dom.Node node) {
        Rectangle rect = new Rectangle();
        NamedNodeMap map = node.getAttributes();
        Node widthNode = map.getNamedItem("width");
        Node heightNode = map.getNamedItem("height");
        Node xNode = map.getNamedItem("x");
        Node yNode = map.getNamedItem("y");
        Node stroke = map.getNamedItem("stroke");
        Node stroke_width = map.getNamedItem("stroke-width");
        Node fill = map.getNamedItem("fill");

        rect.getP1().x = Integer.parseInt(xNode.getNodeValue());
        rect.getP1().y = Integer.parseInt(yNode.getNodeValue());
        rect.getP2().x = rect.getP1().x + Integer.parseInt(widthNode.getNodeValue());
        rect.getP2().y = rect.getP1().y + Integer.parseInt(heightNode.getNodeValue());
        rect.setDrawColor(Color.decode(stroke.getNodeValue()));

        return rect;
    }
}
