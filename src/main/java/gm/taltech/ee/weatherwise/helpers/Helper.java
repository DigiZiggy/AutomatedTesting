package gm.taltech.ee.weatherwise.helpers;

public class Helper {

    public long round_unix_timestamp_to_nearest_hour(long unixSeconds) {
        unixSeconds -= unixSeconds % 3600;
        return unixSeconds;
    }
}
