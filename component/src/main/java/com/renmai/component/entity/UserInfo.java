package com.renmai.component.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-11-19 19:50
 * @depiction   ：
 */
public class UserInfo implements Parcelable {


    /**
     * city : 北京
     * cityCode : 110001
     * country : 中国
     * education : 本科
     * email : email@email.com
     * gender : 1
     * homeAddress : 北京东城银河soho
     * idNo : 11000010303
     * jobTitle : 总经理
     * monthIncome : 1000-5000
     * province : 北京
     * provinceCode : 100000
     * userId : 110000
     * userName : 孙悟空
     */

    private String city;
    private String cityCode;
    private String country;
    private String education;
    private String email;
    private int gender;
    private String homeAddress;
    private String idNo;
    private String jobTitle;
    private String monthIncome;
    private String province;
    private String provinceCode;
    private int userId;
    private String userName;
    private BankCard bankCard;

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public String getProvinceAndCity() {
        String str = "";
        if (!TextUtils.isEmpty(province)) {
            str = province + " ";
        }
        if (!TextUtils.isEmpty(city)) {
            str = str + city;
        }
        return str;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class BankCard implements Parcelable {


        /**
         * bankCardNo (string, optional): 银行卡号 ,
         * bankId (integer, optional): 银行卡id ,
         * bankLogo (string, optional): logo ,
         * bankName (string, optional): 银行名称
         */

        private String bankCardNo;
        private String bankId;
        private String bankLogo;
        private String bankName;

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getBankLogo() {
            return bankLogo;
        }

        public void setBankLogo(String bankLogo) {
            this.bankLogo = bankLogo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.bankCardNo);
            dest.writeString(this.bankId);
            dest.writeString(this.bankLogo);
            dest.writeString(this.bankName);
        }

        public BankCard() {
        }

        protected BankCard(Parcel in) {
            this.bankCardNo = in.readString();
            this.bankId = in.readString();
            this.bankLogo = in.readString();
            this.bankName = in.readString();
        }

        public static final Creator<BankCard> CREATOR = new Creator<BankCard>() {
            @Override
            public BankCard createFromParcel(Parcel source) {
                return new BankCard(source);
            }

            @Override
            public BankCard[] newArray(int size) {
                return new BankCard[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeString(this.cityCode);
        dest.writeString(this.country);
        dest.writeString(this.education);
        dest.writeString(this.email);
        dest.writeInt(this.gender);
        dest.writeString(this.homeAddress);
        dest.writeString(this.idNo);
        dest.writeString(this.jobTitle);
        dest.writeString(this.monthIncome);
        dest.writeString(this.province);
        dest.writeString(this.provinceCode);
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeParcelable(this.bankCard, flags);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.city = in.readString();
        this.cityCode = in.readString();
        this.country = in.readString();
        this.education = in.readString();
        this.email = in.readString();
        this.gender = in.readInt();
        this.homeAddress = in.readString();
        this.idNo = in.readString();
        this.jobTitle = in.readString();
        this.monthIncome = in.readString();
        this.province = in.readString();
        this.provinceCode = in.readString();
        this.userId = in.readInt();
        this.userName = in.readString();
        this.bankCard = in.readParcelable(BankCard.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
