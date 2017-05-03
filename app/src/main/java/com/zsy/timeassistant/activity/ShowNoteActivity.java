package com.zsy.timeassistant.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsy.timeassistant.R;

public class ShowNoteActivity extends BaseActivity {

    public static final String TEXT_EXTRA = "text_extra";
    public static final String IMAGE_EXTRA = "imageView_extra";


    TextView content;
    ImageView contentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        initToolBar("随身记", true);

        content = (TextView) findViewById(R.id.content);
        content.setText(getIntent().getStringExtra(TEXT_EXTRA));

        contentImage = (ImageView) findViewById(R.id.content_image);
        contentImage.setImageBitmap(
                BitmapFactory.decodeFile(getIntent().getStringExtra(IMAGE_EXTRA)));
    }
}
