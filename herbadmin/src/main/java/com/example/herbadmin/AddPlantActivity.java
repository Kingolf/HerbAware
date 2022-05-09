package com.example.herbadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;



public class AddPlantActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE_REQUEST = 1;

    private CircularImageView plant_image;
    private EditText plant_name, bot_name, dop, prepare, bnr;


    private Uri plantImgUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private StorageTask UploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        //Hiding ActionBar---------------------------------
        /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
         Objects.requireNonNull(getSupportActionBar()).hide();**/
        //-------------------------------------------------

        ImageButton btn_back = findViewById(R.id.btn_back);
        plant_image = findViewById(R.id.plant_img);
        AppCompatButton add_image_btn = findViewById(R.id.btn_add_image);
        AppCompatButton btn_add = findViewById(R.id.btn_add);
        plant_name = findViewById(R.id.plant_name);
        bot_name = findViewById(R.id.bot_name);
        dop = findViewById(R.id.dop);
        prepare = findViewById(R.id.prepare);
        bnr = findViewById(R.id.bnr);


        add_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenImageSelector();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("Plant Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Plant Info");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plantName = plant_name.getText().toString();
                String botName = bot_name.getText().toString();
                String plantDetails = dop.getText().toString();
                String prep = prepare.getText().toString();
                String bNR = bnr.getText().toString();

                if (TextUtils.isEmpty(plantName)) {
                    plant_name.setError("Required");
                } else if (TextUtils.isEmpty(botName)) {
                    bot_name.setError("Required");
                } else if (TextUtils.isEmpty(plantDetails)) {
                    dop.setError("Required");
                } else if (TextUtils.isEmpty(prep)) {
                    prepare.setError("Required");
                } else if (TextUtils.isEmpty(bNR)) {
                    bnr.setError("Required");
                } else {
                    if (UploadTask != null && UploadTask.isInProgress()) {
                        Toast.makeText(AddPlantActivity.this, "Upload is in Progress", Toast.LENGTH_SHORT).show();

                    } else {
                        uploadFile(plantName, botName, plantDetails, prep, bNR);
                        plant_name.setText("");
                        bot_name.setText("");
                        dop.setText("");
                        prepare.setText("");
                        bnr.setText("");
                    }
                }


            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void uploadFile(String p_name, String b_name, String dop,String prep,String bnr) {
        if(plantImgUri != null){
            StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(plantImgUri));
            UploadTask = reference.putFile(plantImgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddPlantActivity.this,"Upload Successfully",Toast.LENGTH_LONG).show();
                            Plant plant = new Plant(p_name,b_name,dop,prep,bnr,taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            String plantID = databaseReference.push().getKey();
                            databaseReference.child(plantID).setValue(plant);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPlantActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else{
            Toast.makeText(this,"No Image file selected",Toast.LENGTH_SHORT).show();
        }


    }
    private void OpenImageSelector(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            plantImgUri = data.getData();

            Picasso.get().load(plantImgUri).into(plant_image);
            plant_image.setImageURI(plantImgUri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}