package com.witshare.mars.job.coindata;

import com.google.gson.Gson;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.EnumI18NProject;
import com.witshare.mars.dao.redis.RedisCommonDao;
import com.witshare.mars.util.HttpClientUtil;
import com.witshare.mars.util.RedisKeyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static com.witshare.mars.job.coindata.CoinDataShow.*;
import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * Created by user on 2018/4/12.
 */
@Component
public class ExchangeSpiderJob {

    public static final String NAME = "name";
    public static final String OTHER_NAME = "otherName";
    public static final String CHANGE = "change";
    private final static String HOST = "https://mapi.feixiaohao.com/v2/morecoin/?page=%s";
    private final static Integer MAX_PAGE = 30;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisCommonDao redisCommonDao;

    public LinkedList<CoinDataVo> getCoinData(String name) {
        LinkedList<CoinDataVo> CoinDataVoList = new LinkedList<>();
        if (StringUtils.isEmpty(name)) {
            return CoinDataVoList;
        }
        name = name.toUpperCase();
        String string = redisCommonDao.getHash(RedisKeyUtil.getCoinDataFinalKey(), name);
        if (!StringUtils.isEmpty(string)) {
            try {
                LinkedList<Map<String, String>> list = new Gson().fromJson(string, LinkedList.class);
                if (!CollectionUtils.isEmpty(list)) {
                    for (Map<String, String> map : list) {
                        CoinDataVo coinDataVo = new CoinDataVo();
                        coinDataVo.setName(map.get(NAME));
                        coinDataVo.setOtherName(map.get(OTHER_NAME));
                        coinDataVo.setChange(map.get(CHANGE));
                        if (StringUtils.equals(CurrentThreadContext.getI18N(), EnumI18NProject.PROJECT_DESCRIPTION_EN.getRequestLanguage())) {
                            coinDataVo.setMarketCap(map.get(MARKET_CAP_EN));
                            coinDataVo.setPrice(map.get(PRICE_EN));
                            coinDataVo.setMarketNum(map.get(MARKET_NUM_EN));
                            coinDataVo.setVolume(map.get(VOLUME_EN));
                        } else {
                            coinDataVo.setMarketCap(map.get(MARKET_CAP_ZH));
                            coinDataVo.setPrice(map.get(PRICE_ZH));
                            coinDataVo.setMarketNum(map.get(MARKET_NUM_ZH));
                            coinDataVo.setVolume(map.get(VOLUME_ZH));
                        }
                        CoinDataVoList.add(coinDataVo);
                    }
                }

            } catch (Exception e) {
                LOGGER.error("getCoinData  fail, name:{}", name);
            }
        }
        return CoinDataVoList;
    }

    public void getCoinDataJob() {

        LinkedList<CoinData> list = new LinkedList<>();
        //记录返回数据加工后的行数count
        int[] columnNum = new int[MAX_PAGE];
        for (int page = 1; page < MAX_PAGE; page++) {
            String url = String.format(HOST, page);
            String response = HttpClientUtil.doGet(url, null);
            Map<String, String> map = null;
            try {
                map = new Gson().fromJson(response, Map.class);
            } catch (Exception e) {
                LOGGER.error("getCoinDataJob fail,url:{},response:{}", url, response);
                continue;
            }
            String result2 = map.get("result2");
            if (StringUtils.isEmpty(result2)) {
                LOGGER.error("getCoinDataJob fail because no result2,url:{}", url);
                continue;
            }
            String sb = new StringBuilder("<table>").append(result2).append("</table>").toString();
            Document document = Jsoup.parse(sb);
            ExchangeSpiderUtil.removeNodes(document);
            Element table = document.select("table").first();
            if (table == null) {
                LOGGER.error("getCoinDataJob fail because no table,url:{}", url);
                continue;
            }
            Elements tr = table.select("tr");
            parseData(tr, columnNum, list);
        }
        LOGGER.info("getCoinDataJob  columnNum:{}", JSONObject.valueToString(columnNum));
        LOGGER.info("getCoinDataJob  result:{}", JSONObject.valueToString(list));
        //将原始数据存redis
        redisCommonDao.setString(RedisKeyUtil.getCoinDataOriginKey(), JSONObject.valueToString(list));
        //将数据整理到redis
        makeCoinData(list);
    }

    /**
     * 整理数据
     *
     * @param list
     */
    public void makeCoinData(List<CoinData> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        LinkedList<CoinDataShow> coinDataShows = new LinkedList<>();
        BigDecimal btcRate = new BigDecimal(0.159);
        for (CoinData coinData : list) {
            try {
                CoinDataShow coinDataShow = new CoinDataShow();
                //token
                String name = coinData.getName();
                coinDataShow.setName(name);
                String otherName = coinData.getOtherName();
                coinDataShow.setOtherName(otherName);
                //价格
                String priceC = coinData.getPriceC();
                String priceU = coinData.getPriceU();
                BigDecimal priceZhD = ExchangeSpiderUtil.moneyUtilToNum(priceC);
                String priceZh = ExchangeSpiderUtil.numToMoneyUnit(priceZhD, null, ExchangeSpiderUtil.EnumMoneyUnit.One, true, null);
                BigDecimal priceEnD = ExchangeSpiderUtil.moneyUtilToNum(priceU);
                String priceEn = ExchangeSpiderUtil.numToMoneyUnit(priceEnD, ExchangeSpiderUtil.EnumCountry.En, ExchangeSpiderUtil.EnumMoneyUnit.One, true, null);
                coinDataShow.setPriceZh(priceZh);
                coinDataShow.setPriceEn(priceEn);
                BigDecimal rate = btcRate;
                //计算汇率
                if (priceZhD != BigDecimal.ZERO) {
                    rate = priceEnD.divide(priceZhD, 5, ROUND_HALF_DOWN);
                }
                //获取比特币的汇率
                if ("BTC".equals(name)) {
                    btcRate = rate;
                }
                //如当前coin汇率超出正常范围就将比特币汇率赋值给当前货币
                if (rate.divide(btcRate, 2, ROUND_HALF_DOWN).compareTo(new BigDecimal(0.5)) < 0 || rate.divide(btcRate, 2, ROUND_HALF_DOWN).compareTo(new BigDecimal(1.5)) > 0) {
                    rate = btcRate;
                }

                //流通总量
                String marketNum = coinData.getMarketNum();
                BigDecimal marketNumD = ExchangeSpiderUtil.moneyUtilToNum(marketNum);
                String marketNumDZh = ExchangeSpiderUtil.numToMoneyUnit(marketNumD, null, null, null, null);
                String marketNumDEn = ExchangeSpiderUtil.numToMoneyUnit(marketNumD, ExchangeSpiderUtil.EnumCountry.En, null, null, null);
                coinDataShow.setMarketNumZh(marketNumDZh);
                coinDataShow.setMarketNumEn(marketNumDEn);
                //流通市值
                String marketCapC = coinData.getMarketCapC();
                BigDecimal marketCC = ExchangeSpiderUtil.moneyUtilToNum(marketCapC);
                if (marketCC.compareTo(BigDecimal.ZERO) == 0) {
                    marketCC = priceZhD.multiply(marketNumD);
                }
                String marketCCZh = ExchangeSpiderUtil.numToMoneyUnit(marketCC, null, null, true, null);
                String marketCCEn = ExchangeSpiderUtil.numToMoneyUnit(marketCC, ExchangeSpiderUtil.EnumCountry.En, null, true, rate);
                coinDataShow.setMarketCapEn(marketCCEn);
                coinDataShow.setMarketCapZh(marketCCZh);
                //成交额（24H）
                String volumeC = coinData.getVolumeC();
                String volumeU = coinData.getVolumeU();
                BigDecimal volumeCD = ExchangeSpiderUtil.moneyUtilToNum(volumeC);
                BigDecimal volumeUD = ExchangeSpiderUtil.moneyUtilToNum(volumeU);
                String volumeZh = ExchangeSpiderUtil.numToMoneyUnit(volumeCD, null, null, true, null);
                String volumeEn = ExchangeSpiderUtil.numToMoneyUnit(volumeUD, ExchangeSpiderUtil.EnumCountry.En, null, true, null);
                coinDataShow.setVolumeZh(volumeZh);
                coinDataShow.setVolumeEn(volumeEn);
                //涨幅（24H）
                String change = coinData.getChange();
                coinDataShow.setChange(change);
                coinDataShows.add(coinDataShow);
            } catch (Exception e) {
                LOGGER.info("getCoinDataJob coinDataShows  error coinData:{}", JSONObject.valueToString(coinData), e);
            }

        }
        LinkedHashMap<String, LinkedList<CoinDataShow>> map = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(coinDataShows)) {
            for (CoinDataShow coinDataShow : coinDataShows) {
                String name = coinDataShow.getName().toUpperCase();
                LinkedList<CoinDataShow> defaultList = map.getOrDefault(name, new LinkedList<CoinDataShow>());
                defaultList.add(coinDataShow);
                map.put(name, defaultList);
            }
            Iterator<Map.Entry<String, LinkedList<CoinDataShow>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, LinkedList<CoinDataShow>> next = iterator.next();
                redisCommonDao.putHash(RedisKeyUtil.getCoinDataFinalKey(), next.getKey(), new Gson().toJson(next.getValue()));
            }
        }
        LOGGER.info("getCoinDataJob coinDataShows  result:{}", JSONObject.valueToString(coinDataShows));
        LOGGER.info("getCoinDataJob btcRate:{}", JSONObject.valueToString(btcRate));
    }


    /**
     * 解析数据
     *
     * @param tr
     */
    private void parseData(Elements tr, int[] columnNum, LinkedList<CoinData> list) {
        if (!CollectionUtils.isEmpty(tr)) {
            for (Element t : tr) {
                try {
                    List<Node> nodes = t.childNodes();
                    Document parse = Jsoup.parse(nodes.toString());
                    ExchangeSpiderUtil.removeNodes(parse);
                    String[] split = parse.toString().split("\n");
                    columnNum[split.length]++;
                    String id = ExchangeSpiderUtil.getId(split[2]);
                    String name = ExchangeSpiderUtil.getName(split[3]);
                    String otherName = ExchangeSpiderUtil.getOtherName(split[4]);
                    String dataUsdP = ExchangeSpiderUtil.getDataUsd(split[4]);
                    String dataBtcP = ExchangeSpiderUtil.getDataBtc(split[4]);
                    String dataCnyP = ExchangeSpiderUtil.getDataCny(split[4]);
                    String change = ExchangeSpiderUtil.getChange(split[6]);
                    String marketCap = ExchangeSpiderUtil.getMarketCap(split[7]);
                    if (split.length == 11) {
                        String dataUsdV = ExchangeSpiderUtil.getDataUsd(split[8]);
                        String dataBtcV = ExchangeSpiderUtil.getDataBtc(split[8]);
                        String dataCnyV = ExchangeSpiderUtil.getDataCny(split[8]);
                        String marketNum = ExchangeSpiderUtil.index72(split[7]);
                        CoinData coinData = new CoinData(id, name, otherName, marketCap, dataUsdP, dataCnyP, dataBtcP, marketNum, dataUsdV, dataCnyV, dataBtcV, change, "");
                        list.add(coinData);
                    } else {//多了一行   <a href="https://etherscan.io/token/0xa33e729bf4fdeb868b534e1f20523463d9c46bee" rel="nofollow">10,000万*</a>, ,
                        String dataUsdV = ExchangeSpiderUtil.getDataUsd(split[9]);
                        String dataBtcV = ExchangeSpiderUtil.getDataBtc(split[9]);
                        String dataCnyV = ExchangeSpiderUtil.getDataCny(split[9]);
                        String marketNum = ExchangeSpiderUtil.getMarketNum(split[8]);
                        CoinData coinData = new CoinData(id, name, otherName, marketCap, dataUsdP, dataCnyP, dataBtcP, marketNum, dataUsdV, dataCnyV, dataBtcV, change, "");
                        list.add(coinData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
