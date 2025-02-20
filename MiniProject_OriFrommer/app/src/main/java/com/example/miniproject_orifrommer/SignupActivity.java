package com.example.miniproject_orifrommer;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    private TextView goLogin;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordConfirmation;
    private Button btnPassVisible;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
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
        goLogin = findViewById(R.id.linkLogin);
        etUsername = findViewById(R.id.inputUsername);
        etPassword = findViewById(R.id.inputPassword);
        etPasswordConfirmation = findViewById(R.id.inputPassConfirm);
        btnPassVisible = findViewById(R.id.btnShowPass);
        btnSignup = findViewById(R.id.btnSignup);

        //Set listeners and events
        btnPassVisible.setOnClickListener(v -> {
            if (etPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                // Hide password
                btnPassVisible.setText(Utils.TEXT_SHOW);
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                etPasswordConfirmation.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                // Show password
                btnPassVisible.setText(Utils.TEXT_HIDE);
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                etPasswordConfirmation.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }

        });

        btnSignup.setOnClickListener(v -> {
            final String[] inputs = { Utils.getEditTextValue(etUsername).trim(), Utils.getEditTextValue(etPassword), Utils.getEditTextValue(etPasswordConfirmation) };
            if(Utils.foundEmptyField(inputs))
            {
                Toast.makeText(this, Utils.ERROR_EMPTY, Toast.LENGTH_SHORT).show();
            }
            else
            {

            }
        });


        goLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}