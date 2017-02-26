package br.com.acme.exception;

/**
 * Created by eric-nasc on 26/02/17.
 */
public class AcmeServiceException extends RuntimeException {

    public AcmeServiceException() {
        super();
    }

    public AcmeServiceException(String message) {
        super(message);
    }

    public AcmeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AcmeServiceException(Throwable cause) {
        super(cause);
    }
}