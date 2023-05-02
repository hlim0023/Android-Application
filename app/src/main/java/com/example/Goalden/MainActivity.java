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

import java.time.LocalTime;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button signButton;
    EditText username;
    EditText password;

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
        mCondition = myRef.child("login/users");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                    Toast.makeText(getApplicationContext(), "Hi, "+ username.getText().toString() + ". Welcome back.", Toast.LENGTH_LONG).show();
//                    //Toast is to display the msg like pop up window
//                    binding = HomeActivityBinding.inflate(getLayoutInflater());
//                    setContentView(binding.getRoot());
//                    replaceFragment(new HomeFragment());
//
//                    binding.bottomNavigationView.setOnItemSelectedListener(item ->{
//                        switch (item.getItemId()){
//                            case R.id.calendar_item:
//                                // TODO: FIX this to fragment
//                                Intent intent = new Intent(MainActivity.this, CalendarView.class);
//                                startActivity(intent);
//                                break;
//                        }
//
//                        return false;
//
//                    });
////                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
////                    startActivity(intent);
//                }
                boolean flag = false;
                if (UserInfo.users.size() == 0){
                    Toast.makeText(getApplicationContext(), "No Recorded Users !", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < UserInfo.users.size(); i++) {
                        Log.d("LOGIN", UserInfo.users.toString());
                        UserInfo user = UserInfo.users.get(i);
                        String users_username = user.getUserName();
                        String users_password = user.getUserPassword();

                        if (username.getText().toString().equals(users_username) && password.getText().toString().equals(users_password)) {
                            UserInfo.loggedUser = user;
                            flag = true;
                            Log.d("USERNAME_PASSWORD", users_username + " " + users_password);
                            Toast.makeText(getApplicationContext(), "Hi, " + username.getText().toString() + " Welcome back.", Toast.LENGTH_LONG).show();
                            //Toast is to display the msg like pop up window
                            //Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                            //Changed Intent to calendar
                            binding = HomeActivityBinding.inflate(getLayoutInflater());
                            setContentView(binding.getRoot());
                            replaceFragment(new HomeFragment());

                            binding.bottomNavigationView.setOnItemSelectedListener(item ->{
                                switch (item.getItemId()){
                                    case R.id.calendar_item:
                                        // TODO: FIX this to fragment
                                        Intent intent = new Intent(MainActivity.this, CalendarView.class);
                                        startActivity(intent);
                                        break;
                                }

                                return false;
                            });
                        }
                    }
                    if(!flag)
                        Toast.makeText(getApplicationContext(), "Wrong Password or user name, please try again !", Toast.LENGTH_SHORT).show();
                }



                //TODO: reading from firebase and

            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = mCondition.push().getKey();
                UserInfo user = new UserInfo(userId, username.getText().toString(), password.getText().toString());
                mCondition.child(userId).setValue(user);
                String activityKey = mCondition.child(userId).child("activities").push().getKey();
//                mCondition.child(userId).child("activities").setValue("test");
//                mCondition.child(userId).child("activities").setValue(Activity)
//                mCondition.removeValue();
            }
            });



    }


    @Override
    protected void onStart() {
        super.onStart();
        mCondition.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserInfo user = new UserInfo(snapshot.getKey(), snapshot.child("userName").getValue(String.class), snapshot.child("userPassword").getValue(String.class));
                UserInfo.users.add(user);

                for (DataSnapshot dataSnapshot: snapshot.child("activities").getChildren()){
                    String activityName = dataSnapshot.child("activityName").getValue(String.class);
                    String activityTime = dataSnapshot.child("activityTime").getValue(String.class);
                    String activityDate = dataSnapshot.child("activityDate").getValue(String.class);
                    String activityCategory = dataSnapshot.child("activityCategory").getValue(String.class);

                    Activity activity = new Activity(
                            activityName,
                            LocalTime.parse(activityTime),
                            CalendarUtils.toDate(activityDate),
                            ActivityType.valueOf(activityCategory)
                    );

                    user.addActivity(activity);

                }
//              Log.d("FIREBASE_USER", UserInfo.users.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for(UserInfo user: UserInfo.users){
                    if (user.getUserId().equals(snapshot.getKey())){
                        String username = snapshot.child("userName").getValue(String.class);
                        String password = snapshot.child("userPassword").getValue(String.class);
                        ArrayList<Activity> activities = new ArrayList<>();
                        for (DataSnapshot dataSnapshot: snapshot.child("activities").getChildren()){
                            String activityName = dataSnapshot.child("activityName").getValue(String.class);
                            String activityTime = dataSnapshot.child("activityTime").getValue(String.class);
                            String activityDate = dataSnapshot.child("activityDate").getValue(String.class);
                            String activityCategory = dataSnapshot.child("activityCategory").getValue(String.class);

                            Activity activity = new Activity(
                                    activityName,
                                    LocalTime.parse(activityTime),
                                    CalendarUtils.toDate(activityDate),
                                    ActivityType.valueOf(activityCategory)
                            );

                            activities.add(activity);

                        }

                        user.update(username, password, activities);
                        //user.update();
                        break;
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                for(UserInfo user: UserInfo.users){
                    if (user.getUserId().equals(snapshot.getKey())){
                        UserInfo.users.remove(user);
                        break;
                    }
                }
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
