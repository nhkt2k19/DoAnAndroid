package com.example.doanandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends Activity {

	protected EditText edtEmail;
	protected EditText edtMatKhau;
	protected TextView tvQuenMatKhau;
	protected ImageView lock;
	protected ImageView account_mail;
	public Button btnDangNhap;
	protected Button btnDangKy;
	private FirebaseAuth mAuth;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dang_nhap);
		edtEmail= findViewById(R.id.edtEmail);
		edtMatKhau = findViewById(R.id.edtMatKhau);
		account_mail = findViewById(R.id.account_mail);
		lock=findViewById(R.id.lock);
        tvQuenMatKhau =  findViewById(R.id.tvQuenMatKhau);
		btnDangKy = findViewById(R.id.btnDangKy);
		btnDangNhap=findViewById(R.id.btnDangNhap);
		//custom code goes here
		mAuth = FirebaseAuth.getInstance();
		btnDangNhap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = edtEmail.getText().toString().trim();
				String password = edtMatKhau.getText().toString().trim();

				if (TextUtils.isEmpty(email)) {
					Toast.makeText(getApplicationContext(), "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
					return;
				}

				if (TextUtils.isEmpty(password)) {
					Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
					return;
				}

				// Đăng nhập bằng email và mật khẩu
				mAuth.signInWithEmailAndPassword(email, password)
						.addOnCompleteListener(DangNhapActivity.this, task -> {
							if (task.isSuccessful()) {
								// Đăng nhập thành công, chuyển hướng đến activity tiếp theo
								Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
								startActivity(intent);
								finishAffinity();

							} else {
								// Đăng nhập thất bại, hiển thị thông báo lỗi
								Toast.makeText(getApplicationContext(), "Đăng nhập thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
							}
						});
			}
		});
	}
}
	
	