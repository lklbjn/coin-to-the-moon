package net.welights.jetbrainsplugin.cttm.constants;

/**
 * @author lklbjn
 */
public class CoinConstants {

    private CoinConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PLUGIN_NAME = "To Be A Rich Man";
    //非小号api暂时不支持筛选功能
    public static final String COIN_INFO_URL = "https://rest.coincap.io/v3/assets";
    public static final String CNY_TO_USD_URL = "https://open.er-api.com/v6/latest/USD";
    public static final String COIN_NAME = "Name";
    public static final String SYMBOL = "Code";
    public static final String COIN_LATEST_PRICE_USD = "USD";
    public static final String COIN_LATEST_PRICE_CNY = "CNY";
    public static final String RISE_AND_FALL_RATIO_1Hour = "Ratio(1Hour)";
    public static final String RISE_AND_FALL_RATIO_24Hour = "Ratio(24Hour)";
    public static final String RISE_AND_FALL_RATIO_7Day = "Ratio(7 Day)";
    public static final String TIMESTAMP_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String REFRESH_TIMESTAMP = "Updated at: %s";

}
