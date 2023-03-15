package com.example.Goalden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button signButton;
    EditText username;
    EditText password;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> codes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginButton = findViewById(R.id.button);
        signButton = findViewById(R.id.button2);
        username = findViewById(R.id.textInputEditText);
        password = findViewById(R.id.editTextTextPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Hi, "+ username.getText().toString() + ". Welcome back.", Toast.LENGTH_LONG).show();
                    //Toast is to display the msg like pop up window
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);
                }
                else{
                    for (int i = 0; i < names.size(); i++){
                        if (username.getText().toString().equals(names.get(i))&& password.getText().toString().equals(codes.get(i))){
                            Toast.makeText(getApplicationContext(), "Hi, "+ username.getText().toString() + "Welcome back.", Toast.LENGTH_LONG).show();
                            //Toast is to display the msg like pop up window
                            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                            startActivity(intent);
                            break;
                        }

                        if (i == names.size()-1){
                            Toast.makeText(getApplicationContext(), "Wrong Password or user name, please try again !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (names.size() == 0){
                        Toast.makeText(getApplicationContext(), "Wrong Password or user name, please try again !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}
