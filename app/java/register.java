package com.example.regis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

public class register extends AppCompatActivity {//implements View.OnClickListener{

    private EditText regName, regclass, regid, regpass, regmail;
    private Button regbtn, reglog;
    FirebaseDatabase firebase;
    DatabaseReference reference;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toast.makeText(register.this,"success",Toast.LENGTH_SHORT).show();
        regName = findViewById(R.id.editName);
        regclass = findViewById(R.id.classroom);
        regid = findViewById(R.id.clgid);
        regpass = findViewById(R.id.editPassword);
        regmail = findViewById(R.id.editEmail);
        regbtn = findViewById(R.id.buttonRegister);
        reglog = findViewById(R.id.buttonLogin);

        //firebase=FirebaseDatabase.getInstance();
        //reference=firebase.getReference("users");
        reference=firebase.getInstance().getReference("users");
        userHelper=new UserHelper();
       /* regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=regName.getText().toString();
                String classs=regclass.getText().toString();
                String id=regid.getText().toString();
                String mail=regmail.getText().toString();
                String pass=regpass.getText().toString();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(classs)||TextUtils.isEmpty(id)||TextUtils.isEmpty(mail)||TextUtils.isEmpty(pass)){
                    Toast.makeText(register.this,"add data",Toast.LENGTH_SHORT).show();
                }
                else {
                    addUser(name,mail,id,classs,pass);
                }
            }
        });
    }

    private void addUser(String name, String mail, String id, String classs, String pass) {
        userHelper.setName(name);
        userHelper.setClasss(classs);
        userHelper.setId(id);
        userHelper.setPassword(pass);
        userHelper.setEmail(mail);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.setValue(userHelper);
                Toast.makeText(register.this,"data added",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("hikj","onFailure" +error.toString());
                Toast.makeText(register.this,"data entry failed"+ error,Toast.LENGTH_SHORT).show();

            }
        });
    }
}*/
        regbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          HashMap<String, Object> map = new HashMap<>();
                                          map.put("name", regName.getText().toString());
                                          map.put("class", regclass.getText().toString());
                                          map.put("id", regid.getText().toString());
                                          map.put("mail", regmail.getText().toString());
                                          map.put("pass",regpass.getText().toString());

                                          FirebaseDatabase.getInstance().getReference().child("users").push()
                                                  .setValue(map)
                                                  .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<Void> task) {
                                                          Log.i("hikj", "onComplete");
                                                      }
                                                  }).addOnFailureListener(new OnFailureListener() {
                                              @Override
                                              public void onFailure(@NonNull Exception e) {
                                                  Log.i("hikj", "onFailure" + e.toString());
                                                  Toast.makeText(register.this,"error" +e,Toast.LENGTH_SHORT).show();
                                              }
                                          }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                              @Override
                                              public void onSuccess(Void aVoid) {
                                                  Log.i("hijk", "OnSuccess");
                                              }
                                          });
                                      }
                                  });
    }
}

                /*rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("users");

                String name=regName.getEditableText().toString();
                String classs=regclass.getEditableText().toString();
                String id=regid.getEditableText().toString();
                String emaill=regmail.getEditableText().toString();
                String pass=regpass.getEditableText().toString();


                UserHelper userHelper=new UserHelper(name,emaill,id,classs,pass);

                reference.child(id).setValue(userHelper);*/
            /*}
        });


    }
}*/