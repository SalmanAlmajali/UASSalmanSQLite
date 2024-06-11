package com.example.uassalmansqlite.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uassalmansqlite.ListStudentsActivity;
import com.example.uassalmansqlite.R;
import com.example.uassalmansqlite.UpdateActivity;
import com.example.uassalmansqlite.db.DbHelper;
import com.example.uassalmansqlite.model.Students;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private ArrayList<Students> listStudents = new ArrayList<>();
    private Activity activity;
    private DbHelper dbHelper;

    public StudentsAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Students> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<Students> listNotes) {
        if (listNotes.size() > 0) {
            this.listStudents.clear();
        }
        this.listStudents.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,
                        parent, false);
        return new StudentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StudentViewHolder holder, int position) {
        holder.nim.setText(listStudents.get(position).getNim());
        holder.nama.setText(listStudents.get(position).getNama());
        holder.hp.setText(listStudents.get(position).getHp());
        holder.studi.setText(listStudents.get(position).getStudi());
        holder.email.setText(listStudents.get(position).getEmail());

        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateActivity.class);
            intent.putExtra("user", (Serializable) listStudents.get(position));
            activity.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new
                    AlertDialog.Builder(activity);
            builder.setTitle("Konfirmasi hapus");
            builder.setMessage("Apakah yakin akan dihapus?");
            builder.setPositiveButton("YA", (dialog, which) -> {
                dbHelper.delete(listStudents.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!",
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity,
                        ListStudentsActivity.class);
                activity.startActivity(myIntent);
                activity.finish();
            });
            builder.setNegativeButton("TIDAK", (dialog, which) ->
                    dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        final TextView nim, nama, hp, studi, email;
        final Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nim = itemView.findViewById(R.id.item_nim);
            this.nama = itemView.findViewById(R.id.item_nama);;
            this.hp = itemView.findViewById(R.id.item_hp);;
            this.studi = itemView.findViewById(R.id.item_studi);;
            this.email = itemView.findViewById(R.id.item_email);;
            this.btnEdit = itemView.findViewById(R.id.button_edit);;
            this.btnDelete = itemView.findViewById(R.id.button_delete);;
        }
    }
}
