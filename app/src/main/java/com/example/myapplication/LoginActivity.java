package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    public static String member_id;
    ImageView image;
    TextView txt, forgit;
    Button Go_login, Register;
    EditText text_username, text_password;
    private WifiManager wm;
    CheckBox chk;
    public static String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // forgit=findViewById(R.id.forget_pass);


        image = findViewById(R.id.photo);
        txt = findViewById(R.id.txt_login);
        Register = findViewById(R.id.new_user);
        text_username = findViewById(R.id.txtuser);
        text_password = findViewById(R.id.txtpassp);
        Go_login = findViewById(R.id.Go_login);
        chk = findViewById(R.id.remember);


        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        SharedPreferences sh = getSharedPreferences("Shlogin", MODE_PRIVATE);
        id = sh.getString("id", null);
        String name = sh.getString("name", null);
        if ((id != null) || (name != null)) {
            startActivity(new Intent(LoginActivity.this, congrattulationsActivity.class));
        }

    }
    public void Register(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

 //   @SuppressLint("ApplySharedPref")



    public void Go(View view) {
       // startActivity(new Intent(LoginActivity.this,congrattulationsActivity.class));

        if (text_username.getText().toString().isEmpty()) {
            text_username.setError("Enter Phone / Email");
            text_username.requestFocus();
        } else {
            if (text_password.getText().toString().isEmpty()) {
                text_password.setError("Please enter Password");
                text_password.requestFocus();
            } else {
                Database db = new Database();
                Connection conn = db.ConnectDB();
                if (conn == null) {
                    // Show alert Buttons ( Open Wifi , Open Mobile Data )
                    if (wm.isWifiEnabled()) {
                        Toast.makeText(this, "Internet connection missing", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                        ad.setMessage("Do you want to open wifi?");
                        ad.setPositiveButton("Open wifi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                wm.setWifiEnabled(true);
                            }
                        });
                        ad.setNegativeButton("No thanks", null);
                        ad.create().show();
                    }

                } else {
                    ResultSet rs = db.RunSearch("select * from Family_members where (FamilyUserName='"+text_username.getText()+"') and password_member='"+text_password.getText()+"'");
                    try {
                        if (rs.next()) {
                            if (chk.isChecked()) {
                                getSharedPreferences("Shlogin", MODE_PRIVATE)
                                        .edit()
                                        .putString("member_id", rs.getString(1))
                                        .putString("fname_member", rs.getString(2))
                                        .putString("midName_member", rs.getString(3))
                                        .putString("lName_member", rs.getString(4))
                                        .putString("password_member", rs.getString(5))
                                        .putString("female_member", rs.getString(6))
                                        .putString("male_member", rs.getString(7))
                                        .putString("latitude_member", rs.getString(8))

                                        .putString("longitude_member", rs.getString(9))
                                        .putString("country_member", rs.getString(10))

                                        .putString("Lno", rs.getString(11))
                                        .putString("Rno", rs.getString(12))
                                        .putString("Admin_Id", rs.getString(13))
                                        .putString("FamilyUserName", rs.getString(14))


                                        .commit();
                            }
                            id = rs.getString(1);
                            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                        } else {
                            AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                            ad.setTitle("Login...");
                            ad.setMessage("Invaild user / password :)");
                            ad.setIcon(R.drawable.help);
                            ad.setPositiveButton("Go to Register", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                                }
                            });
                            ad.setNegativeButton("Try again", null);
                            ad.create().show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        if (text_username.getText().toString().isEmpty()) {
            text_username.setError("Enter Phone / Email");
            text_username.requestFocus();
        } else {
            if (text_password.getText().toString().isEmpty()) {
                text_password.setError("Please enter Password");
                text_password.requestFocus();
            } else {
                Database db = new Database();
                Connection conn = db.ConnectDB();
                if (conn == null) {
                    // Show alert Buttons ( Open Wifi , Open Mobile Data )
                    if (wm.isWifiEnabled()) {
                        Toast.makeText(this, "Internet connection missing", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                        ad.setMessage("Do you want to open wifi?");
                        ad.setPositiveButton("Open wifi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                wm.setWifiEnabled(true);
                            }
                        });
                        ad.setNegativeButton("No thanks", null);
                        ad.create().show();
                    }

                } else {
                    ResultSet rs = db.RunSearch("select * from Family_Head where (FUserName='"+text_username.getText()+"') and password_head='"+text_password.getText()+"'");
                    try {
                        if (rs.next()) {
                            if (chk.isChecked()) {
                                getSharedPreferences("Shlogin", MODE_PRIVATE)
                                        .edit()
                                        .putString("Admin_Id", rs.getString(1))
                                        .putString("FName", rs.getString(2))
                                        .putString("MidName", rs.getString(3))
                                        .putString("Lname", rs.getString(4))
                                        .putString("latitude", rs.getString(5))
                                        .putString("longitude", rs.getString(6))
                                        .putString("password_head", rs.getString(7))
                                        .putString("country", rs.getString(8))
                                        .putString("female", rs.getString(9))
                                        .putString("male", rs.getString(10))
                                        .putString("FamilyUserName", rs.getString(11))


                                        .commit();
                            }
                            id = rs.getString(1);
                            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                        } else {
                            AlertDialog.Builder ad = new AlertDialog.Builder(LoginActivity.this);
                            ad.setTitle("Login...");
                            ad.setMessage("Invaild user / password :)");
                            ad.setIcon(R.drawable.help);
                            ad.setPositiveButton("Go to Register", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                                }
                            });
                            ad.setNegativeButton("Try again", null);
                            ad.create().show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }

    }

}













