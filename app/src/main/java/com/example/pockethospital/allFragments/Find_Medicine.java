package com.example.pockethospital.allFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.example.pockethospital.MainActivity;
import com.example.pockethospital.Models.MedicineModel;
import com.example.pockethospital.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Find_Medicine extends AppCompatActivity {
    Button sell;
    ImageView imageView;
    Button button;
    Uri selectedImage;
    FirebaseDatabase database;
    DatabaseReference myRef;
    StorageReference mStorageRef,Ref;
    ProgressBar progressBar1;
    String patientName,patientNumber,patientAddress;
    EditText Name, Number, Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__medicine);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Medicines");
        mStorageRef = FirebaseStorage.getInstance().getReference("medicines");
        imageView=findViewById(R.id.medImage);
        progressBar1=findViewById(R.id.progressBar);
        Name = findViewById(R.id.patientName);
        Number =findViewById(R.id.patientNumber);
        Address =findViewById(R.id.patientAddress);
        sell=(Button)findViewById(R.id.next_button);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryOpens();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }

        }
    }
    public void galleryOpens() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose Image"),1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if(requestCode==1 && resultCode==RESULT_OK && data!=null)
       {
           try {
               selectedImage=data.getData();
               Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                imageView.setImageBitmap(bitmap);
              imageView.setVisibility(View.VISIBLE);

         } catch (IOException e) {
               e.printStackTrace();
            }
       }
      else
     {
           Toast.makeText(this,"Please Select Image",Toast.LENGTH_SHORT).show();
       }
    }
    public void uploadImage() {
        progressBar1.setVisibility(View.VISIBLE);
        patientName=Name.getText().toString();
        patientNumber=Number.getText().toString();
        patientAddress=Address.getText().toString();

        if(TextUtils.isEmpty(patientName) || TextUtils.isEmpty(patientAddress) || TextUtils.isEmpty(patientNumber) ){
            Toast.makeText(this,"Check the details",Toast.LENGTH_SHORT).show();
            progressBar1.setVisibility(View.INVISIBLE);
        }
        else{
            progressBar1.setVisibility(View.VISIBLE);
            Ref=mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(selectedImage));
            Ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            linkUp();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            progressBar1.setVisibility(View.INVISIBLE);
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            progressBar1.setVisibility(View.VISIBLE);
                        }
                    });
        }

    }
    private void linkUp() {
        imageView.setVisibility(View.INVISIBLE);
        progressBar1.setVisibility(View.INVISIBLE);
        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri downloadUrl = uri;
                String mdid=myRef.push().getKey();
                patientName=Name.getText().toString();
                patientNumber=Number.getText().toString();
                patientAddress=Address.getText().toString();
                if(TextUtils.isEmpty(patientName) || TextUtils.isEmpty(patientAddress) || TextUtils.isEmpty(patientNumber) ){
                    Toast.makeText(Find_Medicine.this,"Check the details",Toast.LENGTH_SHORT).show();
                    progressBar1.setVisibility(View.INVISIBLE);
                }
                else {
                    MedicineModel helper=new MedicineModel(downloadUrl.toString(),patientName,patientNumber,patientAddress);
                    myRef.child(mdid).setValue(helper);
                    progressBar1.setVisibility(View.INVISIBLE);
                    notification();
                    Toast.makeText(Find_Medicine.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Find_Medicine.this, MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar1.setVisibility(View.INVISIBLE);
                Toast.makeText(Find_Medicine.this,"Upload Failed Please Check Your Network",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n")
                    .setContentText("Data Uploaded")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setAutoCancel(true)
                    .setContentText("New Data Is Uploaded");
            NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
            managerCompat.notify(999,builder.build());
        }
    }

    private String getFileExtension(Uri selectedImage) {
        ContentResolver contentResolver=this.getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(selectedImage));
    }

}