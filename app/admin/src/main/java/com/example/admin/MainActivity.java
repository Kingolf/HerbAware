package com.example.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.ArrayDeque;

public class MainActivity extends AppCompatActivity {

    EditText plantName, botName, about, uses, risks;
    AppCompatButton addImage, addPlant;
    ImageButton back;
    AppCompatSpinner categories;
    CircularImageView plantImage;
    ProgressDialog progressDialog;
    int Image_Request_Code = 7;
    private Uri imageUri;

    DatabaseReference database;
    StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        plantName = findViewById(R.id.plant_name);
        back = findViewById(R.id.btn_back);
        botName = findViewById(R.id.bot_name);
        about = findViewById(R.id.dop);
        uses = findViewById(R.id.uses);
        risks = findViewById(R.id.risks);
        addImage = findViewById(R.id.btn_add_image);
        addPlant = findViewById(R.id.btn_add_plant);
        plantImage = findViewById(R.id.plant_img);
        database = FirebaseDatabase.getInstance().getReference("Plants");
        reference = FirebaseStorage.getInstance().getReference("Plant Images");
        progressDialog = new ProgressDialog(MainActivity.this);
        categories = findViewById(R.id.categories_spinner);



        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });

        addPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImage();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                plantImage.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    private void UploadImage() {
        if (imageUri != null) {

            progressDialog.setTitle("Data is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = reference.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));
            storageReference2.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String plant_name = plantName.getText().toString();
                            String botanical_name = botName.getText().toString();
                            String about_plant = about.getText().toString();
                            String plant_use = uses.getText().toString();
                            String side_effects = risks.getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Plants imageUploadInfo = new Plants(plant_name,botanical_name,
                                    about_plant,plant_use,side_effects, taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = database.push().getKey();
                            database.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(MainActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }


}
