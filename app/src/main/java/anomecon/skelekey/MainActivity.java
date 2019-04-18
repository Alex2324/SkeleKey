package anomecon.skelekey;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView accountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start AddRecord.class
                Intent addIntent = new Intent(MainActivity.this,
                        AddRecord.class);
                startActivity(addIntent);
            }
        });
    }

    /**
     * set screen text
     */
    public void setTextView() {
        accountName = findViewById(R.id.account_name);
        accountName.setText("Set Account Name");
    }
    public String getTextView(){
        accountName = findViewById(R.id.account_name);
        String name = accountName.getText().toString();
        return name;
    }
}
