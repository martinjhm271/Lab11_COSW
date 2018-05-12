package co.edu.escuelaing.lab11.Network;

import java.util.List;

import co.edu.escuelaing.lab11.model.todo;



public interface Network {
    void login( LoginWrapper loginWrapper, RequestCallback<Token> requestCallback );
    void getTodosList(RequestCallback<List<todo>> requestCallback);
    void createTodo( todo todo);

    void addSecureTokenInterceptor(String accessToken);
}
