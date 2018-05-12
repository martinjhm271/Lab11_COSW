package co.edu.escuelaing.lab11.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.escuelaing.lab11.Network.Network;
import co.edu.escuelaing.lab11.Network.NetworkException;
import co.edu.escuelaing.lab11.Network.RequestCallback;
import co.edu.escuelaing.lab11.Network.RetrofitNetwork;
import co.edu.escuelaing.lab11.R;
import co.edu.escuelaing.lab11.model.todo;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final String TOKEN_KEY = "TOKEN_KEY";
    Network network;
    Context context = this;
    SharedPreferences sharedPref;
    String token;


    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        configureRecyclerView();
        Intent intent = getIntent();
        token = intent.getStringExtra("Token");
        sharedPref= context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        network = new RetrofitNetwork();
        token = sharedPref.getString(TOKEN_KEY, "");
        network.addSecureTokenInterceptor(token);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute( new Runnable()
        {
            @Override
            public void run()
            {
                network.getTodosList(new RequestCallback<List<todo>>() {
                    @Override
                    public void onSuccess(final List<todo> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setAdapter( new TodoAdapter( response ) );
                            }
                        });
                    }

                    @Override
                    public void onFailed(NetworkException e) {
                        e.getStackTrace();
                    }
                });

            }
        } );



    }
    private void configureRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );
        recyclerView.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );

    }
}
