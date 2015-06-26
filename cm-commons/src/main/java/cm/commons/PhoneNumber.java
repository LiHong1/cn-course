package cm.commons;

import cm.commons.exception.BusinessException;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * 手机号码
 *
 * @author leizhenchun
 */
@Embeddable
public final class PhoneNumber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -476219517214020785L;
    // 手机号码
    private String number;

    /**
     * 构造函数
     *
     * @param number
     * @throws ModelException
     */
    public PhoneNumber(String number) {
        setNumber(number);
    }

    /**
     * 构造函数
     *
     * @param number
     * @return
     * @throws ModelException
     */
    public static PhoneNumber valueOf(String number) {
        return new PhoneNumber(number);
    }

    /**
     * 获取号码
     *
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置号码
     *
     * @param number
     * @throws ModelException
     */
    public void setNumber(String number) {
        if (number == null) {
            throw new BusinessException(CommonsErrorCode.PHONE_NUMBER_FORMAT_ERROR, "手机号码格式错误：number=null");
            // throw new IllegalArgumentException("number is null!");
        }
        if (!isNumeric(number)) {
            throw new BusinessException(CommonsErrorCode.PHONE_NUMBER_FORMAT_ERROR, "手机号码格式错误：number="
                    + number.toString());
            // throw new IllegalArgumentException("Invalid phone number!");
        }
        this.number = number;
    }

    /**
     * 判断两个号码是否相同
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneNumber)) {
            return false;
        }

        PhoneNumber object = (PhoneNumber) other;
        return object.number.equals(this.number);
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return number.hashCode();
    }

    /**
     * 将电话号码转换成字符串
     */
    @Override
    public String toString() {
        return number;
    }

    /**
     * 判断字符串是否为合法电话号码
     *
     * @param number
     * @return
     */
    private boolean isNumeric(String number) {
        // [0-9]* // "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
        Pattern pattern = Pattern.compile("^1\\d{10}$");
        return pattern.matcher(number).matches();
    }
}
