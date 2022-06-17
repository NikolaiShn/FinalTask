package com.web.controllers;

import org.springframework.security.core.Authentication;

//интерфейс, чтобы получить аутентификацию и потом получить из аутентификации юзера
public interface IAuthenticationFacade {

    Authentication getAuthentication();
}
