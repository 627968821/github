package club.banyuan.mbm.exception;

public class SupplierNameException extends MbmException {
    public SupplierNameException() {
        super();
    }

    public SupplierNameException(String message) {
        super(message);
    }

    public SupplierNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplierNameException(Throwable cause) {
        super(cause);
    }

    public SupplierNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
