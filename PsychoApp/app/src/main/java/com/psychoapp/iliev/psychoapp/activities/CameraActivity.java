package com.psychoapp.iliev.psychoapp.activities;

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

import com.psychoapp.iliev.psychoapp.helpers.BackGroundChanger;
import com.psychoapp.iliev.psychoapp.customs.ProportionalImageView;
import com.psychoapp.iliev.psychoapp.R;

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

        _btn_take_pic.setOnClickListener(cameraAction);
    }

    View.OnClickListener cameraAction = new View.OnClickListener() {
        // on button "btnTackPic" is clicked
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File file = new File(Environment.getExternalStorageDirectory(), "my-psych-photo.jpg");
            Uri photoPath = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);

            startActivityForResult(intent, TAKE_PICTURE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && intent != null) {

            Bundle extras = intent.getExtras();

            bitMap = (Bitmap) extras.get("data");
            _ivThumbnailPhoto.setImageBitmap(bitMap);
        }
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private boolean hasDefualtCameraApp(String action) {
        final PackageManager packageManager = getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;
    }
}
