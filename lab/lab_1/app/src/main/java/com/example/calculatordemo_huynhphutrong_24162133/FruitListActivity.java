package com.example.calculatordemo_huynhphutrong_24162133;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class FruitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Data source
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Apple (Táo)", "🍎", "45.000đ/kg", "Mỹ",
                "Vitamin C, chất xơ",
                "Táo giòn, ngọt thanh, giàu chất xơ và chất chống oxy hóa. Ăn mỗi ngày giúp tốt cho tim mạch và tiêu hóa.",
                Color.parseColor("#FFD9D9")));
        fruits.add(new Fruit("Banana (Chuối)", "🍌", "25.000đ/kg", "Việt Nam",
                "Kali, vitamin B6",
                "Chuối chín mềm, ngọt dịu, cung cấp năng lượng nhanh và bổ sung kali tốt cho cơ bắp.",
                Color.parseColor("#FFF3C4")));
        fruits.add(new Fruit("Cherry (Anh đào)", "🍒", "250.000đ/kg", "Chile",
                "Vitamin C, anthocyanin",
                "Cherry đỏ thẫm, vị ngọt chua nhẹ, chứa nhiều chất chống viêm và hỗ trợ giấc ngủ ngon.",
                Color.parseColor("#FAD2E1")));
        fruits.add(new Fruit("Date (Chà là)", "🌴", "120.000đ/kg", "Ả Rập Xê Út",
                "Sắt, magie, chất xơ",
                "Chà là ngọt đậm như kẹo caramel tự nhiên, giàu năng lượng, thích hợp ăn vặt thay đồ ngọt.",
                Color.parseColor("#E8D5C4")));
        fruits.add(new Fruit("Grapes (Nho)", "🍇", "90.000đ/kg", "Úc",
                "Vitamin K, resveratrol",
                "Nho mọng nước, vị ngọt thanh, chứa resveratrol giúp bảo vệ tim và làm chậm lão hóa.",
                Color.parseColor("#E2D4F5")));

        // Find ListView by ID
        ListView listView = findViewById(R.id.listView);

        // Create an Adapter (adapter tự viết, xem FruitAdapter.java)
        FruitAdapter adapter = new FruitAdapter(this, fruits);

        // Set adapter to ListView
        listView.setAdapter(adapter);

        // Bấm vào item -> mở màn hình chi tiết
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, FruitDetailActivity.class);
            intent.putExtra(FruitDetailActivity.EXTRA_FRUIT, fruits.get(position));
            startActivity(intent);
        });
    }
}
