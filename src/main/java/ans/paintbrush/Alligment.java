package ans.paintbrush;

import java.awt.Point;
import java.util.ArrayList;

public class Alligment {

    Point dP = null;
    int dX = 0;
    int dY = 0;
   

    public Alligment() {       
        dP = new Point();
    }
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int minXCenter = Integer.MAX_VALUE;
    int minYCenter = Integer.MAX_VALUE;
    int maxXCenter = Integer.MIN_VALUE;
    int maxYCenter = Integer.MIN_VALUE;
    int longDistance;
    int smallDistance;
    int disP1;
    int disP2;
    int h;

    private void xAlligment(ArrayList<Shape> selectedShapes) {
        for (int i = 0; i < selectedShapes.size(); i++) {
            if (selectedShapes.get(i).getP1().x < minX) {
                minX = selectedShapes.get(i).getP1().x;
            }
        }
    }

    private void yAlligment(ArrayList<Shape> selectedShapes) {
        for (int i = 0; i < selectedShapes.size(); i++) {
            if (selectedShapes.get(i).getP1().y < minY) {
                minY = selectedShapes.get(i).getP1().y;
            }
        }
    }

    private void yCenter(ArrayList<Shape> selectedShapes) {
        for (int i = 0; i < selectedShapes.size(); i++) {
            if ((selectedShapes.get(i).getP1().y + (selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y) / 2) < minYCenter) {
                minYCenter = (selectedShapes.get(i).getP1().y + (selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y) / 2);
            }
        }
        for (int i = 0; i < selectedShapes.size(); i++) {
            if ((selectedShapes.get(i).getP1().y + (selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y) / 2) > maxYCenter) {
                maxYCenter = (selectedShapes.get(i).getP1().y + (selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y) / 2);
            }
        }
        longDistance = maxYCenter - minYCenter;
        smallDistance = longDistance / (selectedShapes.size() - 1);
    }

    private void xCenter(ArrayList<Shape> selectedShapes) {
        for (int i = 0; i < selectedShapes.size(); i++) {
            if ((selectedShapes.get(i).getP1().x + (selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x) / 2) < minXCenter) {
                minXCenter = (selectedShapes.get(i).getP1().x + (selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x) / 2);
            }
        }
        for (int i = 0; i < selectedShapes.size(); i++) {
            if ((selectedShapes.get(i).getP1().x + (selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x) / 2) > maxYCenter) {
                maxXCenter = (selectedShapes.get(i).getP1().x + (selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x) / 2);
            }
        }
        longDistance = maxXCenter - minXCenter;
        smallDistance = longDistance / (selectedShapes.size() - 1);
    }

    public void setCenter(boolean xCenter, ArrayList<Shape> selectedShapes) {
        if (xCenter == true) {
            sortHorizotally(selectedShapes);
            xCenter(selectedShapes);
            for (int i = 1; i < selectedShapes.size() - 1; i++) {
                h = selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x;
                disP1 = selectedShapes.get(i - 1).getP2().x + (smallDistance - ((selectedShapes.get(i - 1).getP2().x - selectedShapes.get(i - 1).getP1().x) / 2)) - ((selectedShapes.get(i).getP2().x - selectedShapes.get(i).getP1().x) / 2);
                selectedShapes.get(i).getP1().x = disP1;
                selectedShapes.get(i).getP2().x = disP1 + h;
            }
        } else {
            sortVertically(selectedShapes);
            yCenter(selectedShapes);
            for (int i = 1; i < selectedShapes.size() - 1; i++) {
                h = selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y;
                disP1 = selectedShapes.get(i - 1).getP2().y + (smallDistance - ((selectedShapes.get(i - 1).getP2().y - selectedShapes.get(i - 1).getP1().y) / 2)) - ((selectedShapes.get(i).getP2().y - selectedShapes.get(i).getP1().y) / 2);
                selectedShapes.get(i).getP1().y = disP1;
                selectedShapes.get(i).getP2().y = disP1 + h;
            }
        }
    }

    public void setAlligment(boolean xAxis, ArrayList<Shape> selectedShapes) {

        if (xAxis == true) {
            xAlligment(selectedShapes);
            for (int i = 0; i < selectedShapes.size(); i++) {
                dP.x = selectedShapes.get(i).getP1().x;
                selectedShapes.get(i).getP1().x = minX;
                dX = dP.x - selectedShapes.get(i).getP1().x;
                selectedShapes.get(i).getP2().x = selectedShapes.get(i).getP2().x - dX;
            }
        } else {
            yAlligment(selectedShapes);
            for (int i = 0; i < selectedShapes.size(); i++) {
                dP.y = selectedShapes.get(i).getP1().y;
                selectedShapes.get(i).getP1().y = minY;
                dY = dP.y - selectedShapes.get(i).getP1().y;
                selectedShapes.get(i).getP2().y = selectedShapes.get(i).getP2().y - dY;
            }
        }
    }

    private void sortVertically(ArrayList<Shape> selected) {
        Shape firist = null;
        Shape Secend = null;
        for (int i = 0; i < selected.size(); i++) {
            for (int j = i; j < selected.size(); j++) {
                if (selected.get(j).getP1().y < selected.get(i).getP1().y) {
                    firist = selected.get(j);
                    Secend = selected.get(i);
                    selected.set(i, firist);
                    selected.set(j, Secend);
                }
            }
        }
    }

    private void sortHorizotally(ArrayList<Shape> selected) {
        Shape firist = null;
        Shape Secend = null;
        for (int i = 0; i < selected.size(); i++) {
            for (int j = i; j < selected.size(); j++) {
                if (selected.get(j).getP1().x < selected.get(i).getP1().x) {
                    firist = selected.get(j);
                    Secend = selected.get(i);
                    selected.set(i, firist);
                    selected.set(j, Secend);
                }
            }
        }
    }
}
