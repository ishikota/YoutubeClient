package jp.kota_ishimoto.youtubeclient.data.exceptions;

public class IllegalCredentialAccessException extends YoutubeClientRuntimeException {
    public IllegalCredentialAccessException(String message) {
        super(message);
    }
}
