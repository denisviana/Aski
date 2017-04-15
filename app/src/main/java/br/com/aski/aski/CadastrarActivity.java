package br.com.aski.aski;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by denisvcosta on 13/03/2017.
 */

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCadastrar;
    private TextView tvFazerLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initViews();

        btnCadastrar.setOnClickListener(this);
        tvFazerLogin.setOnClickListener(this);


    }

    private void initViews(){
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        tvFazerLogin = (TextView) findViewById(R.id.tv_fazer_login);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_cadastrar:
                break;
            case R.id.tv_fazer_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
