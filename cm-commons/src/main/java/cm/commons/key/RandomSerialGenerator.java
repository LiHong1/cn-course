package cm.commons.key;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;

/**
 * 随机生成序列号，供测试用
 *
 * @author leizhenchun
 */
public class RandomSerialGenerator implements SerialGenerator {
    private int numberLength = 20;

    public int getNumberLength() {
        return numberLength;
    }

    public void setNumberLength(int numberLength) {
        this.numberLength = numberLength;
    }


    public String get() {
        return RandomStringUtils.random(numberLength);
    }


    public String get(Calendar now) {
        return RandomStringUtils.random(numberLength);
    }


    public Long nextId() {
        return null;
    }


    public String nextKey() {
        return nextId().toString();
    }

}
