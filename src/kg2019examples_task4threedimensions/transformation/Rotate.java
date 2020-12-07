package kg2019examples_task4threedimensions.transformation;

import kg2019examples_task4threedimensions.math.Matrix4;
import kg2019examples_task4threedimensions.math.Matrix4Factories;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.util.ArrayList;
import java.util.List;

public class Rotate implements IFunction{
    private int fixedAxes;
    private Matrix4 rotationMatrix;

    public Rotate(int fixedAxes, float angle) {
        this.fixedAxes = fixedAxes;
        this.rotationMatrix = Matrix4Factories.rotationXYZ(angle, fixedAxes);
    }



    @Override
    public List<PolyLine3D> getPoint(IModel model, Vector3 dPoint, int fixedAxes) {

        return new ArrayList<PolyLine3D>();
    }
}
