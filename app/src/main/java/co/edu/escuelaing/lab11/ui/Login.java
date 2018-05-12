package co.edu.escuelaing.lab11.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.escuelaing.lab11.Network.LoginWrapper;
import co.edu.escuelaing.lab11.Network.Network;
import co.edu.escuelaing.lab11.Network.NetworkException;
import co.edu.escuelaing.lab11.Network.RequestCallback;
import co.edu.escuelaing.lab11.Network.RetrofitNetwork;
import co.edu.escuelaing.lab11.Network.Token;
import co.edu.escuelaing.lab11.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText edtUsername, edtPassword;
    private final String TOKEN_KEY = "TOKEN_KEY";
    Button btnSignIn;
    Network network;
    Token token;
    Context context = this;
    SharedPreferences sharedPref;
    Intent intent;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        network = new RetrofitNetwork();
        sharedPref= context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        intent = new Intent( context, MainActivity.class);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnSignIn.getId()){
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            RequestCallback<Token> requestCallback = new RequestCallback<Token>() {
                @Override
                public void onSuccess(Token response) {
                    token = response;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(TOKEN_KEY,token.getAccessToken());
                    editor.apply();

                }

                @Override
                public void onFailed(NetworkException e) {

                }
            };
            network.login(new LoginWrapper(username,password),requestCallback);
            startActivity(intent);
        }
    }
}
