package com.example.uassalmansqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uassalmansqlite.db.DbHelper;
import com.example.uassalmansqlite.model.Students;

public class UpdateActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private EditText etNim, etNama, etHp, etStudi, etEmail;
    private Button btnSave;
    private Students students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        etNim = findViewById(R.id.edit_nim);
        etNama = findViewById(R.id.edit_nama);
        etHp = findViewById(R.id.edit_hp);
        etStudi = findViewById(R.id.edit_studi);
        etEmail = findViewById(R.id.edit_email);
        btnSave = findViewById(R.id.button_update);

        Intent intent = getIntent();
        students = (Students) intent.getSerializableExtra("user");

        assert students != null;
        etNim.setText(students.getNim());
        etNama.setText(students.getNama());
        etHp.setText(students.getHp());
        etStudi.setText(students.getStudi());
        etEmail.setText(students.getEmail());

        btnSave.setOnClickListener((View v) -> {
            if (etNim.getText().toString().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Error: Nim harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etNama.getText().toString().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etHp.getText().toString().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Error: Nomor Handphone harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etStudi.getText().toString().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Error: Program Studi harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.update(
                        students.getId(),
                        etNim.getText().toString(),
                        etNama.getText().toString(),
                        etHp.getText().toString(),
                        etStudi.getText().toString(),
                        etEmail.getText().toString()
                );

                Toast.makeText(UpdateActivity.this, "Updated berhasil!",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}