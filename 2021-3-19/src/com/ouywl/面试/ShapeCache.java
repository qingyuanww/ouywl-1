package com.ouywl.面试;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-14 21:50
 */

public class ShapeCache {
    private static HashMap<String,Shape> map=new HashMap<>();

    public static Shape getShape(String id)  {
        Shape shape = map.get(id);
        Shape returnShape = null;
        try {
            returnShape=(Shape) shape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return returnShape;
    }
    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        map.put(circle.getId(),circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        map.put(rectangle.getId(),rectangle);

        Square square = new Square();
        square.setId("3");
        map.put(square.getId(),square);
    }
}
