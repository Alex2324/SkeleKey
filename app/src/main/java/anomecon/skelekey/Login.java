package anomecon.skelekey;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import helperClasses.InputValidation;
import anomecon.skelekey.R.id;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity login = Login.this;
    private ConstraintLayout constraintLayout;

    private EditText email;
    private EditText password;

    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;

    private Button button;
    private TextView signUp;

    private DatabaseHelper dbHelper;
    private InputValidation inputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * Initialize views
     */
    private void initViews() {
        constraintLayout = findViewById(id.constraintLayout);
        inputLayoutEmail = findViewById(id.emailInputLayout);
        email = findViewById(id.email_login);
        inputLayoutPassword = findViewById(id.passwordInputLayout);
        password = findViewById(id.password_login);
        button = findViewById(id.button_login);
        signUp = findViewById(id.signUp_text);
    }

    /**
     * Initialize listeners
     */
    private void initListeners(){
        button.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    /**
     * Initialize objects
     */
    private void initObjects(){
        dbHelper = new DatabaseHelper(login);
        inputValidator = new InputValidation(login);
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case id.button_login:
                verify();
                break;
            case id.signUp_text:
                //go to register
                Intent registerActivity = new Intent(getApplicationContext(), Register.class);
                        startActivity(registerActivity);
        }
    }

    /**
     * Let's validate the input fields using SQLite
     */
    private void verify(){
        if(!inputValidator.isTextFieldFilled(email,
                                            inputLayoutEmail,
                                            getString(R.string.error_message_email))){return;}
        if(!inputValidator.isTextFieldEmail(email,
                                            inputLayoutEmail,
                                            getString(R.string.error_message_email))){return;}
        if(!inputValidator.isTextFieldFilled(password,
                                            inputLayoutPassword,
                                            getString(R.string.error_message_email))){return;}
        if (dbHelper.checkUser(email.getText().toString().trim(),
                                password.getText().toString().trim())){
            Intent main = new Intent(login, MainActivity.class);
            main.putExtra("Email ", email.getText().toString().trim());
            emptyTextField();
            startActivity(main);
        }
        //display Snackbar for invalid email or password
        else Snackbar.make(constraintLayout,
                            getString(R.string.error_valid_email_password),
                            Snackbar.LENGTH_LONG).show();
    }
    /**
     * Empty all input edit text
     */
    private void emptyTextField(){
        email.setText(null);
        password.setText(null);
    }
}
