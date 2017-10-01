package jp.kota_ishimoto.youtubeclient.data.exceptions

open class YoutubeClientRuntimeException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

}
