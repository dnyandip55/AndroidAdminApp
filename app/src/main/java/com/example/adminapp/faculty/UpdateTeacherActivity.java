package com.example.adminapp.faculty;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacherActivity extends AppCompatActivity {
    private ImageView updateTeacherImage;
    private EditText updateTeacherName ,updateTeacherEmail,updateTeacherPost;
    private Button updateTeacherBtn,deleteTeacherBtn;

    private  String name,email,image,post;
    private Bitmap bitmap = null;
    private StorageReference storageReference;
    private DatabaseReference reference;
    private ProgressDialog pd;
    private String downloadUrl,category,uniqueKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        post=getIntent().getStringExtra("post");
        image=getIntent().getStringExtra("image");
        uniqueKey=getIntent().getStringExtra("key");
        category=getIntent().getStringExtra("category");

        updateTeacherName=findViewById(R.id.updateTeacherName);
        updateTeacherImage=findViewById(R.id.updateTeacherImage);
        updateTeacherEmail=findViewById(R.id.updateTeacherEmail);
        updateTeacherPost=findViewById(R.id.updateTeacherPost);
        updateTeacherBtn=findViewById(R.id.updateTeacherBtn);
        deleteTeacherBtn=findViewById(R.id.deleteTeacherBtn);

        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");
        storageReference = FirebaseStorage.getInstance().getReference();

        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        updateTeacherEmail.setText(email);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);

        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=updateTeacherName.getText().toString();
                email=updateTeacherEmail.getText().toString();
                post=updateTeacherPost.getText().toString();

                checkValidation();
            }
        });

        deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    private void deleteData() {
        reference.child(category).child(uniqueKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateTeacherActivity.this,"Teacher Deleted Succesfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateTeacherActivity.this,UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
           Toast.makeText(UpdateTeacherActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkValidation() {
        if(name.isEmpty()){
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        } else if (post.isEmpty()) {
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        }
        else if (email.isEmpty()) {
            updateTeacherEmail.setError("Empty");
            updateTeacherEmail.requestFocus();
        } else if (bitmap==null) {
            updateData("");

        }else{
            uploadImage();
        }

    }

    private void uploadImage() {

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg=baos.toByteArray();
        final StorageReference filePath;
        filePath=storageReference.child("Teachers").child(finalimg+ "jpg");
        final UploadTask uploadTask=filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateTeacherActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=String.valueOf(uri);
                                    updateData(downloadUrl);
                                }
                            });
                        }
                    });
                }else{
//                    pd.dismiss();
                    Toast.makeText(UpdateTeacherActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateData(String s) {
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("name", name);
        hp.put("email", email);
        hp.put("post", post);
        if (s != null && !s.isEmpty()) {
            hp.put("image", s); // Only update the image if it's not empty
        }

        reference.child(category).child(uniqueKey).updateChildren(hp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateTeacherActivity.this, "Teacher Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateTeacherActivity.this, UpdateFaculty.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateTeacherActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        someActivityResultLauncher.launch(pickImage);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        updateTeacherImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
}