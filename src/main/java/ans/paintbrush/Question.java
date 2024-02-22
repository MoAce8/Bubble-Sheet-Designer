/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ans.paintbrush;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author MoAce
 */
public class Question extends Shape {

    /**
     * @return the bubbles
     */
    public ArrayList<Shape> getBubbles() {
        return bubbles;
    }

    /**
     * @param bubbles the bubbles to set
     */
    public void setBubbles(ArrayList<Shape> bubbles) {
        this.bubbles = bubbles;
    }

    private int qNum;
    private int ansNum;
    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public Question() {
        super(0, 0, 0, 0);
    }

    public Question(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);

    }

    public void setPoints(Point p) {
        super.getP1().setLocation(new Point(p.x, p.y));
        super.getP2().setLocation(new Point(p.x + ansNum * 8 + (ansNum + 1) * 3, p.y + qNum * 8 + (qNum + 1) * 3));
    }

    private ArrayList<Shape> bubbles = new ArrayList<>();
    private int firstQnum=1;

    //  nq*10+(nq+1)*5
    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);
        Point cDrawPoint = new Point(p1.x, p1.y);
        Shape s;

        g.setFont(new Font("TimesRoman", Font.PLAIN, 6));

        g.setColor(getTheColor());

        //g.drawRect(x, y, w, h);
        for (int i = bubbles.size(); i < qNum; i++) {
            for (int j = 0; j < ansNum; j++) {
                s = new Circle();
                s.setP1(new Point(cDrawPoint.x + 3, cDrawPoint.y + 3));
                s.setP2(new Point(cDrawPoint.x + 11, cDrawPoint.y + 11));

                cDrawPoint.x += 11;
                bubbles.add(s);

                s = null;
            }

            cDrawPoint.x = x;
            cDrawPoint.y += 11;
        }

        //System.out.println(bubbles.size());
        //System.out.println(p1+""+p2);
        int index = 0;
        int i = getFirstQnum();
        for (Shape ss : bubbles) {
            if (index == 0) {
                g.drawString(i++ + "", ss.p1.x - 11, ss.p2.y - 2);
            }
            g.drawOval(ss.p1.x, ss.p1.y, 8, 8);
            g.drawString(letters[index++], ss.p1.x + 2, ss.p2.y - 1);
            if (index >= ansNum) {
                index = 0;
            }
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
    public void moveShapeBy(int dx, int dy) {
        super.moveShapeBy(dx, dy);
        for (Shape s : getBubbles()) {
            s.moveShapeBy(dx, dy);
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
        String svgString = String.format(""
        //                + "<rect "
        //                + "width=\"%d\" "
        //                + "height=\"%d\" "
        //                + "x=\"%d\" "
        //                + "y = \"%d\" "
        //                + "fill=\"white\" "
        //                + "stroke-width=\"2\" "
        //                + "stroke=\"%s\" />"
        //                + "\n",
        //                w, h, x, y, colorHex
        );

        int l = 0;
        int tnum = getFirstQnum();
        for (Shape s : getBubbles()) {
            int cx = Math.min(s.getP1().x, s.getP2().x);
            int cy = Math.min(s.getP1().y, s.getP2().y);
            int r = 8;
            String ltr;
            

            if (l < ansNum) {
                ltr = letters[l];
            } else {
                l = 0;
                ltr = letters[l];
            }

            String circlesvg = String.format(""
                    + "<circle "
                    + "w=\"%d\" "
                    + "h=\"%d\" "
                    + "cx=\"%d\" "
                    + "cy=\"%d\" "
                    + "r=\"%d\" "
                    + "fill=\"none\" "
                    + "stroke=\"%s\" "
                    + "stroke-width=\"1\"/>",
                    r, r, cx + r / 2, cy + r / 2, r / 2, colorHex);

            String textsvgString = String.format(""
                    + "<text "
                    + "x=\"%d\" "
                    + "y=\"%d\" "
                    + "font-family=\"TimesRoman\" "
                    + "class=\"%s\" "
                    + "font-size=\"%d\" "
                    + "text-anchor=\"start\" >"
                    + "%s"
                    + "</text>",
                    cx + 2, cy + 5, ltr, 4, ltr);

            String textNumber = String.format(""
                    + "<text "
                    + "x=\"%d\" "
                    + "y=\"%d\" "
                    + "font-family=\"TimesRoman\" "
                    + "class=\"%s\" "
                    + "font-size=\"%d\" "
                    + "text-anchor=\"start\" >"
                    + "%s"
                    + "</text>",
                    cx - 11, cy + 5, String.valueOf(tnum), 6, String.valueOf(tnum));

            svgString += "\t" + circlesvg + "\n" + textsvgString + "\n";
            if (l == 0) {
                svgString += "\t" + textNumber + "\n";
                tnum++;
            }
            
            l++;
        }
        return svgString;
    }

    /**
     * @return the qNum
     */
    public int getqNum() {
        return qNum;
    }

    /**
     * @param qNum the qNum to set
     */
    public void setqNum(int qNum) {
        this.qNum = qNum;
    }

    /**
     * @return the ansNum
     */
    public int getAnsNum() {
        return ansNum;
    }

    /**
     * @param ansNum the ansNum to set
     */
    public void setAnsNum(int ansNum) {
        this.ansNum = ansNum;
    }

    /**
     * @return the firstQnum
     */
    public int getFirstQnum() {
        return firstQnum;
    }

    /**
     * @param firstQnum the firstQnum to set
     */
    public void setFirstQnum(int firstQnum) {
        this.firstQnum = firstQnum;
    }

}
