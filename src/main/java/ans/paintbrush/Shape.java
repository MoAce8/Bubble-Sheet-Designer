package ans.paintbrush;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.util.HashMap;
import java.util.Map;

public abstract class Shape implements Cloneable{

    Point p1;
    Point p2;
    private boolean selected;
    private boolean filled;
    private boolean group;
    private Color theColor;
    private Color drawColor;

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Shape() {
        this(new Point(), new Point());
    }

    public Shape(Point p1, Point p2) {
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
        this.drawColor =Color.black;
    }

    public Shape(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public abstract void draw(Graphics g);

    public abstract boolean IsPointInsideShape(Point p);

    public abstract boolean IsPointInsideRect(Point p);
    public abstract String toSVG();

    public void drawSelectBorder(Graphics g) {
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        Color oldColor = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(x, y, w, h);
        g.drawRect(x - 5, y - 5, 11, 11);
        g.drawRect(x - 5, y + h - 5, 11, 11);
        g.drawRect(x + w - 5, y + h - 5, 11, 11);
        g.drawRect(x + w - 5, y - 5, 11, 11);
        g.drawRect(x - 5, y + h / 2 - 5, 11, 11);
        g.drawRect(x + w / 2 - 5, y - 5, 11, 11);
        g.drawRect(x + w - 5, y + h / 2 - 5, 11, 11);
        g.drawRect(x + w / 2 - 5, y + h - 5, 11, 11);
        g.setColor(oldColor);
    }

    public void correctPoints() {

        int x_Min = Math.min(p1.x, p2.x);
        int x_Max = Math.max(p1.x, p2.x);
        int y_Min = Math.min(p1.y, p2.y);
        int y_Max = Math.max(p1.y, p2.y);
        p1.setLocation(x_Min, y_Min);
        p2.setLocation(x_Max, y_Max);
    }

    public boolean IsInUpperLeftCorner(Point pt) {
        return (pt.x >= p1.x - 5 && pt.x < p1.x + 5 && pt.y >= p1.y - 5 && pt.y < p1.y + 5);
    }

    public boolean IsInUpperLowerLeftCorner(Point pt) {
        return (pt.x >= p1.x - 5 && pt.x < p1.x + 5 && pt.y >= p2.y - 5 && pt.y < p2.y + 5);
    }

    public boolean IsInUpperMiddleLeftCorner(Point pt) {
        int Mid_y = (p2.y - p1.y) / 2 + p1.y;
        return (pt.x >= p1.x - 5 && pt.x < p1.x + 5 && pt.y >= Mid_y - 5 && pt.y <= Mid_y + 5);
    }

    public boolean IsInUpperRightCorner(Point pt) {
        return (pt.x >= p2.x - 5 && pt.x < p2.x + 5 && pt.y >= p1.y - 5 && pt.y < p1.y + 5);
    }

    public boolean IsInUpperLowerRightCorner(Point pt) {
        return (pt.x >= p2.x - 5 && pt.x < p2.x + 5 && pt.y >= p2.y - 5 && pt.y < p2.y + 5);
    }

    public boolean IsInUpperMiddleRightCorner(Point pt) {
        int Mid_y = (p2.y - p1.y) / 2 + p1.y;
        return (pt.x >= p2.x - 5 && pt.x < p2.x + 5 && pt.y >= Mid_y - 5 && pt.y <= Mid_y + 5);
    }

    public boolean IsInMiddleTopCorner(Point pt) {
        int Mid_y = (p2.y - p1.y) / 2 + p1.y;
        int Mid_x = (p2.x - p1.x) / 2 + p1.x;
        return (pt.x >= Mid_x && pt.x < Mid_x + 5 && pt.y >= p1.y - 5 && pt.y <= p1.y + 5);
    }

    public boolean IsInMiddleBottomCorner(Point pt) {
        int Mid_x = (p2.x - p1.x) / 2 + p1.x;
        return (pt.x >= Mid_x - 5 && pt.x < Mid_x + 5 && pt.y >= p2.y - 5 && pt.y <= p2.y + 5);
    }

    public Cursor getMouseShape(Point pt) {
        if (isSelected()) {
            if (IsInUpperLeftCorner(pt)) {
                return new Cursor(Cursor.NW_RESIZE_CURSOR);
            } else if (IsInUpperLowerLeftCorner(pt)) {
                return new Cursor(Cursor.SW_RESIZE_CURSOR);
            } else if (IsInUpperMiddleLeftCorner(pt)) {
                return new Cursor(Cursor.W_RESIZE_CURSOR);
            } else if (IsInUpperLowerRightCorner(pt)) {
                return new Cursor(Cursor.SE_RESIZE_CURSOR);
            } else if (IsInUpperRightCorner(pt)) {
                return new Cursor(Cursor.NE_RESIZE_CURSOR);
            } else if (IsInUpperMiddleRightCorner(pt)) {
                return new Cursor(Cursor.E_RESIZE_CURSOR);
            } else if (IsInMiddleTopCorner(pt)) {
                return new Cursor(Cursor.N_RESIZE_CURSOR);
            } else if (IsInMiddleBottomCorner(pt)) {
                return new Cursor(Cursor.S_RESIZE_CURSOR);
            }
            if (IsPointInsideShape(pt)) {
                return new Cursor(Cursor.HAND_CURSOR);
            }
        } else {
            if (IsPointInsideShape(pt)) {
                return new Cursor(Cursor.HAND_CURSOR);
            }
        }
        return null;

    }

    public boolean isFilled() {
        return filled;
    }

    /**
     * @param filled the filled to set
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * @return the theColor
     */
    public Color getTheColor() {
        return theColor;
    }

    /**
     * @param theColor the theColor to set
     */
    public void setTheColor(Color theColor) {
        this.theColor = theColor;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    public void moveShapeBy(int dx, int dy) {
        p1.setLocation(p1.x + dx, p1.y + dy);
        p2.setLocation(p2.x + dx, p2.y + dy);
    }

    public Map<String, String> getallProperties() {
        Map<String, String> allProperties = new HashMap<>();
        allProperties.put("X1", this.p1.x + "");
        allProperties.put("Y1", this.p1.y + "");
        allProperties.put("X2", this.p2.x + "");
        allProperties.put("Y2", this.p2.y + "");
        allProperties.put("filled", this.filled + "");

        return allProperties;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the group
     */
    public boolean isGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(boolean group) {
        this.group = group;
    }

    /**
     * @return the drawColor
     */
    public Color getDrawColor() {
        return drawColor;
    }

    /**
     * @param drawColor the drawColor to set
     */
    public void setDrawColor(Color drawColor) {
        this.drawColor = drawColor;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape shape = null;
        shape = (Shape) super.clone();
        return shape;
    }

}
