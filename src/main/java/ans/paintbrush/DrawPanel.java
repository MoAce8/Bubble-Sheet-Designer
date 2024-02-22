package ans.paintbrush;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class DrawPanel extends javax.swing.JPanel {

    /**
     * @return the qShapes
     */
    public ArrayList<Shape> getqShapes() {
        return qShapes;
    }

    /**
     * @param qShapes the qShapes to set
     */
    public void setqShapes(ArrayList<Shape> qShapes) {
        this.qShapes = qShapes;
    }

    public Graphics getOffScreenGraphics() {
        return this.offScreenGraphics;
    }

    /**
     * @return the ansModel
     */
    public boolean isAnsModel() {
        return ansModel;
    }

    /**
     * @param ansModel the ansModel to set
     */
    public void setAnsModel(boolean ansModel) {
        this.ansModel = ansModel;
    }

    private int h;
    private int w;
    private BufferedImage offScreenImage;
    private Graphics offScreenGraphics;
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private double xRel;
    private double yRel;
    private double xOffset = 0;
    private double yOffset = 0;
    protected int x_offscreen = 400;
    protected int y_offscreen = 35;
    private boolean isCtrlpressed = false;
    protected ArrayList<Shape> allShapes = new ArrayList();
    private ArrayList<Shape> SelectedShapes = new ArrayList();
    private ArrayList<Shape> qShapes = new ArrayList();
    private ArrayList<Shape> copiedShapes = new ArrayList();

    public DrawPanel() {
        w = 500;
        h = (int) (w * 29.7 / 21);

        offScreenImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        offScreenGraphics = offScreenImage.getGraphics();
        initComponents();
//    addKeyListener(this);
        setFocusable(true);
//    setFocusTraversalKeysEnabled(true);
        KeyboardFocusManager Keymanager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        Keymanager.addKeyEventPostProcessor(new ControlKeyPostProccessor());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextFrame = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldText = new javax.swing.JTextField();
        okText = new javax.swing.JButton();
        cancelText = new javax.swing.JButton();
        jTextFieldFontSize = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        QNumFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        numQ = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        numAns = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        okQ = new javax.swing.JButton();
        cancelQ = new javax.swing.JButton();
        EditTextFrame = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldText1 = new javax.swing.JTextField();
        okEdit = new javax.swing.JButton();
        cancelEdit = new javax.swing.JButton();
        jTextFieldFontSize1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        TextFrame.setMinimumSize(new java.awt.Dimension(400, 400));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter Text:");

        jTextFieldText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTextActionPerformed(evt);
            }
        });

        okText.setText("OK");
        okText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okTextActionPerformed(evt);
            }
        });

        cancelText.setText("Cancel");
        cancelText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTextActionPerformed(evt);
            }
        });

        jTextFieldFontSize.setText("12");
        jTextFieldFontSize.setMinimumSize(new java.awt.Dimension(80, 22));
        jTextFieldFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFontSizeActionPerformed(evt);
            }
        });

        jLabel4.setText("Enter font size:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(okText)
                        .addGap(48, 48, 48)
                        .addComponent(cancelText)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jTextFieldText, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelText)
                    .addComponent(okText))
                .addGap(53, 53, 53))
        );

        TextFrame.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        QNumFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        QNumFrame.setTitle("Create Question Block");
        QNumFrame.setLocationByPlatform(true);
        QNumFrame.setMinimumSize(new java.awt.Dimension(500, 500));

        jLabel1.setText("Question number");

        jLabel2.setText("Answer number");

        okQ.setText("OK");
        okQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okQActionPerformed(evt);
            }
        });

        cancelQ.setText("Cancel");
        cancelQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelQActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numQ, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(numAns))
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okQ)
                .addGap(48, 48, 48)
                .addComponent(cancelQ)
                .addGap(149, 149, 149))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numAns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelQ)
                    .addComponent(okQ))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        QNumFrame.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        EditTextFrame.setMinimumSize(new java.awt.Dimension(400, 400));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Edit Text:");

        jTextFieldText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldText1ActionPerformed(evt);
            }
        });

        okEdit.setText("OK");
        okEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okEditActionPerformed(evt);
            }
        });

        cancelEdit.setText("Cancel");
        cancelEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEditActionPerformed(evt);
            }
        });

        jTextFieldFontSize1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldFontSize1.setText("12");

        jLabel6.setText("Enter font size:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldText1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(okEdit)
                                .addGap(48, 48, 48)
                                .addComponent(cancelEdit))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldFontSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldText1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFontSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelEdit)
                    .addComponent(okEdit))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        EditTextFrame.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        setBackground(new java.awt.Color(205, 215, 230));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private int numSelect = 0;

    @Override
    protected void paintComponent(Graphics onScreenGraphics) {
//        onScreenGraphics.setColor(Color.yellow);
        super.paintComponent(onScreenGraphics);
        Graphics2D g2 = (Graphics2D) onScreenGraphics;
        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }
        offScreenGraphics.setColor(Color.white);
        offScreenGraphics.fillRect(0, 0, offScreenImage.getWidth(), offScreenImage.getHeight());

        for (Shape s : allShapes) {
            s.draw(offScreenGraphics);
        }

        if (currentShape != null) {
            currentShape.draw(offScreenGraphics);
        }
        onScreenGraphics.drawImage(offScreenImage, x_offscreen, y_offscreen, null);

    }

    private void showPopUpMenu(Point pt) {
        JPopupMenu menu = new JPopupMenu();
        JMenu AlligmentMenu = new JMenu("Alligment");

        JMenuItem menuItemDelete = new JMenuItem("Delete");
        menuItemDelete.addActionListener(deleteActionListener);
        menu.add(menuItemDelete);

//        JMenuItem menuItemCopy = new JMenuItem("Copy");
//        menuItemCopy.addActionListener(copy);
//        menu.add(menuItemCopy);
        JMenuItem menuItemCopy = new JMenuItem("Duplicate");
        menuItemCopy.addActionListener(duplicate);
        menu.add(menuItemCopy);

        JMenuItem menuItemRect = new JMenuItem("Put in Rectangle");
        menuItemRect.addActionListener(putInRect);
        menu.add(menuItemRect);

//        if (copiedShapes.size() > 0) {
//            JMenuItem menuItemPaste = new JMenuItem("Paste");
//            menuItemPaste.addActionListener(paste);
//            menu.add(menuItemPaste);
//        }
        if (SelectedShapes.size() > 1) {
            JMenuItem menuItemGroup = new JMenuItem("Group");
            menuItemGroup.addActionListener(GroupActionListener);
            menu.add(menuItemGroup);

            JMenuItem leftAlligment = new JMenuItem("Left-Alligment");
            leftAlligment.addActionListener(this.leftAlligement);
            AlligmentMenu.add(leftAlligment);

            JMenuItem topAlligment = new JMenuItem("Top-Alligment");
            topAlligment.addActionListener(this.topAlligement);
            AlligmentMenu.add(topAlligment);

            JMenuItem horizontalCenter = new JMenuItem("Distribute-Horizontally");
            horizontalCenter.addActionListener(this.horizentalDistripute);
            AlligmentMenu.add(horizontalCenter);

            JMenuItem verticalCenter = new JMenuItem("Distribute Vertically");
            verticalCenter.addActionListener(this.verticalDistripute);
            AlligmentMenu.add(verticalCenter);

            menu.add(AlligmentMenu);
        }
        if (SelectedShapes.size() >= 1 && (SelectedShapes.get(0) instanceof CompositeShape || SelectedShapes.get(0) instanceof Question)) {
            JMenuItem menuItemUnGroup = new JMenuItem("UnGroup");
            menuItemUnGroup.addActionListener(UnGroupActionListener);
            menu.add(menuItemUnGroup);
        }
        if (SelectedShapes.get(0) instanceof Text) {
            JMenuItem menuItemUnGroup = new JMenuItem("Edit Text");
            menuItemUnGroup.addActionListener(editText);
            menu.add(menuItemUnGroup);
        }

        if (SelectedShapes.get(0) instanceof Question) {
            JMenuItem menuItemUnGroup = new JMenuItem("Sort Questions");
            menuItemUnGroup.addActionListener(sortQ);
            menu.add(menuItemUnGroup);
        }

        menu.show(this, pt.x + x_offscreen, pt.y + y_offscreen);
    }

    private ActionListener deleteActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            allShapes.removeAll(SelectedShapes);
            SelectedShapes.clear();
            repaint();
        }
    };
    private ActionListener GroupActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            CompositeShape cshape = new CompositeShape();
            cshape.setSelected(true);
            cshape.addAll(SelectedShapes);
            allShapes.removeAll(SelectedShapes);
            for (Shape s : SelectedShapes) {
                s.setSelected(false);
            }
            deSelectAllShapes();
            allShapes.add(cshape);
            repaint();

        }
    };
    private ActionListener UnGroupActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (SelectedShapes.get(0) instanceof CompositeShape) {
                CompositeShape cshape = (CompositeShape) SelectedShapes.get(0);
                allShapes.addAll(cshape.getAllShapes());
                allShapes.removeAll(SelectedShapes);
                repaint();
            }
            if (SelectedShapes.get(0) instanceof Question) {
                Question qshape = (Question) SelectedShapes.get(0);
                allShapes.addAll(qshape.getBubbles());
                allShapes.removeAll(SelectedShapes);
                repaint();
                System.out.println(allShapes.size());

            }
        }
    };

    private ActionListener leftAlligement = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            alligment = new Alligment();
            alligment.setAlligment(true, SelectedShapes);
            repaint();
        }
    };
    private ActionListener topAlligement = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            alligment = new Alligment();
            alligment.setAlligment(false, SelectedShapes);
            repaint();
        }
    };
    private ActionListener verticalDistripute = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            alligment = new Alligment();
            alligment.setCenter(false, SelectedShapes);
            repaint();
        }
    };
    private ActionListener horizentalDistripute = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            alligment = new Alligment();
            alligment.setCenter(true, SelectedShapes);
            repaint();
        }
    };

    private ActionListener sortQ = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Question qshape1 = (Question) SelectedShapes.get(0);
            Question qshape2 = (Question) SelectedShapes.get(1);
            qshape2.setFirstQnum(qshape1.getqNum() + qshape1.getFirstQnum());
            if (SelectedShapes.size() > 2) {
                Question qshape3 = (Question) SelectedShapes.get(2);
                qshape3.setFirstQnum(qshape2.getqNum() + qshape2.getFirstQnum());
                if (SelectedShapes.size() > 3) {
                    Question qshape4 = (Question) SelectedShapes.get(3);
                    qshape4.setFirstQnum(qshape3.getqNum() + qshape3.getFirstQnum());
                    if (SelectedShapes.size() > 4) {
                        Question qshape5 = (Question) SelectedShapes.get(4);
                        qshape5.setFirstQnum(qshape4.getqNum() + qshape4.getFirstQnum());
                        if (SelectedShapes.size() > 5) {
                            Question qshape6 = (Question) SelectedShapes.get(5);
                            qshape6.setFirstQnum(qshape5.getqNum() + qshape5.getFirstQnum());
                        }
                    }
                }
            }
            repaint();
        }
    };

    private ActionListener putInRect = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Rectangle rect = new Rectangle();
            if (SelectedShapes.size() == 1) {
                if (SelectedShapes.get(0) instanceof Question) {
                    rect.setP1(new Point(SelectedShapes.get(0).getP1().x - 10, SelectedShapes.get(0).getP1().y));
                    rect.setP2(new Point(SelectedShapes.get(0).getP2().x, SelectedShapes.get(0).getP2().y));
                } else {
                    rect.setP1(new Point(SelectedShapes.get(0).getP1().x - 10, SelectedShapes.get(0).getP1().y - 10));
                    rect.setP2(new Point(SelectedShapes.get(0).getP2().x + 10, SelectedShapes.get(0).getP2().y + 10));
                }
            }
            if (SelectedShapes.size() > 1) {
                for (int i = 1; i < SelectedShapes.size(); i++) {
                    SelectedShapes.get(i).moveShapeBy(SelectedShapes.get(i - 1).getP1().x - SelectedShapes.get(i).getP1().x
                            + Math.abs(SelectedShapes.get(i - 1).getP1().x - SelectedShapes.get(i - 1).getP2().x) + 11,
                            0);
                }
                rect.setP1(new Point(SelectedShapes.get(0).getP1().x - 11, SelectedShapes.get(0).getP1().y));
                rect.setP2(new Point(SelectedShapes.get(SelectedShapes.size() - 1).getP2().x, SelectedShapes.get(SelectedShapes.size() - 1).getP2().y));
            }
            ArrayList<Shape> tempShapes = new ArrayList();
            tempShapes.addAll(SelectedShapes);
            allShapes.remove(SelectedShapes);
            allShapes.add(rect);
            allShapes.addAll(tempShapes);
            repaint();
        }
    };

    private void topAllin() {
//        SelectedShapes.get(i - 1).getP1().y - SelectedShapes.get(i).getP1().y
    }

//    private ActionListener copy = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            copyShapes();
//            repaint();
//        }
//    };
//
//    private ActionListener paste = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            pasteShapes();
//            repaint();
//        }
//    };
    private ActionListener duplicate = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                duplicateShapes();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(DrawPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
    };

    private ActionListener editText = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            openEditTFrame();
            repaint();
        }
    };

    private void openEditTFrame() {
        EditTextFrame.show();
        Text s = (Text) SelectedShapes.get(0);
        jTextFieldText1.setText(s.getText());
        jTextFieldFontSize1.setText(String.valueOf(s.getFontSize()));
//        currentShape.setP1(s.getP1());
//        currentShape.setP2(s.getP2());
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:        
        evt.translatePoint(-x_offscreen, -y_offscreen);
        if (toolspanel.isDrawing()) {
            deSelectAllShapes();
            currentShape = ShapeFactory.createShape(toolspanel.getSelectedShape());
            currentShape.setP1(evt.getPoint());
            currentShape.setP2(evt.getPoint());
            currentShape.setFilled(toolspanel.isFilledcolor());
            currentShape.setTheColor(toolspanel.getCurrentColor());
        } else {
//            selectshape(evt.getPoint());
            if (SwingUtilities.isRightMouseButton(evt)) {
                if (SelectedShapes.size() <= 1) {
                    selectshape(evt.getPoint());
                }
                showPopUpMenu(evt.getPoint());
            } else {
                if (isCtrlpressed) {
                    selectshape(evt.getPoint());
                    dragstartPoint = null;
                } else {
                    if (isCtrlpressed = false) {
                        deSelectAllShapes();
                    }
                    selectshape(evt.getPoint());
                    if (SelectedShapes.size() == 0) {
                        dragstartPoint = evt.getPoint();
                    } else {
                        dragstartPoint = evt.getPoint();

                    }

                }
            }

        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
//        propertyPanel.setAllProperties(currentShape.getallProperties());
        evt.translatePoint(-x_offscreen, -y_offscreen);
        dragstartPoint = null;
        if (!toolspanel.isDrawing()) {
            return;
        } else {
            currentShape.correctPoints();
            if (toolspanel.getSelectedShape() == ShapesEnum.TEXT) {
                TextFrame.show();
            } else if (toolspanel.getSelectedShape() == ShapesEnum.QUESTION) {
                QNumFrame.show();
            } else {
                allShapes.add(currentShape);
                currentShape = null;
                repaint();
            }
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        evt.translatePoint(-x_offscreen, -y_offscreen);
        if (toolspanel.isDrawing()) {
            currentShape.setP2(evt.getPoint());
        } else {
            if (dragstartPoint != null) {
                int dx = evt.getPoint().x - dragstartPoint.x;
                int dy = evt.getPoint().y - dragstartPoint.y;
                Shape s = getsSelectShapes();
                if (s != null) {
                    switch (getCursor().getType()) {
                        case Cursor.HAND_CURSOR:
                            s.moveShapeBy(dx, dy);
                            break;
                        case Cursor.NE_RESIZE_CURSOR:
                            s.getP2().x = s.getP2().x + dx;
                            s.getP1().y = s.getP1().y + dy;
                            break;
                        case Cursor.NW_RESIZE_CURSOR:
                            s.getP1().x = s.getP1().x + dx;
                            s.getP1().y = s.getP1().y + dy;
                            break;
                        case Cursor.SW_RESIZE_CURSOR:
                            s.getP1().x = s.getP1().x + dx;
                            s.getP2().y = s.getP2().y + dy;
                            break;
                        case Cursor.SE_RESIZE_CURSOR:
                            s.getP2().x = s.getP2().x + dx;
                            s.getP2().y = s.getP2().y + dy;
                            break;
                        case Cursor.W_RESIZE_CURSOR:
                            s.getP1().x = s.getP1().x + dx;
                            break;
                        case Cursor.E_RESIZE_CURSOR:
                            s.getP2().x = s.getP2().x + dx;
                            break;
                        case Cursor.N_RESIZE_CURSOR:
                            s.getP1().y = s.getP1().y + dy;
                            break;
                        case Cursor.S_RESIZE_CURSOR:
                            s.getP2().y = s.getP2().y + dy;
                            break;
                    }

                }
                dragstartPoint = evt.getPoint();
            }

        }

        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

//        if (toolspanel.isDrawing()) {
//            return;
//        }
//        if (isCtrlpressed) {
//            Ctrlselectshape(evt.getPoint());
//        } else {
//            selectshape(evt.getPoint());
//        }
//        repaint();
    }//GEN-LAST:event_formMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_CONTROL);
        this.isCtrlpressed = true;

    }//GEN-LAST:event_formKeyPressed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        evt.translatePoint(-x_offscreen, -y_offscreen);
        if (toolspanel.isDrawing()) {
            this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            for (Shape s : allShapes) {
                Cursor c = s.getMouseShape(evt.getPoint());
                if (c != null) {
                    this.setCursor(c);
                    return;
                }
            }
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }//GEN-LAST:event_formMouseMoved

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        // TODO add your handling code here:
        evt.translatePoint(-x_offscreen, -y_offscreen);
        zoomer = true;

        //Zoom in
        if (evt.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (evt.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
            repaint();
        }

    }//GEN-LAST:event_formMouseWheelMoved

    private void jTextFieldTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTextActionPerformed

    private void okTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okTextActionPerformed
        // TODO add your handling code here:
        TextFrame.dispose();

        Text s = new Text();
        s.setText(jTextFieldText.getText());
        s.setFontSize(Integer.parseInt(jTextFieldFontSize.getText()));
        s.setP1(currentShape.p1);
        s.setP2(currentShape.p2);
        s.setSelected(true);
        allShapes.add(s);

        repaint();
        currentShape = null;
        jTextFieldText.setText("");
    }//GEN-LAST:event_okTextActionPerformed

    private void cancelTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTextActionPerformed
        // TODO add your handling code here:
        TextFrame.dispose();
        currentShape = null;
    }//GEN-LAST:event_cancelTextActionPerformed

    private void okQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okQActionPerformed
        // TODO add your handling code here:
        QNumFrame.dispose();

        Question qshape = new Question();
        qshape.setqNum(Integer.parseInt(numQ.getText()));
        qshape.setAnsNum(Integer.parseInt(numAns.getText()));
        qshape.setPoints(currentShape.p1);
        qshape.setSelected(true);
        allShapes.add(qshape);
        repaint();

        currentShape = null;
        numQ.setText("");
        numAns.setText("");
    }//GEN-LAST:event_okQActionPerformed

    private void cancelQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelQActionPerformed
        // TODO add your handling code here:
        QNumFrame.dispose();
    }//GEN-LAST:event_cancelQActionPerformed

    private void jTextFieldText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldText1ActionPerformed

    private void okEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okEditActionPerformed
        // TODO add your handling code here:
        EditTextFrame.dispose();

        Text s = (Text) SelectedShapes.get(0);

        s.setText(jTextFieldText1.getText());
        s.setFontSize(Integer.parseInt(jTextFieldFontSize1.getText()));
//        s.setP1(currentShape.p1);
//        s.setP2(currentShape.p2);
        s.setSelected(true);
//        allShapes.add(s);

        repaint();
//        currentShape = null;
        jTextFieldText1.setText("");
    }//GEN-LAST:event_okEditActionPerformed

    private void cancelEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditActionPerformed
        // TODO add your handling code here:
        EditTextFrame.dispose();
//        currentShape = null;
    }//GEN-LAST:event_cancelEditActionPerformed

    private void jTextFieldFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFontSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFontSizeActionPerformed
//    private Shape selectshape(Point pt) {
//        deSelectAllShapes();
//        if (ansModel == true) {
//            Question qBlock = (Question) allShapes.get(allShapes.size());
//            qShapes.addAll(qBlock.getBubbles());
//
//            for (int i = qShapes.size() - 1; i >= 0; i--) {
//                Shape s = qShapes.get(i);
//                if (s.IsPointInsideShape(pt)) {
//
//                    s.setSelected(true);
//                    SelectedShapes.add(s);
//                    System.out.println(s.p1);
////                this.propertyPanel.setAllProperties(s.getallProperties());
//                    numSelect = 1;
//                }
//                return s;
//
//            }
//
//        } else {
//            for (int i = allShapes.size() - 1; i >= 0; i--) {
//                Shape s = allShapes.get(i);
//                if (s.IsPointInsideShape(pt)) {
//
//                    s.setSelected(true);
//                    SelectedShapes.add(s);
////                this.propertyPanel.setAllProperties(s.getallProperties());
//                    numSelect = 1;
//                }
//                return s;
//
//            }
//        }
//        return null;
//    }

    private void selectshape(Point pt) {
        if (isCtrlpressed == false) {
            deSelectAllShapes();
        }
        for (int i = allShapes.size() - 1; i >= 0; i--) {
            Shape s = allShapes.get(i);
            if (s.IsPointInsideShape(pt)) {
                s.setSelected(true);
                SelectedShapes.add(s);
                return;

//                this.propertyPanel.setAllProperties(s.getallProperties());
//                numSelect = 1;
            }
        }
    }

    private void Ctrlselectshape(Point pt) {

        for (int i = allShapes.size() - 1; i >= 0; i--) {
            Shape s = allShapes.get(i);
            if (s.IsPointInsideShape(pt)) {
                if (!s.isSelected()) {
                    s.setSelected(true);
                    SelectedShapes.add(s);

                }
//                this.propertyPanel.setAllProperties(s.getallProperties());
            }
        }
    }

    private Shape getsSelectShapes() {
        for (Shape s : allShapes) {
            if (s.isSelected()) {
                return s;
            }
        }
        return null;
    }

    private void deSelectAllShapes() {
        SelectedShapes.clear();
        for (Shape s : allShapes) {
            s.setSelected(false);
        }

    }

//    private void copyShapes() {
//        copiedShapes.clear();
//        copiedShapes.addAll(SelectedShapes);
//    }
//
//    private void pasteShapes() {
//        allShapes.addAll(copiedShapes);
//        Shape s;
//        for (int i = copiedShapes.size(), j = allShapes.size() - 1; i > 0; i--, j--) {
//            allShapes.get(j).moveShapeBy(30, 30);
//        }
//    }
    private void duplicateShapes() throws CloneNotSupportedException {
        copiedShapes.clear();
        copiedShapes.addAll(SelectedShapes);
        for (Shape s : copiedShapes) {
            Point p3 = new Point();
            Point p4 = new Point();
            p3.x = s.getP1().x + 30;
            p3.y = s.getP1().y + 30;
            p4.x = s.getP2().x + 30;
            p4.y = s.getP2().y + 30;
            allShapes.add((Shape) s.clone());
            allShapes.get(allShapes.size() - 1).setP1(p3);
            allShapes.get(allShapes.size() - 1).setP2(p4);
        }
    }

//    private void Delete()
//    {
//         item1.addActionL istener(deleteActionListener);
//         
//    }
//     private void Group()
//    {
//        for(Shape s:allShapes)
//        {
//        if(s.isSelected()){
//            s.setGroup(true);
//            SelectedShapes.add(s);
//        }
//        }
//        
//        
//    }
//     private void unGroup()
//    {
//       for(Shape s:allShapes)
//        {
//        if(s.isSelected()){
//            s.setGroup(false);
//        }
//        SelectedShapes.clear() ; 
//        }
//             
//           
//    }
    private ToolsPanel toolspanel;
    private PropertyPanel propertyPanel;
    public Shape currentShape = null;
    private Point dragstartPoint = null;
//    private boolean Resizing;
    private boolean Multiselect = false;
    private boolean ansModel = false;
    private Alligment alligment;

//    JPopupMenu popMenu = new JPopupMenu();
//    JMenuItem item1 = new JMenuItem("Delete");
//    JMenuItem item2 = new JMenuItem("Group");
//    JMenuItem item3 = new JMenuItem("unGroup");
//    public void setItem1(JMenuItem item1) {
//        item1.addActionListener(this);
//        item1.setActionCommand("Delete");
//        popMenu.removeAll();
//        popMenu.add(item1);   
//    }
//    public void setItem2(JMenuItem item2) {
//        item2.addActionListener(this);
//        item2.setActionCommand("Group");
//        popMenu.add(item2); 
//        
//    }
//    public void setItem3(JMenuItem item3) {
//        item3.addActionListener(this);
//        item3.setActionCommand("unGroup");
//        popMenu.add(item3); 
//        
//    }
    public ToolsPanel getToolspanel() {
        return toolspanel;
    }

    public void setToolspanel(ToolsPanel toolspanel) {
        this.toolspanel = toolspanel;
    }

    public PropertyPanel getPropertyPanel() {
        return propertyPanel;
    }

    public void setPropertyPanel(PropertyPanel propertyPanel) {
        this.propertyPanel = propertyPanel;
    }

//    public boolean isResizing() {
//        return Resizing;
//    }
//
//    public void setResizing(boolean Resizing) {
//        this.Resizing = Resizing;
//    }
    public boolean isMultiselect() {
        return Multiselect;
    }

    public void setMultiselect(boolean Multiselect) {
        this.Multiselect = Multiselect;
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode()==e.VK_CONTROL)
//            setMultiselect(true);
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        if(e.getKeyCode()==e.VK_CONTROL)
//            setMultiselect(false);
//    }
    /**
     * @return the numSelect
     */
    public int getNumSelect() {
        return numSelect;
    }

    /**
     * @param numSelect the numSelect to set
     */
    public void setNumSelect(int numSelect) {
        this.numSelect = numSelect;
    }

    private class ControlKeyPostProccessor implements KeyEventPostProcessor {

        public ControlKeyPostProccessor() {
        }

        @Override
        public boolean postProcessKeyEvent(KeyEvent e) {
            if (e.isControlDown()) {
                isCtrlpressed = true;
                return true;
            } else {
                isCtrlpressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (e.getID() == KeyEvent.KEY_PRESSED && SelectedShapes != null) {
                    for (Shape s : SelectedShapes) {
                        s.moveShapeBy(1, 0);
                    }
                    repaint();
                }
                return true;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (e.getID() == KeyEvent.KEY_PRESSED && SelectedShapes != null) {
                    for (Shape s : SelectedShapes) {
                        s.moveShapeBy(-1, 0);
                    }
                    repaint();
                }
                return true;
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (e.getID() == KeyEvent.KEY_PRESSED && SelectedShapes != null) {
                    for (Shape s : SelectedShapes) {
                        s.moveShapeBy(0, -1);
                    }
                    repaint();
                }
                return true;
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (e.getID() == KeyEvent.KEY_PRESSED && SelectedShapes != null) {
                    for (Shape s : SelectedShapes) {
                        s.moveShapeBy(0, 1);
                    }
                    repaint();
                }
                return true;
            }

            return true;
        }
    }

    public boolean saveImageAsPng(String filename) {
        try {
            ImageIO.write(offScreenImage, "png", new File(filename));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveImageAsSVG(String filename) {
        String svgString = String.format("<svg width =\"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\"> \n",
                w, h
        );
        for (Shape s : allShapes) {
            svgString += "\t" + s.toSVG() + "\n";
        }
        svgString += "</svg>";
        try {
            PrintWriter pw = new PrintWriter(filename);
            pw.println(svgString);
            pw.flush();
            pw.close();
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    public void setAllShapes(ArrayList<Shape> allShapes) {
        this.allShapes.clear();
        this.SelectedShapes.clear();
        this.allShapes.addAll(allShapes);
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame EditTextFrame;
    private javax.swing.JFrame QNumFrame;
    private javax.swing.JFrame TextFrame;
    private javax.swing.JButton cancelEdit;
    private javax.swing.JButton cancelQ;
    private javax.swing.JButton cancelText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldFontSize;
    private javax.swing.JTextField jTextFieldFontSize1;
    private javax.swing.JTextField jTextFieldText;
    private javax.swing.JTextField jTextFieldText1;
    private javax.swing.JTextField numAns;
    private javax.swing.JTextField numQ;
    private javax.swing.JButton okEdit;
    private javax.swing.JButton okQ;
    private javax.swing.JButton okText;
    // End of variables declaration//GEN-END:variables
}
