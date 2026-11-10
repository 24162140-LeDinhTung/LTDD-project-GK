package com.example.calculatordemo_huynhphutrong_24162133;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // Lưu tài khoản tạm trong bộ nhớ (email -> mật khẩu), chỉ phục vụ demo lab
    private final Map<String, String> accounts = new HashMap<>();
    private boolean isLoginMode = true;

    private View layoutLogin, layoutRegister;
    private TextView tvTitle, tvSubtitle, tvSwitch;
    private TextInputEditText edtLoginEmail, edtLoginPassword;
    private TextInputEditText edtRegName, edtRegEmail, edtRegPassword, edtRegConfirm;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegister = findViewById(R.id.layoutRegister);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tvSubtitle);
        tvSwitch = findViewById(R.id.tvSwitch);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtRegName = findViewById(R.id.edtRegName);
        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegPassword = findViewById(R.id.edtRegPassword);
        edtRegConfirm = findViewById(R.id.edtRegConfirm);
        cbRemember = findViewById(R.id.cbRemember);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        tvSwitch.setOnClickListener(v -> setMode(!isLoginMode));
        btnLogin.setOnClickListener(v -> doLogin());
        btnRegister.setOnClickListener(v -> doRegister());
    }

    private void setMode(boolean login) {
        isLoginMode = login;
        layoutLogin.setVisibility(login ? View.VISIBLE : View.GONE);
        layoutRegister.setVisibility(login ? View.GONE : View.VISIBLE);
        tvTitle.setText(login ? "ĐĂNG NHẬP" : "ĐĂNG KÝ");
        tvSubtitle.setText(login ? "Chào mừng bạn quay lại" : "Tạo tài khoản mới");
        tvSwitch.setText(login ? "Chưa có tài khoản? Đăng ký" : "Đã có tài khoản? Đăng nhập");
    }

    private String text(TextInputEditText e) {
        return e.getText() == null ? "" : e.getText().toString().trim();
    }

    private void doLogin() {
        String email = text(edtLoginEmail);
        String pass = text(edtLoginPassword);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtLoginEmail.setError("Email không hợp lệ");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            edtLoginPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }
        String saved = accounts.get(email);
        if (saved == null) {
            Toast.makeText(this, "Tài khoản chưa tồn tại, hãy đăng ký", Toast.LENGTH_SHORT).show();
        } else if (!saved.equals(pass)) {
            edtLoginPassword.setError("Sai mật khẩu");
        } else {
            String extra = cbRemember.isChecked() ? " (đã ghi nhớ)" : "";
            Toast.makeText(this, "Đăng nhập thành công" + extra, Toast.LENGTH_SHORT).show();        }
    }

    private void doRegister() {
        String name = text(edtRegName);
        String email = text(edtRegEmail);
        String pass = text(edtRegPassword);
        String confirm = text(edtRegConfirm);

        if (name.isEmpty()) {
            edtRegName.setError("Vui lòng nhập họ tên");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtRegEmail.setError("Email không hợp lệ");
            return;
        }
        if (pass.length() < 6) {
            edtRegPassword.setError("Mật khẩu tối thiểu 6 ký tự");
            return;
        }
        if (!pass.equals(confirm)) {
            edtRegConfirm.setError("Mật khẩu nhập lại không khớp");
            return;
        }
        if (accounts.containsKey(email)) {
            edtRegEmail.setError("Email đã được đăng ký");
            return;
        }

        accounts.put(email, pass);
        Toast.makeText(this, "Đăng ký thành công! Hãy đăng nhập", Toast.LENGTH_SHORT).show();
        edtLoginEmail.setText(email);
        edtLoginPassword.setText("");
        setMode(true);
    }
}
