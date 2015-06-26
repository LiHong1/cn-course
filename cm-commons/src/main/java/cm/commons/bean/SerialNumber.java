package cm.commons.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 序号
 *
 * @author leizhenchun
 */
@Entity
@Table(name = "T_C_SERIAL_NUMBER")
public class SerialNumber {
    // 下一值
    private Long nextNumber;
    // 最大值
    private Long maxNumber;
    // 值段长度
    private Integer numberLength;
    // 值段增量
    private Integer incrementBy;
    // 序列器名称
    private String keyName;
    // 前缀
    private String prefix;
    // 后缀
    private String suffix;

    /*
     * 获取序列号
     */
    public String next() {
        StringBuilder sb = new StringBuilder();
        if (prefix != null && !prefix.isEmpty()) {
            sb.append(prefix);
        }
        sb.append(String.format("%0" + numberLength + "d", nextNumber));
        if (suffix != null && !suffix.isEmpty()) {
            sb.append(suffix);
        }
        return sb.toString();
    }

    @Id
    @Column(name = "KEY_NAME")
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Column(name = "NEXT_NUMBER")
    public Long getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Long nextNumber) {
        this.nextNumber = nextNumber;
    }

    @Column(name = "NUMBER_LENGTH")
    public Integer getNumberLength() {
        return numberLength;
    }

    public void setNumberLength(Integer numberLength) {
        this.numberLength = numberLength;
    }

    @Column(name = "INCREMENT_BY")
    public Integer getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(Integer incrementBy) {
        this.incrementBy = incrementBy;
    }

    @Column(name = "PREFIX")
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Column(name = "SUFFIX")
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Column(name = "MAX_NUMBER")
    public Long getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Long maxNumber) {
        this.maxNumber = maxNumber;
    }

}
