package romz.luannguyen.getfirebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import romz.luannguyen.getfirebase.R;

public class ThongTinActivity extends AppCompatActivity {

    private TextView mContent, mTitel;
    private ImageView imvHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDulieu();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_thongtin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_down:
                break;
            case R.id.action_copy:
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    public void getDulieu(){
        mContent= (TextView)findViewById(R.id.txtContent);
        mTitel = (TextView)findViewById(R.id.txtTitle);
        imvHinh = (ImageView)findViewById(R.id.imvHinh);

        Intent intt = getIntent();
        Log.d("---rom---","B=========>");

        String imvhinh = intt.getStringExtra("url");
        String content=intt.getStringExtra("content");
        String titel=intt.getStringExtra("titel");
        Glide.with(this).load(imvhinh)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imvHinh);
        mContent.setText(content);
        mTitel.setText(titel);
    }


}
