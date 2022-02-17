package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class profile extends AppCompatActivity {

        EditText txtnamep, txtemailp, txtphonep, txtpasswordp;
        ImageView imageView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            imageView = findViewById(R.id.imagep);
            txtnamep = findViewById(R.id.namep);
            txtphonep = findViewById(R.id.phonep);
            txtemailp = findViewById(R.id.txtuserp);
            txtpasswordp = findViewById(R.id.txtpassp);


            Database data = new Database();
            data.ConnectDB();
            ResultSet rs = data.RunSearch("select * from Family members where member_id='"+LoginActivity.member_id+"','',''");
            try {
                if (rs.next()) {
                    txtnamep.setText(rs.getString(2));
                    txtemailp.setText(rs.getString(3));
                    txtphonep.setText(rs.getString(4));
                    txtpasswordp.setText(rs.getString(5));
                   // picassoClient.downloadImage(this,rs.getString(8),imageView);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            txtemailp.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    String regExp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
                    if (txtemailp.getText().toString().matches(regExp)) {
                        ;
                    } else {
                        txtemailp.setError("Invaild Email Formate (example@domain)");
                    }

                }
            });

            txtphonep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b == true) {
                        ;
                    } else {
                        if (txtphonep.getText().toString().length() != 11) ;
                        Toast.makeText(profile.this, "Must be 11 digit", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public void updateprofile(View view) {
            if (txtemailp.getText().toString().isEmpty()) {
                txtemailp.setError("Please enter Email");
                txtemailp.requestFocus();
            } else {
                if (txtphonep.getText().toString().isEmpty()) {
                    txtphonep.setError("Please enter Phone");
                    txtphonep.requestFocus();
                } else {
                    if (txtpasswordp.getText().toString().isEmpty()) {
                        txtpasswordp.setError("Please enter password");
                        txtpasswordp.requestFocus();
                    } else {
                        Database db = new Database();
                        Connection conn = db.ConnectDB();
                        if (conn == null) {
                            Toast.makeText(this, "Check internet access", Toast.LENGTH_SHORT).show();
                        } else {
                            String don = null;
                            don = db.RUNDML("update   client set client_name =N'" + txtnamep.getText() + "',email='" + txtemailp.getText() + "',phone='" + txtphonep.getText() + "',password='" + txtpasswordp.getText() + "'where txtnamep'"+LoginActivity.member_id+"')");
                            if (don.equals("Ok")) {
                                AlertDialog.Builder ad = new AlertDialog.Builder(profile.this);
                                ad.setTitle("Registeration...");
                                ad.setMessage("Your account has created :)");
                                ad.setIcon(R.drawable.help);
                                ad.setNegativeButton("Thanks", null);
                                ad.create().show();
                            } else if (don.contains("uqemail")) {
                                Toast.makeText(this, "This email is used", Toast.LENGTH_LONG).show();
                            } else if (don.contains("uqphone")) {
                                Toast.makeText(this, "This phone is used", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "Error is " + don, Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                }
            }
        }
    }

