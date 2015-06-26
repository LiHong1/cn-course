package cm.commons;

import javax.persistence.Embeddable;

/**
 * 地址
 *
 * @author leizhenchun
 *         2013-8-12
 */
@Embeddable
public final class Address {
    //省
    private String province;
    //市
    private String city;
    //街道
    private String street;
    //邮政编码
    private String postCode;

    /**
     * 构造函数
     */
    public Address() {
    }

    /**
     * 构造函数
     *
     * @param province
     * @param city
     * @param street
     */
    public Address(String province, String city, String street) {
        this.province = province;
        this.city = city;
        this.street = street;
    }

    /**
     * 构造函数
     *
     * @param province
     * @param city
     * @param street
     * @param postCode
     */
    public Address(String province, String city, String street, String postCode) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    /**
     * 获取整个地址，由省、市、街道组成
     *
     * @return
     */
    public String getAddress() {
        return province + city + street;
    }

    /**
     * 获取省
     *
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取街道
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 判断两个地址是否相同
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }

        Address object = (Address) other;
        if (object.province.equals(this.province) && object.city.equals(this.city)
                && object.street.equals(this.street)) {
            return true;
        }

        return false;
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return (province + city + street).hashCode();
    }

    /**
     * 将地址转换成字符串
     */
    @Override
    public String toString() {
        return province + city + street + postCode;
    }

    /**
     * 获取邮政编码
     *
     * @return
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}
