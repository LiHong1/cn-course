package cm.commons.exception;


public class BusinessException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 6927306761441364988L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误参数
     */
    private Object[] params;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, Object[] params, String message) {
        super(message);
        this.code = code;
        this.params = params;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String code, Object[] params, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.params = params;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
