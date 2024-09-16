package org.example.exception

enum class ErrorType(
    val message: String,
) {
    SLIDE_NOT_FOUND("Slide not found"),
    INVALID_REQUEST("Invalid request"),
    INVALID_PARAMETER("Invalid parameter"),
    ANALYSIS_RESULT_NOT_FOUND("Analysis result not found"),
    INVALID_ANALYSIS_RESULT("Analysis result is invalid"),
    REQUEST_NOT_YET("Request not yet"),
}