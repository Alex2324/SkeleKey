package anomecon.skelekey;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import anomecon.skelekey.adapters.ContentList;
import helperClasses.InputValidation;
import anomecon.skelekey.R.id;

public class AddRecord extends AppCompatActivity implements View.OnClickListener{

    //region Initialize AddRecord Variables
    private final AppCompatActivity addRecord = AddRecord.this;


    private EditText name;
    private EditText userName;
    private EditText password;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutUserName;
    private TextInputLayout inputLayoutPassword;

    private Button button;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private Content content;
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
        inputLayoutName = findViewById(R.id.nameInputLayout);
        inputLayoutUserName = findViewById(id.userNameInputLayout);
        inputLayoutPassword = findViewById(R.id.passwordInputLayout);


        name = findViewById(id.add_name);
        userName = findViewById(id.add_user_name);
        password = findViewById(id.add_password);

        button = findViewById(id.button_add);
    }

    private void initListeners(){
        button.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(addRecord);
        databaseHelper = new DatabaseHelper(addRecord);
        content = new Content();
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        postData();
        Intent content = new Intent(getApplicationContext(), ContentList.class);
        startActivity(content);
    }

    private void postData(){
        if (!inputValidation.isTextFieldFilled(name,
                inputLayoutName,
                getString(R.string.error_message_name))) return;
        if (!inputValidation.isTextFieldFilled(userName,
                inputLayoutUserName,
                getString(R.string.error_message_name))) return;
        if (!inputValidation.isTextFieldFilled(password,
                inputLayoutPassword,
                getString(R.string.error_message_password))) return;

        if (!databaseHelper.checkUser(name.getText().toString().trim())) {

            content.setContentName(name.getText().toString().trim());
            content.setContentUserName(userName.getText().toString().trim());
            content.setContentPassword(password.getText().toString().trim());

            databaseHelper.addContent(content);
            //Also want to display this in the ContentList as it is an account the User owns
            //databaseHelper.addContent(user);

            // Snack Bar to show success message that record saved successfully
            //Snackbar.make(findViewById(android.R.id.content), getString(R.string.success_message),
                    //Snackbar.LENGTH_LONG).show();
            //setToEmpty();
        }
    }

    private void setToEmpty(){
        name.setText(null);
        userName.setText(null);
        password.setText(null);
    }
}