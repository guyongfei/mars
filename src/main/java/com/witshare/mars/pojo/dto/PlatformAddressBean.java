package com.witshare.mars.pojo.dto;

/**
 * Created by user on 2018/6/27.
 */
public class PlatformAddressBean {
    private String address;

    public PlatformAddressBean() {
    }

    public static PlatformAddressBean newInstance() {
        return new PlatformAddressBean();
    }


    public String getAddress() {
        return address;
    }

    public PlatformAddressBean setAddress(String address) {
        this.address = address;
        return this;
    }
}
