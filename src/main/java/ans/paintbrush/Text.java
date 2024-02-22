package ans.paintbrush;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Text extends Shape {

    /**
     * @return the fontSize
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize the fontSize to set
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    private int fontSize;
    private String text;
    private boolean bold = false;

    public Text() {
        super(0, 0, 0, 0);
    }

    @Override
    public boolean IsPointInsideShape(Point p) {
        int x1 = Math.min(getP1().x, getP2().x);
        int y1 = Math.min(getP1().y, getP2().y);
        int x2 = Math.max(getP1().x, getP2().x);
        int y2 = Math.max(getP1().y, getP2().y);
        if ((p.x >= x1 - 5 && p.x <= x2 + 5) && (p.y >= y1 - 5 && p.y <= y2 + 5)) {
            return true;
        } else {
            return false;
        }
    }
    int x4 = Math.min(getP1().x, getP2().x);
    int y4 = Math.min(getP1().y, getP2().y);
    Font font;

    @Override
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(getDrawColor());
        int x = getP1().x;
        int y = getP1().y;
        font = new Font("TimesRoman", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(font);
        int txth = metrics.getHeight();
        int txtw = metrics.stringWidth(text);
        int w = txtw;
        int h = txth;
        setP2(new Point(x + w, y + h));

//        setFontSize(Math.min(w * 3 / 4, h * 3 / 4));
        g.setFont(font);
        g.drawString(getText(), x, y + h - fontSize / 4);

        if (isSelected()) {
            drawSelectBorder(g);
        }
        g.setColor(oldColor);
    }

    @Override
    public String toSVG() {
        int x = Math.min(getP1().x, getP2().x);
        int y = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);

        String svgString = String.format(""
                + "<text "
                + "x=\"%d\" "
                + "y=\"%d\" "
                + "font-family=\"TimesRoman\" "
                + "class=\"%s\" "
                + "font-size=\"%d\" "
                + "text-anchor=\"start\" >"
                + "%s"
                + "</text>",
                x + 2, y + 6, getText(), getFontSize(), getText());

        return svgString;
    }

    public static Shape loadSVG(org.w3c.dom.Node node) {
        Text text = new Text();
        NamedNodeMap map = node.getAttributes();
        Node xNode = map.getNamedItem("x");
        Node yNode = map.getNamedItem("y");
        Node font_size = map.getNamedItem("font-size");
        Node txt = map.getNamedItem("class");
//        Node stroke = map.getNamedItem("stroke");

        text.getP1().x = Integer.parseInt(xNode.getNodeValue()) ;
        text.getP1().y = Integer.parseInt(yNode.getNodeValue()) - 6;
        text.setText(txt.getNodeValue());
        text.setFontSize(Integer.parseInt(font_size.getNodeValue()));
//        text.setDrawColor(Color.decode(stroke.getNodeValue()));

        return text;
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

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the bold
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * @param bold the bold to set
     */
    public void setBold(boolean bold) {
        this.bold = bold;
    }
}
