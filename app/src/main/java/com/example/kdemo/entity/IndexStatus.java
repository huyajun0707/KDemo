package com.example.kdemo.entity;

import java.util.List;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-10-29 16:56
 * @depiction ：
 */
public class IndexStatus {

    @Override
    public String toString() {
        return "IndexStatus{" +
                "buttonText='" + buttonText + '\'' +
                ", buttonUrl='" + buttonUrl + '\'' +
                ", creditAmount=" + creditAmount +
                ", canRaiseAmount=" + canRaiseAmount +
                ", customerPhone='" + customerPhone + '\'' +
                ", maxAmount=" + maxAmount +
                ", statusText='" + statusText + '\'' +
                ", styleCode=" + styleCode +
                ", userStatus='" + userStatus + '\'' +
                ", withdrawId=" + withdrawId +
                ", attachTexts=" + attachTexts +
                '}';
    }

    /**
     * attachTexts : ["借款10万日息低至20元","5分钟急速审批"]
     * buttonText : 速戳提现
     * buttonUrl : withdrawdetail
     * creditAmount : 20000
     * canRaiseAmount : true
     * customerPhone : 400-180-5665
     * maxAmount : 10000
     * statusText : 额度计算中
     请稍后...
     * styleCode : 6
     * userStatus : NO_APPLY
     * withdrawId : 669
     */

    private String buttonText;
    private String buttonUrl;
    private int creditAmount;
    private boolean canRaiseAmount;
    private String customerPhone;
    private int maxAmount;
    private String statusText;
    private int styleCode;
    private String userStatus;
    private int withdrawId;
    private List<String> attachTexts;

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public boolean isCanRaiseAmount() {
        return canRaiseAmount;
    }

    public void setCanRaiseAmount(boolean canRaiseAmount) {
        this.canRaiseAmount = canRaiseAmount;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(int styleCode) {
        this.styleCode = styleCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(int withdrawId) {
        this.withdrawId = withdrawId;
    }

    public List<String> getAttachTexts() {
        return attachTexts;
    }

    public void setAttachTexts(List<String> attachTexts) {
        this.attachTexts = attachTexts;
    }
}
