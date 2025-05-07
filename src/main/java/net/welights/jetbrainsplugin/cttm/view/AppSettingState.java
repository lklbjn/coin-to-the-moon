package net.welights.jetbrainsplugin.cttm.view;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author lklbjn
 */
@State(name = "net.welights.jetbrainsplugin.cttm.ui.AppSettingState", storages = {@Storage("coin_to_the_moon.xml")})
public class AppSettingState implements PersistentStateComponent<AppSettingState> {

    // 默认值
    public static final boolean IS_RED_RISE = true;
    public static final String COIN_LIST = "BTC;ETH;BCH";
    public static final Integer RANK = 100;
    public static final String API_KEY = "0c21653d-0b89-4a5c-aea0-59554f2453d0";

    // 自定义值
    private boolean isRedRise = true;
    private String coinList = "BTC;ETH;BCH";
    private int rank = 100;
    private String apiKey;

    public static AppSettingState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingState.class);
    }

    public void reset() {
        setRedRise(IS_RED_RISE);
        setCoinList(COIN_LIST);
        setRank(RANK);
        setApiKey(API_KEY);
    }

    @Nullable
    @Override
    public AppSettingState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingState appSettingState) {
        XmlSerializerUtil.copyBean(appSettingState, this);
    }

    public String getCoinList() {
        return coinList;
    }

    public void setCoinList(String coinList) {
        this.coinList = coinList;
    }

    public boolean isRedRise() {
        return isRedRise;
    }

    public void setRedRise(boolean redRise) {
        isRedRise = redRise;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }
}
