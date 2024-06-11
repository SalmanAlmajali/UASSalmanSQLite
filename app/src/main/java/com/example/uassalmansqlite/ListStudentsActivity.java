package com.example.uassalmansqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uassalmansqlite.R;
import com.example.uassalmansqlite.adapter.StudentsAdapter;
import com.example.uassalmansqlite.db.DbHelper;
import com.example.uassalmansqlite.model.Students;

import java.util.ArrayList;

public class ListStudentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentsAdapter adapter;
    private ArrayList<Students> studentsArrayList;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        recyclerView = (RecyclerView) findViewById(R.id.rv_view);
        adapter = new StudentsAdapter(this);
        dbHelper = new DbHelper(this);
        studentsArrayList = dbHelper.getAllStudents();
        adapter.setListStudents(studentsArrayList);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(ListStudentsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsArrayList = dbHelper.getAllStudents();
        adapter.setListStudents(studentsArrayList);
        adapter.notifyDataSetChanged();
    }
}