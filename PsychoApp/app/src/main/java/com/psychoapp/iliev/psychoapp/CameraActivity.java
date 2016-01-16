package com.psychoapp.iliev.psychoapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.psychoapp.iliev.psychoapp.dummy.Helpers.BackGroundChanger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity {

    @Bind(R.id.btnTakePic) Button _btn_take_pic;
    @Bind(R.id.ivThumbnailPhoto) ImageView _ivThumbnailPhoto;
    @Bind(R.id.background_image) ProportionalImageView _background;

    Bitmap bitMap;
    static int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

        // add onclick listener to the button
        _btn_take_pic.setOnClickListener(cameraAction);
    }


    View.OnClickListener cameraAction = new View.OnClickListener() {
        // on button "btnTackPic" is clicked
        @Override
        public void onClick(View view) {

            // create intent with ACTION_IMAGE_CAPTURE action
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // this part to save captured image on provided path
            File file = new File(Environment.getExternalStorageDirectory(), "my-psych-photo.jpg");
            Uri photoPath = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);

            // start camera activity
            startActivityForResult(intent, TAKE_PICTURE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get(MediaStore.EXTRA_OUTPUT);
            _ivThumbnailPhoto.setImageBitmap(bitMap);

        }
    }

    // method to check if you have a Camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    // method to check you have Camera Apps
    private boolean hasDefualtCameraApp(String action){
        final PackageManager packageManager = getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;
    }
}
