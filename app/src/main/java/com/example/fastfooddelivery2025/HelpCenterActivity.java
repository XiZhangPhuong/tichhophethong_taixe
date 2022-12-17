package com.example.fastfooddelivery2025;

import static com.example.fastfooddelivery2025.InitData.referenceHelpCenter;
import static com.example.fastfooddelivery2025.InitData.KeyUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fastfooddelivery2025.Data.DataSharedPreferences;
import com.example.fastfooddelivery2025.Model.HelpCenter;

public class HelpCenterActivity extends AppCompatActivity {
    private Button btn_send;
    private EditText edt_title_helpCenter,edt_ep_helpCenter,edt_content_helpCenter;
    private ImageView img_back_helpCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        edt_title_helpCenter = findViewById(R.id.edt_title_helpCenter);
        edt_ep_helpCenter = findViewById(R.id.edt_ep_helpCenter);
        edt_content_helpCenter = findViewById(R.id.edt_content_helpCenter);
        btn_send  = findViewById(R.id.btn_send);
        img_back_helpCenter = findViewById(R.id.img_back_helpCenter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edt_title_helpCenter.getText().toString();
                String ep = edt_ep_helpCenter.getText().toString();
                String content = edt_content_helpCenter.getText().toString();
                String id = referenceHelpCenter.push().getKey();
                HelpCenter mHelpCenter = new HelpCenter(id,title,ep,content, DataSharedPreferences.getUser(getApplicationContext(),KeyUser).getId_staff());
                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(ep) || TextUtils.isEmpty(content)){
                    Toast.makeText(HelpCenterActivity.this,"Không được để trống",Toast.LENGTH_LONG).show();
                    return;
                }
                referenceHelpCenter.child(id).setValue(mHelpCenter);
                Toast.makeText(HelpCenterActivity.this,"Gửi thành công",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        img_back_helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}