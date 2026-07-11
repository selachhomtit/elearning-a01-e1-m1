package co.istad.sela.elearning.exception;

public record FiledErrorResponse(
        String filed,
        String reason
) {
}
