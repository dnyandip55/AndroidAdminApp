package com.example.adminapp.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView csDepartment,physicsDepartment,mechDepartment,chemDepartment;
    private LinearLayout csNoData,physicsNoData,mechNoData,chemNoData;

    private List<TeacherData> list1,list2,list3,list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference,dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        csDepartment=findViewById(R.id.csDepartment);
        physicsDepartment=findViewById(R.id.physicsDepartment);
        mechDepartment=findViewById(R.id.mechDepartment);
        chemDepartment=findViewById(R.id.chemDepartment);

        csNoData=findViewById(R.id.csNoData);
        physicsNoData=findViewById(R.id.physicsNoData);
        mechNoData=findViewById(R.id.mechNoData);
        chemNoData=findViewById(R.id.chemNoData);

        reference= FirebaseDatabase.getInstance().getReference().child("Teachers");

        csDepartment();
        mechDepartment();
        physicsDepartment();
        chemDepartment();





        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this, AddTeachers.class));
            }
        });

    }

    private void csDepartment() {
        dbRef=reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list1,UpdateFaculty.this,"Computer Science");
                    csDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void mechDepartment() {
        dbRef=reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if(!snapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDepartment.setVisibility(View.GONE);
                }else{
                    mechNoData.setVisibility(View.GONE);
                    mechDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mechDepartment.setHasFixedSize(true);
                    mechDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list2,UpdateFaculty.this,"Mechanical");
                    mechDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void physicsDepartment() {
        dbRef=reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    physicsNoData.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }else{
                    physicsNoData.setVisibility(View.GONE);
                    physicsDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list3,UpdateFaculty.this,"Physics");
                    physicsDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chemDepartment() {
        dbRef=reference.child("Chemical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4=new ArrayList<>();
                if(!snapshot.exists()){
                    chemNoData.setVisibility(View.VISIBLE);
                    chemDepartment.setVisibility(View.GONE);
                }else{
                    chemNoData.setVisibility(View.GONE);
                    chemDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    chemDepartment.setHasFixedSize(true);
                    chemDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list4,UpdateFaculty.this,"Chemical");
                    chemDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}