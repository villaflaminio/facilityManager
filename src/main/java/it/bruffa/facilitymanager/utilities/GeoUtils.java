package it.bruffa.facilitymanager.utilities;

public class GeoUtils {

    //credits to  https://it.martech.zone/calculate-great-circle-distance/
    public static double getDistanceBetweenPointsNew(double latitude1, double longitude1, double latitude2, double longitude2, boolean isMiles) {
        double theta = longitude1 - longitude2;
        double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
                Math.sin(latitude1 * (Math.PI/180)) * Math.sin(latitude2 * (Math.PI/180)) +
                        Math.cos(latitude1 * (Math.PI/180)) * Math.cos(latitude2 * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
        );
        if (isMiles) {
            return Math.round(distance);
        } else  {
            return Math.round(distance * 1.609344);
        }
    }
}
