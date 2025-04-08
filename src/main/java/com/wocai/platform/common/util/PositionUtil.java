package com.wocai.platform.common.util;

public class PositionUtil {


    /**
     * 赤道半径（单位：米）
     */
    private static final double EQUATOR_RADIUS = 6378137;

    /**
     * 地球平均半径（单位：米）
     */
    private static final double EARTH_AVG_RADIUS = 6371000;

    /**
     * 方法一：（反余弦计算方式）
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离，单位m
     */
    public static double getDistance1(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lon1 = Math.toRadians(longitude1);
        double lon2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lon1 - lon2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘赤道半径, 返回单位: 米
        s = s * EQUATOR_RADIUS;
        return s;
    }
    public static double getDistance2(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 经纬度（角度）转弧度。弧度作为作参数，用以调用Math.cos和Math.sin
        // A经弧度
        double radiansAX = Math.toRadians(longitude1);
        // A纬弧度
        double radiansAY = Math.toRadians(latitude1);
        // B经弧度
        double radiansBX = Math.toRadians(longitude2);
        // B纬弧度
        double radiansBY = Math.toRadians(latitude2);

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX) + Math.sin(radiansAY) * Math.sin(radiansBY);
        // System.out.println("cos = " + cos); // 值域[-1,1]

        // 反余弦值
        double acos = Math.acos(cos);
        // System.out.println("acos = " + acos); // 值域[0,π]
        // System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]

        // 最终结果
        return EARTH_AVG_RADIUS * acos;
    }


    public static void main(String[] args) {

        double longitude1 = 120.561168;
        double latitude1 = 30.655121;
        double longitude2 = 120.053780;
        double latitude2 = 30.237210;

        double distance1 = PositionUtil.getDistance1(longitude1, latitude1, longitude2, latitude2);
        double distance2 = PositionUtil.getDistance2(longitude1, latitude1, longitude2, latitude2);

        System.out.println("方法1算出的距离：" + distance1);
        System.out.println("方法1算出的距离：" + distance2);

    }


}
