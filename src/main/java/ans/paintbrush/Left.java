package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Left extends Shape {

//    private Color Color;
    public Left() {
        super(0, 0, 0, 0);
    }

    public Left(int x1, int y1, int x2, int y2) {
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
        int y1 = y + h / 2;

        int x2 = x + w / 2;
        int y2 = y;

        int x3 = x + w / 2;
        int y3 = y + h / 4;

        int x4 = x + w;
        int y4 = y + h / 4;

        int x5 = x + w;
        int y5 = y + h * 3 / 4;

        int x6 = x + w / 2;
        int y6 = y + h * 3 / 4;

        int x7 = x + w / 2;
        int y7 = y + h;
        if (isFilled()) {
            g.setColor(getTheColor());
            g.fillPolygon(new int[]{x1, x2, x3, x4, x5, x6, x7}, new int[]{y1, y2, y3, y4, y5, y6, y7}, 7);

        } else {
            g.setColor(getTheColor());
        }
        g.drawPolygon(new int[]{x1, x2, x3, x4, x5, x6, x7}, new int[]{y1, y2, y3, y4, y5, y6, y7}, 7);

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
        int y1 = y + h / 2;

        int x2 = x + w / 2;
        int y2 = y;

        int x3 = x + w / 2;
        int y3 = y + h / 4;

        int x4 = x + w;
        int y4 = y + h / 4;

        int x5 = x + w;
        int y5 = y + h * 3 / 4;

        int x6 = x + w / 2;
        int y6 = y + h * 3 / 4;

        int x7 = x + w / 2;
        int y7 = y + h;
                
         String colorHex = String.format("#%02X%02X%02X", 
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String Polysvg = String.format(""
                + "<polygon "
                + "class=\"Left\" "
                + "points = \"%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d\"  "
                + "fill=\"white\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />",                 
                     x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7,colorHex
                );
        return Polysvg;
        
    }
     public static Shape loadSVG(org.w3c.dom.Node node){
        Left left = new Left();
        NamedNodeMap map = node.getAttributes();
        Node pointsNode = map.getNamedItem("points");
        Node stroke  = map.getNamedItem("stroke");
        Node stroke_width  = map.getNamedItem("stroke-width");
        String []values = pointsNode.getNodeValue().split(",");
        left.getP1().x = Integer.parseInt(values[8]);
        left.getP1().y = Integer.parseInt(values[3]);
        left.getP2().x = Integer.parseInt(values[0]);
        left.getP2().y = Integer.parseInt(values[13]);
        left.setDrawColor(Color.decode(stroke.getNodeValue()));
        return left;
    }
}

//        String svgString = String.format("<polygon points= \" %d,%d  %d,%d  %d ,%d  %d ,%d  %d,%d  %d ,%d  %d ,%d  \" fill=\"white\" stroke-width=\"1\" stroke=\"black\" /> ",
//                x1, y1, x2, y2, x3, y3,x4,y4,x5,y5,x6,y6,x7,y7, Integer.toHexString(getDrawColor().getRGB())
//        );
//        return svgString;
//}
//}
