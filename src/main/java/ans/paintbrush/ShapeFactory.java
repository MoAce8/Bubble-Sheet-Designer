package ans.paintbrush;

import org.w3c.dom.css.Rect;

public class ShapeFactory {

    public static Shape createShape(ShapesEnum theShape) {
        Shape s = null;
        switch (theShape) {
            case CIRCLE:
                s = new Circle();
                break;
            case LINE:
                s = new Line();
                break;
            case OVAL:
                s = new Oval();
                break;
            case RECTANGLE:
                s = new Rectangle();
                break;
            case RightTriangle:
                s = new RightTriangle();
                break;
            case Triangle:
                s = new Triangle();
                break;
            case pentagram:
                s = new Pentagram();
                break;
            case Hexognal:
                s = new Hexognal();
                break;
            case ROUND_RECT:
                s = new RoundRect();
                break;
            case Smile:
                s = new Smile();
                break;
            case SmileFace:
                s = new SmileFace();
                break;
            case Certain:
                s = new Diamond();
                break;
            case Star:
                s = new Star();
                break;
            case SixPointStar:
                s = new SixPointStar();
                break;
            case Right:
                s = new Right();
                break;
            case Left:
                s = new Left();
                break;
            case Up:
                s = new Up();
                break;
            case Down:
                s = new Down();
                break;
            case TEXT:
                s = new Text();
                break;
            case QUESTION:
                s = new Question();
                break;
        }

        return s;
    }
}
