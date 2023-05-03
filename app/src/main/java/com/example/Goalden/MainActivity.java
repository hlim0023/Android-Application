package com.example.Goalden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.Goalden.databinding.HomeActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button signButton;
    EditText username;
    EditText password;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> codes = new ArrayList<>();

    DatabaseReference myRef,mCondition;

    HomeActivityBinding binding;

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_layout_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginButton = findViewById(R.id.button);
        signButton = findViewById(R.id.button2);
        username = findViewById(R.id.textInputEditText);
        password = findViewById(R.id.editTextTextPassword);

        myRef = FirebaseDatabase.getInstance().getReference();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Hi, "+ username.getText().toString() + ". Welcome back.", Toast.LENGTH_LONG).show();
                    //Toast is to display the msg like pop up window
                    binding = HomeActivityBinding.inflate(getLayoutInflater());
                    setContentView(binding.getRoot());
                    replaceFragment(new HomeFragment());

                    binding.bottomNavigationView.setOnItemSelectedListener(item ->{
                        switch (item.getItemId()){
                            case R.id.calendar_item:
                                // TODO: FIX this to fragment
                                Intent intent = new Intent(MainActivity.this, CalendarView.class);
                                startActivity(intent);
//                                replaceFragment(new CalendarFragment());
                                break;
                        }

                        return true;

                    });
//                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
//                    startActivity(intent);
                }
                else{
                    for (int i = 0; i < names.size(); i++){
                        if (username.getText().toString().equals(names.get(i))&& password.getText().toString().equals(codes.get(i))){
                            Toast.makeText(getApplicationContext(), "Hi, "+ username.getText().toString() + "Welcome back.", Toast.LENGTH_LONG).show();
                            //Toast is to display the msg like pop up window
                            //Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                            //Changed Intent to calendar
                            Intent intent = new Intent(MainActivity.this, CalendarView.class);
                            startActivity(intent);
                            break;
                        }

                        if (i == names.size()-1){
                            Toast.makeText(getApplicationContext(), "Wrong Username or Password, please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (names.size() == 0){
                        Toast.makeText(getApplicationContext(), "Wrong Username or Password, please try again!", Toast.LENGTH_SHORT).show();
                    }
                }

                //TODO: reading from firebase and

            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo user = new UserInfo(username.getText().toString(), password.getText().toString());
                mCondition.push().setValue(user);
            }
            });



    }


    @Override
    protected void onStart() {
        super.onStart();

        mCondition = myRef.child("login/users");
        mCondition.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
