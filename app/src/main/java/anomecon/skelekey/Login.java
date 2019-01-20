package anomecon.skelekey;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button button;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Locate the button in activity_main.xml
        button = findViewById(R.id.button_login);
        register = findViewById(R.id.signUp_text);

        // Listen for button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(Login.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });
        // Listen for text clicks
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(Login.this,
                        Register.class);
                startActivity(myIntent);
            }
        });
    }
}
