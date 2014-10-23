package cn.youhui.utils;

public class DistanceUtil {

	private static final double EARTH_RADIUS = 637813700;//地球半径  m
	
	private static double rad(double d){
	   return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2, double lng2){
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}

	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(getDistance(31.9757	,118.7757,  31.9446, 118.7854));
		System.out.println(getDistatce(31.9446	,118.7854,  31.9757, 118.7757));
		System.out.println(System.currentTimeMillis());
	}
	
	public static double getDistatce(double lat1, double lon1, double lat2, double lon2) { 
        double R = 6378137; 
        double rate = Math.PI / 180;
        double distance = 0.0; 
        double dLat = (lat2 - lat1) * rate; 
        double dLon = (lon2 - lon1) * rate; 
        double sindlat = Math.sin(dLat / 2);
        double sindlon =  Math.sin(dLon / 2);
        double a = sindlat*sindlat + sindlon*sindlon*Math.cos(lat1 * rate)* Math.cos(lat2 * rate); 
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R; 
        return distance; 
    }
}
