package com.example.miniproject_orifrommer;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private TextView goSignup;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnPassVisible;
    private Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initUI();

    }

    /**
     Function will initialize all objects on screen (UI)
     */
    private void initUI()
    {
        //Initialize objects
        goSignup = findViewById(R.id.linkSignup);
        etUsername = findViewById(R.id.inputUsername);
        etPassword = findViewById(R.id.inputPassword);
        btnPassVisible = findViewById(R.id.btnShowPass);
        btnLogin = findViewById(R.id.btnLogin);

        //Set Listeners and events
        btnPassVisible.setOnClickListener(v -> {
            if (etPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                // Hide password
                btnPassVisible.setText(Utils.TEXT_SHOW);
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                // Show password
                btnPassVisible.setText(Utils.TEXT_HIDE);
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }

        });

        btnLogin.setOnClickListener(v -> {
            final String[] inputs = { Utils.getEditTextValue(etUsername).trim(), Utils.getEditTextValue(etPassword) };
            if(Utils.foundEmptyField(inputs))
            {
                Toast.makeText(this, Utils.ERROR_EMPTY, Toast.LENGTH_SHORT).show();
            }
            else
            {

            }
        });



        goSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

    }


}