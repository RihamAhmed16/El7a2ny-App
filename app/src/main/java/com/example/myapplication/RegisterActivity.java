package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

public class RegisterActivity extends AppCompatActivity {

    ImageView image;
    EditText txt,txtWelcome,Email, phone, text_password, password, id, Address;
    Button GoTo_login, forget,signup;
    private WifiManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        image = findViewById(R.id.photo);
        txt = findViewById(R.id.fullname);
        Address = findViewById(R.id.Address);
        signup = findViewById(R.id.new_user);
        id = findViewById(R.id.id_login);
        txtWelcome= findViewById(R.id.txtpassword);
        GoTo_login = findViewById(R.id.Go_login);
        Email=findViewById(R.id.username_signup);
        password=findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        wm=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String regExp= "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
                if(Email.getText().toString().matches(regExp))
                {
                    ;
                }
                else {
                    Email.setError("Invaild Email Formate (example@domain)");
                }

            }
        });

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b==true)
                {
                    ;
                }
                else
                {
                    if(phone.getText().toString().length()!=11) {
                        Toast.makeText(RegisterActivity.this, "Must be 11 digit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    public void Register(View view) {


        if (Email.getText().toString().isEmpty()) {
            Email.setError("Please enter Email");
            Email.requestFocus();
        } else {
            if (phone.getText().toString().isEmpty()) {
                phone.setError("Please enter Phone");
                phone.requestFocus();
            } else {
                if (Email.getText().toString().isEmpty()) {
                    Email.setError("Please enter password");
                    Email.requestFocus();
                } else {
                    Database db=new Database();
                    Connection conn=db.ConnectDB();
                    if(conn==null)
                    {
                        if(wm.isWifiEnabled()){
                            Toast.makeText(this, "Check internet access", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            AlertDialog.Builder ad = new AlertDialog.Builder(RegisterActivity.this);
                            ad.setMessage("Do you want to open wifi?");
                            ad.setPositiveButton("Open wifi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    wm.setWifiEnabled(true);
                                }
                            });
                            ad.setNegativeButton("No thanks",null);
                            ad.create().show();
                        }
                    }
                    else {
                        String don=null;
                        don=db.RUNDML("insert into Family members values (N'"+txt.getText()+"','"+Email.getText()+"','"+ text_password.getText()+"','"+phone.getText()+"','"+Address.getText()+"','"+id.getText()+"','"+MapsActivity.la+"','"+MapsActivity.lo+"')");

                        if(don.equals("Ok"))
                        {
                            AlertDialog.Builder ad=new AlertDialog.Builder(RegisterActivity.this);
                            ad.setTitle("Registeration...");
                            ad.setMessage("Your account has created :)");
                            ad.setIcon(R.drawable.help);
                            ad.setPositiveButton("Go to Login", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(RegisterActivity.this,choiceActivity.class));
                                }
                            });
                            ad.setNegativeButton("Thanks",null);
                            ad.create().show();
                        }
                        else if(don.contains("uqemail"))
                        {
                            Toast.makeText(this, "This email is used", Toast.LENGTH_LONG).show();
                        }
                        else if(don.contains("uqphone"))
                        {
                            Toast.makeText(this, "This phone is used", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this,"Error is "+don,Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        }
    }

    public void Login(View view) {
        startActivity(new Intent(RegisterActivity.this,choiceActivity.class));
    }
}
