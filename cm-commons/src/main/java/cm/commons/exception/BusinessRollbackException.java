package cm.commons.exception;

/**
 * 需要回滚异常类
 *
 * @author lzc
 */
public class BusinessRollbackException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = -7676406081968492999L;

    public BusinessRollbackException(String message) {
        super(message);
    }

    public BusinessRollbackException(Throwable cause) {
        super(cause);
    }

    public BusinessRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

/*    public BusinessRollbackException(Integer errorCode) {
        super(errorCode);
    }

    public BusinessRollbackException(Integer errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BusinessRollbackException(Integer errorCode, String message) {
        super(errorCode, message);
    }
    
    public BusinessRollbackException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public BusinessRollbackException(Integer errorCode, String message, String errorMsg) {
        super(errorCode, message, errorMsg);
    }

    public BusinessRollbackException(Integer errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public BusinessRollbackException(Integer errorCode, String message, String errorMsg, Throwable cause) {
        super(errorCode, message, errorMsg, cause);
    }*/

}
