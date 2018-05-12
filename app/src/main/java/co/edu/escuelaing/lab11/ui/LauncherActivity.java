package co.edu.escuelaing.lab11.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import co.edu.escuelaing.lab11.R;

public class LauncherActivity extends AppCompatActivity {

    private final String TOKEN_KEY = "TOKEN_KEY";
    SharedPreferences sharedPref;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        sharedPref= context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        verifyUserToken();
    }

    private void verifyUserToken() {

        String token = sharedPref.getString(TOKEN_KEY,"");
        if(token.equals("")){
            Intent intent = new Intent( context, Login.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent( context, MainActivity.class);
            startActivity(intent);
        }
    }
}
