package jp.kota_ishimoto.youtubeclient.data.exceptions;

public class YoutubeClientRuntimeException extends RuntimeException {

    public YoutubeClientRuntimeException(String message) {
        super(message);
    }

    public YoutubeClientRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public YoutubeClientRuntimeException(Throwable cause) {
        super(cause);
    }

}
