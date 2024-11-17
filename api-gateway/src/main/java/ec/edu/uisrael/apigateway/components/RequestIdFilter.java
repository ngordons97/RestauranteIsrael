package ec.edu.uisrael.apigateway.components;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString(); // Generar un requestId único
        MDC.put("requestId", requestId); // Agregar requestId al MDC

        try {
            chain.doFilter(request, response); // Continuar con la solicitud
        } finally {
            MDC.clear(); // Limpiar el contexto después de la solicitud
        }
    }
}