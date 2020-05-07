package com.anqi.demo.demosearch.entity;
/*
    "_id":"https://detail.tmall.com/item.htm?id=38701730304u0026skuId=65205296562",
    "卖家地址":"江苏南京",
    "快递费":"快递: 0.00",
    "优惠活动":"满199减10,满299减30,满499减60,可跨店",
    "商品ID":"38701730304",
    "原价":"339.00",
    "活动开始时间":"2016-11-01 00:00:00",
    "活动结束时间":"2016-11-11 23:59:59",
    "标题":"送镜盒]库博酷柏硅水凝胶隐形近视眼镜爱维舒月抛6片",
    "服务保障":"正品保证;公益宝贝;赠运费险;不支持7天退换;极速退款",
    "会场":"健康保健会场",
    "现价":"109.00"
*/

public class Item {

    private String itemId;

    private String urlId;

    private String sellAddress;

    private String courierFee;

    private String promotions;

    private String originalPrice;

    private String startTime;

    private String endTime;

    private String title;

    private String serviceGuarantee;

    private String venue;

    private String currentPrice;

    public Item() { }

    public Item(String itemId, String urlId, String sellAddress,
                String courierFee, String promotions, String originalPrice,
                String startTime, String endTime, String title, String serviceGuarantee,
                String venue, String currentPrice) {
        this.itemId = itemId;
        this.urlId = urlId;
        this.sellAddress = sellAddress;
        this.courierFee = courierFee;
        this.promotions = promotions;
        this.originalPrice = originalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.serviceGuarantee = serviceGuarantee;
        this.venue = venue;
        this.currentPrice = currentPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getSellAddress() {
        return sellAddress;
    }

    public void setSellAddress(String sellAddress) {
        this.sellAddress = sellAddress;
    }

    public String getCourierFee() {
        return courierFee;
    }

    public void setCourierFee(String courierFee) {
        this.courierFee = courierFee;
    }

    public String getPromotions() {
        return promotions;
    }

    public void setPromotions(String promotions) {
        this.promotions = promotions;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceGuarantee() {
        return serviceGuarantee;
    }

    public void setServiceGuarantee(String serviceGuarantee) {
        this.serviceGuarantee = serviceGuarantee;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }
}
