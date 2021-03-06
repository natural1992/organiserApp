package pl.wojciechbury.organiser.models;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



@Configuration

public class LoginInterceptor extends HandlerInterceptorAdapter {



    public static final String PASSWORD_HEADER_API = "api-password";



    @Value("${api}")

    String ourApiKey;



    final UserSession userSession;



    @Autowired

    public LoginInterceptor(UserSession userSession) {

        this.userSession = userSession;

    }



    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().contains("api")){

            String passwordApi = request.getHeader(PASSWORD_HEADER_API);

            if(passwordApi == null || !passwordApi.equals(ourApiKey)){

                response.sendError(403, "Bad api key");

                return false;

            }

        }

        //&key=jakiestajnehaslo





        if((!request.getRequestURI().contains("login")

                && !request.getRequestURI().contains("register"))

                && !userSession.isLoggedIn()){

            response.sendRedirect("/user/login");

            return false;

        }







        return super.preHandle(request, response, handler);

    }
}
