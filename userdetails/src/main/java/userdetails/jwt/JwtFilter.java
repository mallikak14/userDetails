package userdetails.jwt;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest=(HttpServletRequest) request;
        final HttpServletResponse httpResponse=(HttpServletResponse) response;
        final String authHeader=httpRequest.getHeader("authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new ServletException();
        }
        final String token=authHeader.substring(7);
        Claims claims=Jwts.parser().setSigningKey("wHyU8pBzXdr8eG7VRmBQiJSkZ2hKABTspNYIbFRgN4k")
        .parseClaimsJws(token).getBody();
        request.setAttribute("token", token);
        chain.doFilter(httpRequest, httpResponse);
    }
    
    
}
