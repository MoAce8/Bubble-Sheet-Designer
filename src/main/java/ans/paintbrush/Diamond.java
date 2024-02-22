package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Diamond extends Shape {

//    private Color Color;
    public Diamond() {
        super(0, 0, 0, 0);
    }

    public Diamond(int x1, int y1, int x2, int y2) {
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
        int x1 = x + w / 2;
        int y1 = y;
        int x2 = x;
        int y2 = y + h / 2;
        int x3 = x + w / 2;
        int y3 = y + h;
        int x4 = x + w;
        int y4 = y + h / 2;
        g.drawPolygon(new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4);

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
        int x1 = x + w / 2;
        int y1 = y;
        int x2 = x;
        int y2 = y + h / 2;
        int x3 = x + w / 2;
        int y3 = y + h;
        int x4 = x + w;
        int y4 = y + h / 2;

        String colorHex = String.format("#%02X%02X%02X", 
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String Polysvg = String.format(""
                + "<polygon "
                 + "class=\"Certain\" "
                + "points = \"%d,%d,%d,%d,%d,%d,%d,%d \"  "
                + "fill=\"white\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />",                 
                     x1, y1, x2, y2, x3, y3,x4,y4,colorHex
                );
        return Polysvg;
        
    }
    public static Shape loadSVG(org.w3c.dom.Node node){
        Diamond Cer = new Diamond();
        NamedNodeMap map = node.getAttributes();
        Node pointsNode = map.getNamedItem("points");
        Node stroke  = map.getNamedItem("stroke");
        Node stroke_width  = map.getNamedItem("stroke-width");
        
        String []values = pointsNode.getNodeValue().split(",");
        
        
        Cer.getP1().x = Integer.parseInt(values[2]);
        Cer.getP1().y = Integer.parseInt(values[1]);
        Cer.getP2().x = Integer.parseInt(values[6]);
        Cer.getP2().y = Integer.parseInt(values[5]);
        Cer.setDrawColor(Color.decode(stroke.getNodeValue()));
        return Cer;
    }

}
