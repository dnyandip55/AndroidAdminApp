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
    private RecyclerView animationDepartment,biotechDepartment,botanyDepartment,chemDepartment,csDepartment,electronicDepartment,mathDepartment,microbiologyDepartment,physicsDepartment,statDepartment;
    private LinearLayout animationNoData,biotechNoData,botanyNoData,chemNoData,csNoData,electronicNoData,mathematicsNoData,microbiologyNoData,physicsNoData,statisticsNoData;

    private List<TeacherData> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10;
    private TeacherAdapter adapter;

    private DatabaseReference reference,dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        animationDepartment=findViewById(R.id.animationDepartment);
        biotechDepartment=findViewById(R.id.biotechDepartment);
        botanyDepartment=findViewById(R.id.botonyDepartment);
        chemDepartment=findViewById(R.id.chemDepartment);
        csDepartment=findViewById(R.id.csDepartment);
        electronicDepartment=findViewById(R.id.electronicDepartment);
        mathDepartment=findViewById(R.id.mathematicsDepartment);
        microbiologyDepartment=findViewById(R.id.microbiologyDepartment);
        physicsDepartment=findViewById(R.id.physicsDepartment);
        statDepartment=findViewById(R.id.statisticsDepartment);


        animationNoData=findViewById(R.id.animationNoData);
        biotechNoData=findViewById(R.id.biotechNoData);
        botanyNoData=findViewById(R.id.botanyNoData);
        chemNoData=findViewById(R.id.chemNoData);
        csNoData=findViewById(R.id.csNoData);
        electronicNoData=findViewById(R.id.electronicNoData);
        mathematicsNoData=findViewById(R.id.mathematicsNoData);
        microbiologyNoData=findViewById(R.id.microbiologyNoData);
        physicsNoData=findViewById(R.id.physicsNoData);
        statisticsNoData=findViewById(R.id.statisticsNoData);


        reference= FirebaseDatabase.getInstance().getReference().child("Teachers");

        animaDepartment();
        bioDepartment();
        botDepartment();
        chemDepartment();
        csDepartment();
        electroDepartment();
        mathematicsDepartment();
        microbioDepartment();
        physicsDepartment();
        statisticsDepartment();







        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this, AddTeachers.class));
            }
        });

    }

    private void animaDepartment() {
        dbRef=reference.child("Animation");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    animationNoData.setVisibility(View.VISIBLE);
                    animationDepartment.setVisibility(View.GONE);
                }else{
                    animationNoData.setVisibility(View.GONE);
                    animationDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    animationDepartment.setHasFixedSize(true);
                    animationDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list1,UpdateFaculty.this,"Animation");
                    animationDepartment.setAdapter(adapter);

                }

    }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bioDepartment() {
        dbRef=reference.child("Biotechnology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if(!snapshot.exists()){
                    biotechNoData.setVisibility(View.VISIBLE);
                    biotechDepartment.setVisibility(View.GONE);
                }else{
                    biotechNoData.setVisibility(View.GONE);
                    biotechDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    biotechDepartment.setHasFixedSize(true);
                    biotechDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list2,UpdateFaculty.this,"Biotechnology");
                    biotechDepartment.setAdapter(adapter);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void botDepartment() {
        dbRef=reference.child("Botany");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    botanyNoData.setVisibility(View.VISIBLE);
                    botanyDepartment.setVisibility(View.GONE);
                }else{
                    botanyNoData.setVisibility(View.GONE);
                    botanyDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    botanyDepartment.setHasFixedSize(true);
                    botanyDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list3,UpdateFaculty.this,"Animation");
                    botanyDepartment.setAdapter(adapter);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chemDepartment() {
        dbRef=reference.child("Chemistry");
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
                    adapter=new TeacherAdapter(list4,UpdateFaculty.this,"Chemistry");
                    chemDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void csDepartment() {
        dbRef=reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5=new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list5,UpdateFaculty.this,"Computer Science");
                    csDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void electroDepartment() {
        dbRef=reference.child("Electronic Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6=new ArrayList<>();
                if(!snapshot.exists()){
                    electronicNoData.setVisibility(View.VISIBLE);
                    electronicDepartment.setVisibility(View.GONE);
                }else{
                    electronicNoData.setVisibility(View.GONE);
                    electronicDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    electronicDepartment.setHasFixedSize(true);
                    electronicDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list6,UpdateFaculty.this,"Electronic Department");
                    electronicDepartment.setAdapter(adapter);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void mathematicsDepartment() {
        dbRef=reference.child("Mathematics Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7=new ArrayList<>();
                if(!snapshot.exists()){
                    mathematicsNoData.setVisibility(View.VISIBLE);
                    mathDepartment.setVisibility(View.GONE);
                }else{
                    mathematicsNoData.setVisibility(View.GONE);
                    mathDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    mathDepartment.setHasFixedSize(true);
                    mathDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list7,UpdateFaculty.this,"Mathematics Department");
                    mathDepartment.setAdapter(adapter);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void microbioDepartment() {
        dbRef=reference.child("Microbiology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list8=new ArrayList<>();
                if(!snapshot.exists()){
                    microbiologyNoData.setVisibility(View.VISIBLE);
                    microbiologyDepartment.setVisibility(View.GONE);
                }else{
                    microbiologyNoData.setVisibility(View.GONE);
                    microbiologyDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    microbiologyDepartment.setHasFixedSize(true);
                    microbiologyDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list8,UpdateFaculty.this,"Microbiology");
                    microbiologyDepartment.setAdapter(adapter);

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
                list9=new ArrayList<>();
                if(!snapshot.exists()){
                    physicsNoData.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }else{
                    physicsNoData.setVisibility(View.GONE);
                    physicsDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list9,UpdateFaculty.this,"Physics");
                    physicsDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void  statisticsDepartment() {
        dbRef=reference.child("Statistics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list10=new ArrayList<>();
                if(!snapshot.exists()){
                    statisticsNoData.setVisibility(View.VISIBLE);
                    statDepartment.setVisibility(View.GONE);
                }else{
                    statisticsNoData.setVisibility(View.GONE);
                    statDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    statDepartment.setHasFixedSize(true);
                    statDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list10,UpdateFaculty.this,"Statistics");
                    statDepartment.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}