package com.example.herbaware;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herbaware.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class TakePhotoActivity extends AppCompatActivity {

    //Declaring Variables
    ImageView imageView;
    AppCompatButton btnOpenCamera, btnReadMore;
    TextView prediction;
    ImageButton buttonHome;
    int imageSize =64;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_take_photo);

        imageView = findViewById(R.id.image_view);
        btnOpenCamera = findViewById(R.id.btnopencam);
        btnReadMore = findViewById(R.id.btn_readmore);
        buttonHome =findViewById(R.id.btnhome);
        prediction = findViewById(R.id.txtviewprdct);


        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
    }

    public void predictImage(Bitmap imageBitmap){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 64, 64, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int [] intValues = new int[imageSize * imageSize];
            imageBitmap.getPixels(intValues,0,imageBitmap.getWidth(),0,  0,imageBitmap.getWidth(),imageBitmap.getHeight());
            int pixel = 0;
            for (int i =0; i < imageSize; i++){
                for (int j =0; j < imageSize; j++){
                    int val = intValues [pixel++]; //RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF)*(1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF)*(1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF)*(1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float [] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++){
                if (confidences[i] > maxConfidence){
                    maxConfidence =  confidences[i];
                    maxPos = i;
                }
            }
            String [] classes = {"Thai Ginger", "Amaranthus Viridis(3fan)", "Jackfruit", "Neem Plant",
                    "Spinach Plant", "Indian Mustard", "Carissa Carandas(Nkonkyere)", "Lemon Plant (Ankaatwade3)",
                    "Roxburgh Fig", "Peepal Tree", "Hibiscus Plant (Bisap)", "Jasmine Plant", "Mango Plant",
                    "Mentha(Numnum)", "Moringa", "Mutingia Calabura", "Curry Plant", "Oleander", "Parijata",
                    "Ocimum Tenuiflorum (Akokob3sa)", "Piper Betle (Sorowisa)", "Plectranthus Pinnata (Fomwisa)",
                    "Indian Beech", "Guava Plant", "Pomegranate", "Sandalwood", "Jamum", "Rose Apple Plant (Abrofo Kutum3)",
                    "Crape Jasmine", "Fenugreek"};

            prediction.setText(classes[maxPos]);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(imageBitmap.getWidth(),imageBitmap.getHeight());
            imageBitmap = ThumbnailUtils.extractThumbnail(imageBitmap,dimension,dimension);
            imageView.setImageBitmap(imageBitmap);

            imageBitmap = Bitmap.createScaledBitmap(imageBitmap,imageSize, imageSize,false);
            predictImage(imageBitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    }




