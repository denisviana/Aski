package br.com.aski.aski;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by denisvcosta on 12/03/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private Button btnLogin;
    private Button btnLogin_google;
    private TextView tvCadastrar;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuth != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.id_client_web))
                .requestEmail()
                .build();


        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();

                if(usuario != null){
                    Log.d(TAG, "Autenticado: "+ usuario.getUid());
                } else {
                    Log.d(TAG, "Não autenticado");
                }
            }
        };

        btnLogin.setOnClickListener(this);
        btnLogin_google.setOnClickListener(this);
        tvCadastrar.setOnClickListener(this);

    }

    private void initViews(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin_google = (Button) findViewById(R.id.btnLogin_google);
        tvCadastrar = (TextView) findViewById(R.id.tv_cadastrar);
    }

    private void signIn(){
        Intent loginIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(loginIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount contaUsuario = result.getSignInAccount();
                autenticaComFirebase(contaUsuario);
            }
        }

    }

    private void autenticaComFirebase(GoogleSignInAccount contaUsuario){
        Log.d(TAG, "autenticaComFirebase: " + contaUsuario.getId());

        AuthCredential credencial = GoogleAuthProvider.getCredential(contaUsuario.getIdToken(), null);
        firebaseAuth.signInWithCredential(credencial)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"Login completo: "+ task.isSuccessful());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        FirebaseUser user = task.getResult().getUser();
                        Toast.makeText(LoginActivity.this,user.getDisplayName(),Toast.LENGTH_SHORT).show();

                        if(!task.isSuccessful()){
                            Log.d(TAG,"Login com credencial",task.getException());
                            Toast.makeText(LoginActivity.this,"Falha na autenticação.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:

                break;
            case R.id.btnLogin_google:
                signIn();
                break;
            case R.id.tv_cadastrar:
                startActivity(new Intent(this,CadastrarActivity.class));
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
