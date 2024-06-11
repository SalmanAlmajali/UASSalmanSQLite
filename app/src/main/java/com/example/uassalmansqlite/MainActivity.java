package com.example.uassalmansqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uassalmansqlite.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private EditText etNim, etNama, etHp, etStudi, etEmail;
    private Button btnSave, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        etNim = findViewById(R.id.nim);
        etNama = findViewById(R.id.nama);
        etHp = findViewById(R.id.hp);
        etStudi = findViewById(R.id.studi);
        etEmail = findViewById(R.id.email);
        btnSave = findViewById(R.id.button_submit);
        btnList = findViewById(R.id.button_list);

        btnSave.setOnClickListener(v -> {
            if (etNim.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Nim harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etNama.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etHp.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Nomor Handphone harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etStudi.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Program Studi harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.store(
                        etNim.getText().toString(),
                        etNama.getText().toString(),
                        etHp.getText().toString(),
                        etStudi.getText().toString(),
                        etEmail.getText().toString()
                );

                etNim.setText("");
                etNama.setText("");
                etHp.setText("");
                etStudi.setText("");
                etEmail.setText("");

                Toast.makeText(MainActivity.this, "Simpan berhasil!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    ListStudentsActivity.class);
            startActivity(intent);
        });
    }
}