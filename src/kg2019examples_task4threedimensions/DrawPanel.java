/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import kg2019examples_task4threedimensions.draw.IDrawer;
import kg2019examples_task4threedimensions.draw.SimpleEdgeDrawer;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.screen.ScreenConverter;
import kg2019examples_task4threedimensions.third.Camera;
import kg2019examples_task4threedimensions.third.Scene;
import kg2019examples_task4threedimensions.transformation.IFunction;
import kg2019examples_task4threedimensions.transformation.Rotate;
import models.Icosahedron;
import models.Parallelepiped;
import models.Triangle;

/**
 *
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener, KeyListener {
    private Scene scene;
    private ScreenConverter sc;
    private Camera cam;
    private CameraController camController;
    private IFunction transformation;
    
    public DrawPanel() {
        super();
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new Camera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();
        
//        scene.getModelsList().add(new Parallelepiped(
//                new Vector3(-0.4f, -0.4f, -0.4f),
//                new Vector3(0.4f, 0.4f, 0.4f)
//        ));

        //scene.getModelsList().add(new Triangle(new Vector3(-0.4f, -0.4f, 1),new Vector3(1f, 1f, 1),new Vector3(1, 1, 2)));

        scene.getModelsList().add(new Icosahedron(new Vector3(0, 0, 0), 1));
        transformation = new Rotate(3, 0.09f);
        //transformation.getPoint(scene.getModelsList().get(0),)
        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }
    
    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D)bi.getGraphics();
        IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        //scene.getModelsList().get(0).rotate(0.01f);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
    }

    private void rotateFigure(Parallelepiped p, float angle){
        p.rotate(angle);
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    private boolean toRotate = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E){
            toRotate = !toRotate;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
