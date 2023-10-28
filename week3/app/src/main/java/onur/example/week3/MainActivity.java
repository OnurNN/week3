package onur.example.week3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    List<Post> postList =new ArrayList<Post>();
    Button btnPost;
    static final int POST_REQUEST=3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);

        PostAdapter adapter=new PostAdapter(this, postList);
        listView.setAdapter(adapter);

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PostActivity.class);
                startActivityForResult(intent,POST_REQUEST);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POST_REQUEST && resultCode == RESULT_OK) {
            Post post = new Post();
            post.setMessage(data.getCharSequenceExtra("msg").toString());
            post.setImage(data.getParcelableExtra("bitmap"));
            postList.add(post);
            ((PostAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }



}