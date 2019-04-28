package anomecon.skelekey;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import helperClasses.InputValidation;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity register = Register.this;

    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editConfirmPassword;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;
    private TextInputLayout inputLayoutConfirmPassword;

    private Button button;
    private TextView loginText;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_register:
                 postData();
                break;

            case R.id.loginLink:
                finish();
                break;
        }
    }

    private void initViews(){
        inputLayoutName = findViewById(R.id.name_InputLayout);
        inputLayoutEmail = findViewById(R.id.emailInputLayout);
        inputLayoutPassword = findViewById(R.id.password_InputLayout);
        inputLayoutConfirmPassword = findViewById(R.id.confirm_password_InputLayout);

        editName = findViewById(R.id.name_register);
        editEmail = findViewById(R.id.email_register);
        editPassword = findViewById(R.id.password_register);
        editConfirmPassword = findViewById(R.id.confirm_password_register);

        button = findViewById(R.id.button_register);
    }

    private void initListeners(){
        button.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(register);
        databaseHelper = new DatabaseHelper(register);
        user = new User();
    }

    private void postData(){
        if (!inputValidation.isTextFieldFilled(editName,
                                                inputLayoutName,
                                                getString(R.string.error_message_name))) return;
        if (!inputValidation.isTextFieldFilled(editEmail,
                                                inputLayoutEmail,
                                                getString(R.string.error_message_email))) return;
        if (!inputValidation.isTextFieldEmail(editEmail,
                                                inputLayoutEmail,
                                                getString(R.string.error_message_email))) return;
        if (!inputValidation.isTextFieldFilled(editPassword,
                                                inputLayoutPassword,
                                                getString(R.string.error_message_password))) return;
        if (!inputValidation.isEachTextFieldsSame(editPassword, editConfirmPassword,
                inputLayoutConfirmPassword, getString(R.string.error_password_match))) return;

        if (!databaseHelper.checkUser(editName.getText().toString().trim())) {

            user.setName(editName.getText().toString().trim());
            user.setEmail(editEmail.getText().toString().trim());
            user.setPassword(editPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(findViewById(R.id.constraintLayout), getString(R.string.success_message),
                                                                        Snackbar.LENGTH_LONG).show();
            setToEmpty();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(findViewById(R.id.constraintLayout), getString(R.string.error_password_match),
                                                                            Snackbar.LENGTH_LONG).show();
        }
    }

    private void setToEmpty(){
        editName.setText(null);
        editEmail.setText(null);
        editPassword.setText(null);
        editConfirmPassword.setText(null);
    }
}