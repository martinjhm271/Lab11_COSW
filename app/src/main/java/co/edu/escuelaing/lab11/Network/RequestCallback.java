package co.edu.escuelaing.lab11.Network;



public interface RequestCallback<T> {

    void onSuccess( T response );

    void onFailed( NetworkException e );

}