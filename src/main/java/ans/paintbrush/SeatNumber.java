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
public class SeatNumber extends Shape {

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

    private int vNumbersNum = 10;
    private int hNumbersNum = 10;
    //String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public SeatNumber() {
        super(0, 0, 0, 0);
    }

    public SeatNumber(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);

    }

    public void setPoints(Point p) {
        super.getP1().setLocation(new Point(p.x, p.y));
        super.getP2().setLocation(new Point(p.x + hNumbersNum * 8 + (hNumbersNum + 1) * 3, p.y + vNumbersNum * 8 + (vNumbersNum + 1) * 3));
    }

    private ArrayList<Shape> bubbles = new ArrayList<>();
    //private int firstQnum=1;

    //  nq*10+(nq+1)*5
    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x = getP1().x;
        int y = getP1().y;
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y) + 32;
        Point cDrawPoint = new Point(p1.x, p1.y + 32);
        Shape s;

        g.setColor(getTheColor());

        //g.drawRect(x, y, w, h);
        for (int i = bubbles.size(); i < vNumbersNum; i++) {
            for (int j = 0; j < hNumbersNum; j++) {
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

        System.out.println(bubbles.size());
        //System.out.println(p1+""+p2);
        int index = 0;
        int nmbr = 0;

        //super.getP2().setLocation(new Point(x + hNumbersNum * 8 + (hNumbersNum + 1) * 3, y + vNumbersNum * 8 + (vNumbersNum + 1) * 3 + 32));
        g.drawRect(x, y, w, 27);
        g.drawRect(x, y + 32, w, h - 32);
        int rindex = x + 1;
        for (int i = 0; i < 9; i++) {
            g.drawRect(rindex, y + 12, 11, 15);
            rindex += 11;
        }
        g.drawRect(rindex, y + 12, 12, 15);
//        g.drawRect(x + 1, y + 12, 11, 15);
//        g.drawRect(x + 12, y + 12, 11, 15);
//        g.drawRect(x + 23, y + 12, 11, 15);
//        g.drawRect(x + 34, y + 12, 11, 15);
//        g.drawRect(x + 45, y + 12, 11, 15);
//        g.drawRect(x + 56, y + 12, 12, 15);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
        g.drawString("Seat Number", x + 34, y + 10);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 6));

        for (Shape ss : bubbles) {
            g.drawOval(ss.p1.x, ss.p1.y, 8, 8);
            g.drawString(nmbr + "", ss.p1.x + 2, ss.p2.y - 1);
            index++;
            if (index == 10) {
                nmbr++;
                index = 0;
            }
        }
        if (isSelected()) {
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
        g.setColor(oldColor);
    }

    @Override
    public boolean IsPointInsideShape(Point p) {
        int x1 = Math.min(getP1().x, getP2().x);
        int y1 = Math.min(getP1().y, getP2().y);

        int x2 = Math.max(getP1().x, getP2().x);
        int y2 = Math.max(getP1().y, getP2().y) + 32;

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
        int h = Math.abs(getP1().y - getP2().y) + 32;
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
        int h = Math.abs(getP1().y - getP2().y) + 32;

        String colorHex = String.format("#%02X%02X%02X",
                super.getDrawColor().getRed(),
                super.getDrawColor().getGreen(),
                super.getDrawColor().getBlue());
        String svgString = String.format(""
                + "<rect "
                + "width=\"%d\" "
                + "height=\"%d\" "
                + "x=\"%d\" "
                + "y = \"%d\" "
                + "fill=\"none\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />"
                + "\n",
                w, 26, x, y, colorHex
        );

        String svgBigRect = String.format(""
                + "<rect "
                + "width=\"%d\" "
                + "height=\"%d\" "
                + "x=\"%d\" "
                + "y = \"%d\" "
                + "fill=\"none\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />"
                + "\n",
                w, h - 32, x, y + 32, colorHex
        );

        int rindex = x+1;
        for (int i=0;i<9;i++) {
            String svgRect1 = String.format(""
                    + "<rect "
                    + "width=\"%d\" "
                    + "height=\"%d\" "
                    + "x=\"%d\" "
                    + "y = \"%d\" "
                    + "fill=\"none\" "
                    + "stroke-width=\"2\" "
                    + "stroke=\"%s\" />"
                    + "\n",
                    11, 14, rindex, y + 12, colorHex
            );
            rindex+=11;
            svgString += svgRect1;
        }
        

        String svgRect2 = String.format(""
                + "<rect "
                + "width=\"%d\" "
                + "height=\"%d\" "
                + "x=\"%d\" "
                + "y = \"%d\" "
                + "fill=\"none\" "
                + "stroke-width=\"2\" "
                + "stroke=\"%s\" />"
                + "\n",
                12, 14, rindex, y + 12, colorHex
        );

//        String svgRect3 = String.format(""
//                + "<rect "
//                + "width=\"%d\" "
//                + "height=\"%d\" "
//                + "x=\"%d\" "
//                + "y = \"%d\" "
//                + "fill=\"none\" "
//                + "stroke-width=\"2\" "
//                + "stroke=\"%s\" />"
//                + "\n",
//                11, 14, x + 23, y + 12, colorHex
//        );
//
//        String svgRect4 = String.format(""
//                + "<rect "
//                + "width=\"%d\" "
//                + "height=\"%d\" "
//                + "x=\"%d\" "
//                + "y = \"%d\" "
//                + "fill=\"none\" "
//                + "stroke-width=\"2\" "
//                + "stroke=\"%s\" />"
//                + "\n",
//                11, 14, x + 34, y + 12, colorHex
//        );
//
//        String svgRect5 = String.format(""
//                + "<rect "
//                + "width=\"%d\" "
//                + "height=\"%d\" "
//                + "x=\"%d\" "
//                + "y = \"%d\" "
//                + "fill=\"none\" "
//                + "stroke-width=\"2\" "
//                + "stroke=\"%s\" />"
//                + "\n",
//                11, 14, x + 45, y + 12, colorHex
//        );
//
//        String svgRect6 = String.format(""
//                + "<rect "
//                + "width=\"%d\" "
//                + "height=\"%d\" "
//                + "x=\"%d\" "
//                + "y = \"%d\" "
//                + "fill=\"none\" "
//                + "stroke-width=\"2\" "
//                + "stroke=\"%s\" />"
//                + "\n",
//                12, 14, x + 56, y + 12, colorHex
//        );

        String svgText = String.format(""
                + "<text "
                + "x=\"%d\" "
                + "y=\"%d\" "
                + "font-family=\"TimesRoman\" "
                + "class=\"Seat Number\" "
                + "font-size=\"%d\" "
                + "text-anchor=\"start\" >"
                + "Seat Number"
                + "</text>",
                x + 34, y + 8, 8);

        svgString += svgBigRect + svgRect2 + svgText;

        int index = 0;
        int n = 0;
        for (Shape s : getBubbles()) {
            int cx = Math.min(s.getP1().x, s.getP2().x);
            int cy = Math.min(s.getP1().y, s.getP2().y);
            int r = 8;

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
                    cx + 2, cy + 6, n + "", 6, n + "");

            svgString += "\t" + circlesvg + "\n" + "\t" + textsvgString + "\n";

            index++;
            if (index == 10) {
                n++;
                index = 0;
            }
        }
        return svgString;
    }

    /**
     * @return the vNumbersNum
     */
    public int getvNumbersNum() {
        return vNumbersNum;
    }

    /**
     * @param vNumbersNum the vNumbersNum to set
     */
    public void setvNumbersNum(int vNumbersNum) {
        this.vNumbersNum = vNumbersNum;
    }

    /**
     * @return the hNumbersNum
     */
    public int gethNumbersNum() {
        return hNumbersNum;
    }

    /**
     * @param hNumbersNum the hNumbersNum to set
     */
    public void sethNumbersNum(int hNumbersNum) {
        this.hNumbersNum = hNumbersNum;
    }

    /**
     * @return the firstQnum
     */
}
