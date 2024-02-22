package ans.paintbrush;

import java.util.ArrayList;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Utility {

    public static ArrayList<Shape> loadSVG(String svgFilename) {
        ArrayList<Shape> shapes = new ArrayList<>();
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            FileInputStream inStream = new FileInputStream(svgFilename);
            Document doc = builder.parse(inStream);

            Element root = doc.getDocumentElement();

            NodeList allChildren = root.getChildNodes();
            System.out.println(root.getNodeName());
            Shape s;
            for (int i = 0; i < allChildren.getLength(); i++) {
                Node node = allChildren.item(i);
                switch (node.getNodeName()) {
                    case "line":
                        shapes.add(Line.loadSVG(node));
                        break;
                    case "rect":
                        shapes.add(Rectangle.loadSVG(node));
                        break;
                    case "roundrect":
                        shapes.add(Rectangle.loadSVG(node));
                        break;
                    case "ellipse":
                        shapes.add(Oval.loadSVG(node));
                        break;
                    case "circle":
                        shapes.add(Circle.loadSVG(node));
                        break;
                    case "polygon":
                        shapes.add(loadPolygonShapeSVG(node));
                        break;
                    case "text":
                        shapes.add(Text.loadSVG(node));

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return shapes;
    }

    public static Shape loadPolygonShapeSVG(Node node) {
        switch (node.getAttributes().getNamedItem("class").getNodeValue()) {
            case "Triangle":
                return Triangle.loadSVG(node);
            case "Certain":
                return Diamond.loadSVG(node);
            case "RightTriangle":
                return RightTriangle.loadSVG(node);
            case "Down":
                return Down.loadSVG(node);
            case "Up":
                return Up.loadSVG(node);
            case "Left":
                return Left.loadSVG(node);
            case "Right":
                return Right.loadSVG(node);
            case "Star":
                return Star.loadSVG(node);
            case "SixPointStar":
                return SixPointStar.loadSVG(node);
            case "Hexognal":
                return Hexognal.loadSVG(node);
            case "Pentagram":
                return Pentagram.loadSVG(node);

        }
        return null;
    }

}
