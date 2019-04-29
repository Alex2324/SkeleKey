package anomecon.skelekey;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helperClasses.InputValidation;
import anomecon.skelekey.R.id;

public class AddRecord extends AppCompatActivity implements View.OnClickListener{

    //region Initialize AddRecord Variables
    private final AppCompatActivity addRecord = AddRecord.this;
    private ConstraintLayout constraintLayout;

    private EditText name;
    private EditText userName;
    private EditText password;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutUserName;
    private TextInputLayout inputLayoutPassword;

    private Button button;

    private DatabaseHelper dbHelper;
    private InputValidation inputValidator;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        constraintLayout = findViewById(id.constraintLayout);
        inputLayoutName = findViewById(id.nameInputLayout);
        inputLayoutUserName = findViewById(id.userNameInputLayout);
        inputLayoutPassword = findViewById(id.passwordInputLayout);
        name = findViewById(id.add_name);
        userName = findViewById(id.add_user_name);
        password = findViewById(id.add_password);

        button = findViewById(id.done_fab);
    }

    private void initListeners(){
        button.setOnClickListener(this);
    }

    private void initObjects(){
        dbHelper = new DatabaseHelper(addRecord);
        inputValidator = new InputValidation(addRecord);
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view){
        verify();
    }

    private void verify(){
        if(!inputValidator.isTextFieldFilled(name,
                                            inputLayoutName,
                                            getString(R.string.error_message_name)))return;
        if(!inputValidator.isTextFieldFilled(userName,
                                            inputLayoutUserName,
                                            getString(R.string.error_message_name)))return;
        if(!inputValidator.isTextFieldFilled(password,
                                            inputLayoutPassword,
                                            getString(R.string.error_message_password)))return;
        if(dbHelper.checkContent(name.getText().toString().trim(),
                                userName.getText().toString().trim(),
                                password.getText().toString().trim())){
            Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();
            Intent main = new Intent(addRecord, MainActivity.class);
            main.putExtra("Name ", name.getText().toString().trim());
            setToEmpty();
            startActivity(main);
        }
    }

    private void setToEmpty(){
        name.setText(null);
        userName.setText(null);
        password.setText(null);
    }
}