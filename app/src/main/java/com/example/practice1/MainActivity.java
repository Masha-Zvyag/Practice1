package com.example.practice1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;

public class MainActivity extends AppCompatActivity {

    private TextView tvOut;
    private Button btnWhoAmI;
    private CheckBox checkBox;
    private Button btnItIsNotMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        checkBox = findViewById(R.id.checkBox);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);

        btnWhoAmI.setOnClickListener(v -> {
            tvOut.setText("Мой номер по списку № Х (по журналу)");
            checkBox.toggle();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });
    }
    public void onMyButtonClick(View view) {
        tvOut.setText("Это не я сделал");
        checkBox.setChecked(!checkBox.isChecked());
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
    }
}