package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class RightTriangle extends Shape {

    public RightTriangle() {
        super(0, 0, 0, 0);
    }

    public RightTriangle(int x1, int y1, int x2, int y2) {
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

        int x1 = x;
        int y1 = y;
        int x2 = x;
        int y2 = y + h;
        int x3 = x + w;
        int y3 = y + h;
        g.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        } else {
            g.setColor(getTheColor());
        }
        g.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);

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

        int x1 = x;
        int y1 = y;
        int x2 = x;
        int y2 = y + h;
        int x3 = x + w;
        int y3 = y + h;
        String colorHex = String.format("#%02X%02X%02X", 
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String Polysvg = String.format(""
                + "<polygon "
                + "class=\"RightTriangle\" "
                + "points = \"%d,%d,%d,%d,%d,%d\"  "
                + "fill=\"white\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />",                 
                     x1, y1, x2, y2, x3, y3,colorHex
                );
        return Polysvg;
        
    }
     public static Shape loadSVG(org.w3c.dom.Node node){
        RightTriangle triangle = new RightTriangle();
        NamedNodeMap map = node.getAttributes();
        Node pointsNode = map.getNamedItem("points");
        Node stroke  = map.getNamedItem("stroke");
        Node stroke_width  = map.getNamedItem("stroke-width");
        
        String []values = pointsNode.getNodeValue().split(",");
        
        
        triangle.getP1().x = Integer.parseInt(values[2]);
        triangle.getP1().y = Integer.parseInt(values[1]);
        triangle.getP2().x = Integer.parseInt(values[4]);
        triangle.getP2().y = Integer.parseInt(values[5]);
        triangle.setDrawColor(Color.decode(stroke.getNodeValue()));
        return triangle;
    }
}
//               String svgString = String.format("<polygon points= \" %d,%d  %d,%d  %d ,%d  \" fill=\"white\" stroke-width=\"1\" stroke=\"black\" /> ",
//                x1, y1, x2, y2, x3, y3, Integer.toHexString(getDrawColor().getRGB())
//        );
//        return svgString;
//}
//}
