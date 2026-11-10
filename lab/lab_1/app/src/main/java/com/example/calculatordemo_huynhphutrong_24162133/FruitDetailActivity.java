package com.example.calculatordemo_huynhphutrong_24162133;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class FruitDetailActivity extends AppCompatActivity {

    public static final String EXTRA_FRUIT = "fruit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_fruit_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Fruit fruit = (Fruit) getIntent().getSerializableExtra(EXTRA_FRUIT);
        if (fruit == null) {
            finish();
            return;
        }

        TextView tvEmoji = findViewById(R.id.tvDetailEmoji);
        tvEmoji.setText(fruit.emoji);
        tvEmoji.setBackgroundTintList(ColorStateList.valueOf(fruit.color));
        ((TextView) findViewById(R.id.tvDetailName)).setText(fruit.name);
        ((TextView) findViewById(R.id.tvDetailPrice)).setText(fruit.price);
        ((TextView) findViewById(R.id.tvDetailOrigin)).setText(fruit.origin);
        ((TextView) findViewById(R.id.tvDetailVitamins)).setText(fruit.vitamins);
        ((TextView) findViewById(R.id.tvDetailDesc)).setText(fruit.description);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
