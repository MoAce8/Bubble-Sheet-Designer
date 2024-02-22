package ans.paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class CompositeShape extends Shape {

    private ArrayList<Shape> allShapes = new ArrayList<>();

    public void addAll(ArrayList<Shape> shapes) {
        allShapes.addAll(shapes);
        super.getP1().setLocation(getMinPoint());
        super.getP2().setLocation(getMaxPoint());

    }

    private Point getMaxPoint() {
        if (allShapes.size() > 0) {
            int max_x = allShapes.get(0).getP1().x;
            int max_y = allShapes.get(0).getP1().y;

            for (Shape s : allShapes) {
                if (max_x < s.getP2().x) {
                    max_x = s.getP2().x;
                }
                if (max_y < s.getP2().y) {
                    max_y = s.getP2().y;
                }
            }
            return new Point(max_x + 5, max_y + 5);
        }
        return new Point(0, 0);
    }

    private Point getMinPoint() {
        if (allShapes.size() > 0) {
            int min_x = allShapes.get(0).getP1().x;
            int min_y = allShapes.get(0).getP1().y;

            for (Shape s : allShapes) {
                if (min_x > s.getP1().x) {
                    min_x = s.getP1().x;
                }
                if (min_y > s.getP1().y) {
                    min_y = s.getP1().y;
                }
            }
            return new Point(min_x - 5, min_y - 5);
        }
        return new Point(0, 0);
    }

    @Override
    public void draw(Graphics g) {
        for (Shape s : allShapes) {
            s.draw(g);
        }
        if (isSelected()) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.drawRect(getP1().x, getP1().y, getP2().x - getP1().x, getP2().y - getP1().y);
            drawSelectBorder(g);
        }
    }

    @Override
    public void moveShapeBy(int dx, int dy) {
        super.moveShapeBy(dx, dy);
        for (Shape s : allShapes) {
            s.moveShapeBy(dx, dy);
        }
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the allShapes
     */
    public ArrayList<Shape> getAllShapes() {
        return allShapes;
    }

    /**
     * @param allShapes the allShapes to set
     */
    public void setAllShapes(ArrayList<Shape> allShapes) {
        this.allShapes = allShapes;
    }

    @Override
    public String toSVG() {
        return "";
    }

}
