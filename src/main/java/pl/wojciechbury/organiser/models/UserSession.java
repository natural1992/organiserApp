package pl.wojciechbury.organiser.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.wojciechbury.organiser.models.entities.UserEntity;

@Component
@Data
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private boolean isLoggedIn;
    private UserEntity userEntity;
}
