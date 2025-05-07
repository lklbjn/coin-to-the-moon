package net.welights.jetbrainsplugin.cttm.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.welights.jetbrainsplugin.cttm.constants.CoinConstants;
import net.welights.jetbrainsplugin.cttm.dto.CryptoCurrency;
import net.welights.jetbrainsplugin.cttm.util.HttpClientPool;
import net.welights.jetbrainsplugin.cttm.util.PluginLogUtil;
import net.welights.jetbrainsplugin.cttm.view.AppSettingState;
import java.util.logging.Level;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * @author will
 */
public class CoinPriceHandler extends AbstractCoinPriceHandler {
    public CoinPriceHandler(JTable table, JLabel label) {
        super(table, label);
    }

    @Override
    public String[] getColumnNames() {
        return handleColumnNames(coinColumnNames);
    }

    @Override
    public void load(List<String> symbols, int rank) {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (symbols.isEmpty()) {
                return;
            }
            String apiKey = AppSettingState.getInstance().getApiKey();
            String coinInfoUrl = CoinConstants.COIN_INFO_URL + "?apiKey=" + apiKey + "&limit=";
            for (int i = 0; i <= 3; i++) {
                try {
                    String entity = HttpClientPool.getInstance().get(coinInfoUrl + rank);
                    parse(symbols, entity);
                    updateView();
                    break;
                } catch (Exception e) {
                    PluginLogUtil.info(e.getMessage());
                    PluginLogUtil.info("stops updating " + jTable.getToolTipText() + " data because of " + e.getMessage());
                }
            }

        }, 0L, 1, TimeUnit.HOURS);
        PluginLogUtil.info("updating " + jTable.getToolTipText() + " data");
    }

    /**
     * 根据币种筛选
     *
     * @param symbols
     * @param entity
     * @return void
     * @author lklbjn
     * @version 1.0.0.0
     * @since 10:28 2025/5/7
     */
    private void parse(List<String> symbols, String entity) {
        double rate = 7.25;
        try {
            String parities = HttpClientPool.getInstance().get(CoinConstants.CNY_TO_USD_URL);
            String asString = JsonParser.parseString(parities).getAsJsonObject().getAsJsonObject("rates").get("CNY").getAsString();
            rate = Double.parseDouble(asString);
            System.out.println("USD TO RMB汇率为：" + rate);
        } catch (Exception e) {
            PluginLogUtil.info(e.getMessage());
        }
        double finalRate = rate;
        JsonParser.parseString(entity).getAsJsonObject().getAsJsonArray("data").forEach(element -> {
            JsonObject coinObj = element.getAsJsonObject();
            if (symbols.contains(coinObj.get("symbol").getAsString())) {
                CryptoCurrency coinInfo = new CryptoCurrency();
                System.out.println("element单元为：" + coinObj.toString());
                coinInfo.setSymbol(coinObj.get("symbol").getAsString());
                coinInfo.setName(coinObj.get("name").getAsString());
                coinInfo.setLatestPriceUs(coinObj.get("priceUsd").getAsDouble());
                coinInfo.setLatestPriceCny(coinObj.get("priceUsd").getAsDouble() * finalRate);
//            coinInfo.setChangeRatio1Hour(coinObj.get("percent_change_1h").getAsDouble());
                coinInfo.setChangeRatio24Hour(coinObj.get("changePercent24Hr").getAsDouble());
//            coinInfo.setChangeRatio7Day(coinObj.get("percent_change_7d").getAsDouble());
                updateCoinInfo(coinInfo);
            }
        });
    }
}