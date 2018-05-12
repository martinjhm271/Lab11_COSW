package co.edu.escuelaing.lab11.Network;

import java.util.List;

import co.edu.escuelaing.lab11.model.todo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;



interface NetworkService {
    @POST( "user/login" )
    Call<Token> login(@Body LoginWrapper user );
    @GET( "api/todo" )
    Call<List<todo>> getTodosList( );
    @POST("api/todo")
    Call<todo> createTodo(@Body todo todo);

}