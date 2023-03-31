package com.prerana.finalyearproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button button;
    private static final int PERMISSION_CODE =1234;
    Uri image_uri;
    private static final int CAPTURE_CODE =1001 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bottomNavigationView = findViewById(R.id.bottom_navigatior);
//        bottomNavigationView.setSelectedItemId(R.id.camera);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.camera:
//                        startActivity(new Intent(getApplicationContext(), CameraActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//            }
//        });

        ImageCarousel carousel = findViewById(R.id.carousel);

        //create list to store carousel image
        ArrayList<CarouselItem> clist = new ArrayList<>();
        clist.add(new CarouselItem(
                "https://nithinorganics.com/wp-content/uploads/2021/04/Amruthaballi.jpg",
                "Amruthballi"
        ));
        clist.add(new CarouselItem(
                "https://us.123rf.com/450wm/nipapornnan/nipapornnan1803/nipapornnan180300059/96861578-green-piper-betle-leaf-on-white-background.jpg?ver=6",
                "Betal"
        ));
        clist.add(new CarouselItem(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4IY_TTa9OnER0eyVwFDif2qWl2Vl0PTP9Cg&usqp=CAU",
                "Brahmi"
        ));
        clist.add(new CarouselItem(
                "data:image/jpeg;base64,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",
                "Doddapatra"
        ));

        clist.add(new CarouselItem(
                "https://i0.wp.com/www.easyayurveda.com/wp-content/uploads/2014/11/long-pepper-leaf.jpg?resize=450%2C360&ssl=1",
                "Hipli"
        ));
        clist.add(new CarouselItem(

                "https://img.freepik.com/premium-photo/fresh-mint-leaves-peppermint-isolated-white-background_158502-322.jpg?w=2000",
                "Mint"
        ));
        clist.add(new CarouselItem(
                "https://t3.ftcdn.net/jpg/01/06/64/96/360_F_106649673_6CQ9YD900RdxIBT3j7dZHQXXhSA78bg4.jpg",
                "Neem"
        ));clist.add(new CarouselItem(
                "https://5.imimg.com/data5/CF/VS/MY-13512250/harsingar-leaves-parijat-nyctanthes-arbor-tristis-500x500.jpg",
                "Parijat"
        ));
        clist.add(new CarouselItem(
                "data:image/jpeg;base64,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",
                "Peepal"
        ));
        clist.add(new CarouselItem(
                "https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/1296x728_Holy_Basil.jpg?w=1155&h=1528",
                "Tulsi"
        ));


        carousel.setData(clist);


        // bottom nav

        button =findViewById(R.id.bottom_navigator);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA)==
                            PackageManager.PERMISSION_DENIED||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                    PackageManager.PERMISSION_DENIED) {

                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }else {
                        openCamera();
                    }
                }else{
                    openCamera();
                }
            }
        });
    }
    private void openCamera(){

        ContentValues values= new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"new image");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent caminent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        caminent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(caminent,CAPTURE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_CODE:
                if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }





    }
}