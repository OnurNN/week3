package onur.example.week3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {
    ImageView Imageview;
    TextView  TxtMessage;
    ImageButton btnOK;
    ImageButton btnCANCEL;
    static final int CAPTURE_IMAGE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TxtMessage =findViewById(R.id.txtMessage);
        Imageview =findViewById(R.id.ImageView);

        Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent,CAPTURE_IMAGE);
            }
        });
        btnOK=findViewById(R.id.btnOk);
        btnCANCEL=findViewById(R.id.btnCancel);
         btnOK.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent =new Intent();
                 Bundle bundle =new Bundle();
                 bundle.getCharSequence("msg",TxtMessage.getText());
                 bundle.putParcelable("bitmap",
                         ((BitmapDrawable)Imageview.getDrawable()).getBitmap());
                 intent.putExtras(bundle);
                 setResult(Activity.RESULT_OK);
                 finish();
             }
         });

                 btnCANCEL.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         setResult(Activity.RESULT_CANCELED);
                         finish();
                     }
                 });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            Imageview.setImageBitmap(image);
        }


    }
}