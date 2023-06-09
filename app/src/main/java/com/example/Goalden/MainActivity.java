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
import android.view.Gravity;
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
public class MainActivity extends BaseActivity {

    Button loginButton;
    Button signButton;
    EditText username;
    EditText password;

    DatabaseReference myRef,mCondition;

    HomeActivityBinding binding;

//    public void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.home_layout_frame, fragment);
//        fragmentTransaction.commit();
//    }

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

                boolean flag = false;
                if (UserInfo.users.size() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "No existing account found.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 100);
                    toast.show();
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
                            Toast toast = Toast.makeText(getApplicationContext(), "Hi " + username.getText().toString() + ", welcome back.", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM, 0, 100);
                            toast.show();
                            //Toast is to display the msg like pop up window
                            //Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                            //Changed Intent to calendar
                            binding = HomeActivityBinding.inflate(getLayoutInflater());
                            setContentView(binding.getRoot());
                            setupBottomNavigationView();
                            replaceFragment(new HomeFragment());
                            break;
//                            binding.bottomNavigationView.setOnItemSelectedListener(item ->{
//                                switch (item.getItemId()) {
//                                    case R.id.home_item:
//                                        replaceFragment(new HomeFragment());
//                                        break;
//                                    case R.id.calendar_item:
//                                        Intent intent = new Intent(MainActivity.this, CalendarView.class);
//                                        startActivity(intent);
//                                        break;
//                                    case R.id.tasks_item:
//                                        replaceFragment(new TasksFragment());
//                                        break;
//                                    case R.id.profile_item:
//                                        replaceFragment(new ProfileFragment());
//                                        break;
//                                }
//                                return true;
//                            });
                        }
                    }
                    if(!flag) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Wrong username or password, please try again.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM, 0, 100);
                        toast.show();
                    }
                }



                //TODO: reading from firebase and

            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserInfo.findUserInfoByName(username.getText().toString()) == null) {
                    String userId = mCondition.push().getKey();
                    UserInfo user = new UserInfo(userId, username.getText().toString(), password.getText().toString());
                    mCondition.child(userId).setValue(user);
                    return;
//                mCondition.child(userId).child("activities").setValue("test");
//                mCondition.child(userId).child("activities").setValue(Activity)
//                mCondition.removeValue();
                }
                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
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
                    String activityId = dataSnapshot.getKey();
                    String activityName = dataSnapshot.child("activityName").getValue(String.class);
                    String activityTime = dataSnapshot.child("activityTime").getValue(String.class);
                    String activityDate = dataSnapshot.child("activityDate").getValue(String.class);
                    String activityCategory = dataSnapshot.child("activityCategory").getValue(String.class);
                    boolean activityComplete = Boolean.TRUE.equals(dataSnapshot.child("activityComplete").getValue(Boolean.class));

                    Activity activity = new Activity(
                            activityId,
                            activityName,
                            LocalTime.parse(activityTime),
                            CalendarUtils.toDate(activityDate),
                            ActivityType.valueOf(activityCategory),
                            activityComplete
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
                            String activityId = dataSnapshot.getKey();
                            String activityName = dataSnapshot.child("activityName").getValue(String.class);
                            String activityTime = dataSnapshot.child("activityTime").getValue(String.class);
                            String activityDate = dataSnapshot.child("activityDate").getValue(String.class);
                            String activityCategory = dataSnapshot.child("activityCategory").getValue(String.class);
                            boolean activityComplete = Boolean.TRUE.equals(dataSnapshot.child("activityComplete").getValue(Boolean.class));

                            Activity activity = new Activity(
                                    activityId,
                                    activityName,
                                    LocalTime.parse(activityTime),
                                    CalendarUtils.toDate(activityDate),
                                    ActivityType.valueOf(activityCategory),
                                    activityComplete
                            );

                            activities.add(activity);

                        }

                        user.update(username, password, activities);
                        //user.update();
                        break;
                    }
                }
            }
            //            @Override
            public void onBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    replaceFragment(new HomeFragment());
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
