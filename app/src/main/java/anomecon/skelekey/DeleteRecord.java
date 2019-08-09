package anomecon.skelekey;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import anomecon.skelekey.adapters.ContentList;


public class DeleteRecord extends AppCompatActivity implements View.OnClickListener{

    //region Initialize AddRecord Variables
    private EditText name;
    private final AppCompatActivity deleteRecord = DeleteRecord.this;
    private DatabaseHelper databaseHelper;
    private Content content;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initObjects();
        initViews();
        initListeners();
    }
    private void initListeners(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        content.setId(name.getId());
        databaseHelper.deleteContent(content);
        Intent content = new Intent(getApplicationContext(), ContentList.class);
        startActivity(content);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(deleteRecord);
        content = new Content();
    }

    public void initViews(){
        name = findViewById(R.id.delete_name);
        button = findViewById(R.id.button_delete);
    }
}
