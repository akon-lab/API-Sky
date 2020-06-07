package servises.interfaces;

import domain.Background.AccessToken;
import domain.RealPerson.Administrator;

public interface IAuthorizationService<T> {

    T signIn(T data) throws Exception;


    AccessToken authenticateUser(T data) throws Exception;
}
