package cm.commons;

import cm.commons.exception.BusinessException;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * 电子邮箱地址
 *
 * @author leizhenchun
 */
@Embeddable
public final class EmailAddress implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1898950922082756539L;
    // 邮箱地址
    private String address;

    /**
     * 邮箱地址构造函数
     *
     * @param address
     * @throws BusinessException
     */
    public EmailAddress(String address) {
        this.setAddress(address);
    }

    /**
     * 构造新的邮箱地址实例
     *
     * @param address
     * @return
     * @throws BusinessException
     */
    public static EmailAddress valueOf(String address) {
        return new EmailAddress(address);
    }

    /**
     * 判断两个邮箱地址是否相同
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmailAddress)) {
            return false;
        }

        EmailAddress object = (EmailAddress) other;
        return object.address.equals(this.address);
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return address.hashCode();
    }

    /**
     * 邮箱地址转换成字符串
     */
    @Override
    public String toString() {
        return address;
    }

    /**
     * 获取邮箱地址
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置邮箱地址
     *
     * @param address
     * @throws BusinessException
     */
    public void setAddress(String address) {
        if (address == null) {
            throw new BusinessException(CommonsErrorCode.EMAIL_ADDRESS_FORMAT_ERROR, "邮箱地址格式错误：address=null");
            // throw new IllegalArgumentException("null address!");
        }
        if (!isEmailAddress(address)) {
            throw new BusinessException(CommonsErrorCode.EMAIL_ADDRESS_FORMAT_ERROR, "邮箱地址格式错误：address" + address);
            // throw new IllegalArgumentException("Invalid email address!");
        }
        this.address = address;
    }

    /**
     * 模式匹配判断邮箱地址是否合法
     *
     * @param address
     * @return
     */
    private boolean isEmailAddress(String address) {
        Pattern pattern = Pattern
                .compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        // "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        return pattern.matcher(address).matches();
    }
}
