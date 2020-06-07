package repository.interfaces;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;

public interface ContainerResponseFilter {
    void filter(ContainerRequestContext requestContext,
                ContainerResponseContext responseContext);
}
