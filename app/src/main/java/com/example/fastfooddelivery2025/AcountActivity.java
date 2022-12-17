package com.example.fastfooddelivery2025;

import static com.example.fastfooddelivery2025.InitData.KeyUser;
import static com.example.fastfooddelivery2025.InitData.referenceAccountEmployee;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fastfooddelivery2025.Data.DataSharedPreferences;
import com.example.fastfooddelivery2025.Model.Staff;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcountActivity extends AppCompatActivity {
    private EditText edt_password,edt_confirmPassword,edt_oldPassword;
    private Button btn_change;
    private ImageView img_back_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);

        edt_password = findViewById(R.id.edt_password);
        edt_confirmPassword = findViewById(R.id.edt_confirmPassword);
        edt_oldPassword = findViewById(R.id.edt_oldPassword);
        btn_change = findViewById(R.id.btn_change);
        img_back_account = findViewById(R.id.img_back_account);

        img_back_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edt_password.getText().toString().trim();
                String confPass = edt_confirmPassword.getText().toString().trim();
                String oldPass = edt_oldPassword.getText().toString().trim();

                Staff staff = DataSharedPreferences.getUser(AcountActivity.this,KeyUser);

                if(!staff.getPassword().equals(oldPass)){
                    Toast.makeText(AcountActivity.this,"Mật khẩu cũ không đúng hoặc mật khẩu mới chưa khớp!",Toast.LENGTH_LONG).show();
                    return;
                }

                if(pass.length() < 6){
                    edt_password.setError("Nhập trên 6 kí tự");
                    return;
                }
                if(confPass.length() < 6){
                    edt_confirmPassword.setError("Nhập trên 6 kí tự");
                    return;
                }
                if(!pass.equals(confPass)){
                    Toast.makeText(AcountActivity.this,"Mật khẩu cũ không đúng hoặc mật khẩu mới chưa khớp!",Toast.LENGTH_LONG).show();
                }else{
                    staff.setPassword(pass);
                    referenceAccountEmployee.child(staff.getId_staff()).setValue(staff);
                    finish();
                }
            }
        });
    }
}