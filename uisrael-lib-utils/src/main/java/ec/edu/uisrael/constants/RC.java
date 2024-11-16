package ec.edu.uisrael.constants;

/**
 * Enum que representa los códigos de estado HTTP y sus mensajes asociados.
 */
public enum RC {

    // --- 2xx Success ---
    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),

    // --- 4xx Client Errors ---
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),

    // --- 5xx Server Errors ---
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    private final int code;
    private final String message;

    RC(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Obtiene el código de estado HTTP.
     * 
     * @return el código HTTP.
     */
    public int getCode() {
        return code;
    }

    /**
     * Obtiene el mensaje asociado al código de estado HTTP.
     * 
     * @return el mensaje del estado.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Devuelve el enum correspondiente al código proporcionado.
     * 
     * @param code el código HTTP.
     * @return el enum correspondiente o {@code null} si no existe.
     */
    public static RC fromCode(int code) {
        for (RC status : RC.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null; // O lanza una excepción si prefieres manejar errores directamente.
    }

    @Override
    public String toString() {
        return code + " " + message;
    }
}
