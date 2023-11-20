package experiment_11_20;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyJSONReader {

    // 地球半径，单位：千米
    private static final double EARTH_RADIUS = 6371;

    public static void main(String[] args)  {
        {
            String jsonContent = null;
            try {
                jsonContent = new String(Files.readAllBytes(Paths.get("world.json")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            parseJson(jsonContent);
        }
    }



    public static void parseJson(String jsonContent) {
        JSONObject jsonObject = JSON.parseObject(jsonContent);
        JSONArray features = jsonObject.getJSONArray("features");

        for (int i = 0; i < features.size(); i++) {
            JSONObject feature = features.getJSONObject(i);
            String countryName = feature.getJSONObject("properties").getString("name");
            JSONArray geometry = feature.getJSONObject("geometry").getJSONArray("coordinates");

            // Check if it's a MultiPolygon or a Polygon
            if (geometry.size() == 1) {
                // Single Polygon
                JSONArray coordinates = getCoordinates(geometry.get(0));
                double area = calculateArea(coordinates);
                System.out.println("Country: " + countryName + ", Area: " + area + " square kilometers");
            } else {
                // MultiPolygon
                for (int j = 0; j < geometry.size(); j++) {
                    JSONArray coordinates = getCoordinates(geometry.get(j));
                    double area = calculateArea(coordinates);
                    System.out.println("Country: " + countryName + " (Part " + (j + 1) + "), Area: " + area + " square kilometers");
                }
            }
        }
    }

    private static JSONArray getCoordinates(Object element) {
        // Check the type of the element
        if (element instanceof JSONArray) {
            return (JSONArray) element;
        } else if (element instanceof List) {
            // If it's a List, convert it to JSONArray
            return (JSONArray) JSON.toJSON(element);
        } else {
            throw new RuntimeException("Unexpected element type: " + element.getClass());
        }
    }



    public static double calculateArea(Object coordinates) {
        double totalArea = 0.0;

        if (coordinates instanceof JSONArray) {
            // 单个多边形的情况
            JSONArray polygon = (JSONArray) coordinates;
            totalArea += calculateSinglePolygonArea(polygon);
        } else if (coordinates instanceof List) {
            // 多个多边形的情况
            List<?> polygons = (List<?>) coordinates;
            for (Object polygon : polygons) {
                totalArea += calculateSinglePolygonArea((JSONArray) polygon);
            }
        } else {
            throw new RuntimeException("Unexpected coordinates type: " + coordinates.getClass());
        }

        return totalArea;
    }


    // ...

    private static double calculateSinglePolygonArea(JSONArray polygon) {
        BigDecimal area = BigDecimal.ZERO;

        // 遍历多边形的所有顶点
        for (int j = 0; j < polygon.size() - 1; j++) {
            Object point1Obj = polygon.get(j);
            Object point2Obj = polygon.get(j + 1);

            if (!(point1Obj instanceof JSONArray) || !(point2Obj instanceof JSONArray)) {
                throw new RuntimeException("Unexpected point type in polygon: " + point1Obj.getClass() + ", " + point2Obj.getClass());
            }

            JSONArray point1 = (JSONArray) point1Obj;
            JSONArray point2 = (JSONArray) point2Obj;

            BigDecimal lat1 = new BigDecimal(point1.getString(1));
            BigDecimal lng1 = new BigDecimal(point1.getString(0));
            BigDecimal lat2 = new BigDecimal(point2.getString(1));
            BigDecimal lng2 = new BigDecimal(point2.getString(0));
            BigDecimal sinLat1 = BigDecimal.valueOf(Math.sin(lat1.multiply(BigDecimal.valueOf(Math.PI)).divide(BigDecimal.valueOf(180), 10, BigDecimal.ROUND_HALF_UP).doubleValue()));
            BigDecimal sinLat2 = BigDecimal.valueOf(Math.sin(lat2.multiply(BigDecimal.valueOf(Math.PI)).divide(BigDecimal.valueOf(180), 10, BigDecimal.ROUND_HALF_UP).doubleValue()));

            // 使用 BigDecimal 进行精确的除法运算
            BigDecimal lngDifference = lng2.subtract(lng1);
            if (lngDifference.signum() != 0) {
                area = area.add(lngDifference.multiply(BigDecimal.valueOf(2).add(sinLat1).add(sinLat2)));
            }
        }

        // 使用 BigDecimal 进行精确的除法运算
        if (area.signum() != 0) {
            return area.multiply(BigDecimal.valueOf(EARTH_RADIUS).multiply(BigDecimal.valueOf(EARTH_RADIUS)).divide(BigDecimal.valueOf(2), 10, BigDecimal.ROUND_HALF_UP)).doubleValue();
        } else {
            return 0.0;
        }
    }

}