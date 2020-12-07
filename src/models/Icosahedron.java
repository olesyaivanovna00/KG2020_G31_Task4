package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class Icosahedron implements IModel {
    private Vector3 center;
    private Vector3 vRadius;
    private float radius;
    private Color c;
    private float angle;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Icosahedron(Vector3 center, float radius) {
        this.center = center;
        this.vRadius = new Vector3(center.getX(), center.getY(), radius);
        this.radius = radius;
        this.c = Color.black;
        this.angle = 0;
    }



    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> triangles = new LinkedList<>();
        double alpha = (2 * PI / 5);

        for (int j = 0; j < 4; j++) {
            //double angleRotated = (j / 2) * 2 * PI / 10;
            Vector3 p1, p2, p3;
            int level = levelForJ(j, false);
            int nextLevel = levelForJ(j, true);

            for (int i = 0; i < 5; i++) {
                float x = getXForLevel(nextLevel, i, alpha);
                float y = getYForLevel(nextLevel, i, alpha);


                float x1 = getXForLevel(nextLevel, i + 1, alpha); //next level point
                float y1 = getYForLevel(nextLevel, i + 1, alpha);

                p1 = new Vector3(x, y, getZForLevel(nextLevel));
                p2 = new Vector3(x1, y1, getZForLevel(nextLevel));

                x = getXForLevel(level, i, alpha);  //current level next point
                y = getYForLevel(level, i, alpha);

                p3 = (new Vector3(x, y, getZForLevel(level)));
                triangles.add(new PolyLine3D(Arrays.asList(p1, p2, p3), true, c));

            }
        }

        return triangles;
    }


    @Override
    public void rotate(float angle) {

    }

    private float getZForLevel(int level) {

        if (level == 0) {
            return (center.getZ() + radius);
        } else if (level == 1) {
            return (center.getZ() + radius / 2);
        } else if (level == 2) {
            return (center.getZ() - radius / 2);
        } else {
            return (center.getZ() - radius);
        }
    }

    private float getXForLevel(int level, int i, double alpha) {
        if (level == 0) {
            return center.getX();
        } else if (level == 1) {
            return (float) (radius * Math.cos(alpha * i + angle) + center.getX());
        } else if (level == 2) {
            return (float) (radius * Math.cos(alpha * i + (2 * PI / 10)+ angle) + center.getX());
        } else {
            return center.getX();
        }
    }

    private float getYForLevel(int level, int i, double alpha) {
        if (level == 0) {
            return center.getY();
        } else if (level == 1) {
            return (float) (radius * Math.sin(alpha * i + angle) + center.getY());
        } else if (level == 2) {
            return (float) (radius * Math.sin(alpha * i + (2 * PI / 10) + angle) + center.getY());
        } else {
            return center.getY();
        }
    }
    private int levelForJ(int j, boolean next){
        if (j == 0){
            if (next){
                return 1;
            } else {
                return 0;
            }
        } else if (j == 1){
            if (next){
                return 1;
            } else {
                return 2;
            }
        } else if (j == 2){
            if (next){
                return 1;
            } else {
                return 2;
            }
        } else {
            if (next) {
                return 2;
            } else {
                return 3;
            }
        }
    }



}
