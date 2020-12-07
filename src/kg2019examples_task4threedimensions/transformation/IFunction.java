package kg2019examples_task4threedimensions.transformation;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.util.List;

public interface IFunction {

    List<PolyLine3D> getPoint(IModel model, Vector3 dPoint, int fixedAxes);
}
