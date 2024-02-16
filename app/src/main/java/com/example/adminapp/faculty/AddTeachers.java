package com.example.adminapp.faculty;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddTeachers extends AppCompatActivity {

    private ImageView addTeacherImage;
    private EditText teacherName;
    private EditText teacherEmail;
    private EditText teacherPost;
    private Spinner addTeacherCategory;
    private Button addTeacherBtn;
    private Bitmap bitmap = null;
    private String category;
    private String name, email, post;
    private ProgressDialog pd;
    private DatabaseReference reference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teachers);

        // Initialize Views
        addTeacherImage = findViewById(R.id.addTeacherImage);
        teacherName = findViewById(R.id.addTeacherName);
        teacherEmail = findViewById(R.id.addTeacherEmail);
        teacherPost = findViewById(R.id.addTeacherPost);
        addTeacherCategory = findViewById(R.id.addTeacherCategory);
        addTeacherBtn = findViewById(R.id.addTeacherBtn);
        pd = new ProgressDialog(this);

        // Initialize Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");
        storageReference = FirebaseStorage.getInstance().getReference();

        // Spinner Initialization
        String[] items = new String[]{"Select Category", "Computer Science", "Physics", "Mechanical", "Chemical"};
        addTeacherCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        // Button Click Listeners
        addTeacherImage.setOnClickListener(v -> openGallery());

        addTeacherBtn.setOnClickListener(v -> checkValidation());
    }

    private void checkValidation() {
        name = teacherName.getText().toString();
        email = teacherEmail.getText().toString();
        post = teacherPost.getText().toString();
        category = addTeacherCategory.getSelectedItem().toString();


        if (name.isEmpty() || email.isEmpty() || post.isEmpty() || category.equals("Select Category")) {
            Toast.makeText(this, "Please fill all fields and select a category", Toast.LENGTH_SHORT).show();
        } else if (bitmap == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        } else {
            pd.setMessage("Uploading...");
            pd.show();
            uploadImage();
        }
    }


    private void uploadImage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath = storageReference.child("Teachers").child(System.currentTimeMillis() + ".jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            filepath.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                addTeacherToDatabase(downloadUrl);
            });
        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(AddTeachers.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
        });
    }

    private void addTeacherToDatabase(String imageUrl) {
        String uniqKey = reference.child(category).push().getKey();
        TeacherData teacherData = new TeacherData(name, email, post, imageUrl, uniqKey);
        reference.child(category).child(uniqKey).setValue(teacherData)
                .addOnSuccessListener(aVoid -> {
                    pd.dismiss();
                    Toast.makeText(AddTeachers.this, "Teacher Added", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    pd.dismiss();
                    Toast.makeText(AddTeachers.this, "Failed to add teacher", Toast.LENGTH_SHORT).show();
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
                        addTeacherImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
}