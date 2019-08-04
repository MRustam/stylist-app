package live.evsianna.stylist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AccessDeniedException ex) throws IOException {

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info(auth.getName() + " was trying to access protected resource: " + request.getRequestURI());
        }
        response.sendRedirect(request.getContextPath() + "/access-denied");
    }

}
