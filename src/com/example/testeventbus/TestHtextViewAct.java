package com.example.testeventbus;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class TestHtextViewAct extends Activity {
	
	   String[] sentences = new String[]{"What is design?", "Design", "Design is not just", "what it looks like", "and feels like.", "Design", "is how it works.", "- Steve Jobs", "Older people", "sit down and ask,", "'What is it?'", "but the boy asks,", "'What can I do with it?'.", "- Steve Jobs", "Swift", "Objective-C", "iPhone", "iPad", "Mac Mini", "MacBook Pro", "Mac Pro", "������", "���ź�Ů��"};
	    private int mCounter = 10;
	    private HTextView hTextView;
	    private RemoteImageView rg;
	    private SeekBar seekBar;
	    private RadioGroup radioGroup;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.test_htextview);
	        hTextView = (HTextView) findViewById(R.id.text2);
	        rg = (RemoteImageView) findViewById(R.id.reimag);
	        rg.setImageUrl("", 200, 400);
	        seekBar = (SeekBar) findViewById(R.id.seekbar);
	        seekBar.setMax(20);
	        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	                hTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8 + progress);
	                hTextView.reset(hTextView.getText());
	            }

	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {

	            }

	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {

	            }
	        });
	        
	        seekBar.setProgress(10);

	        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);
	        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	            @Override
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch (checkedId) {
	                    case R.id.scale:
	                        hTextView.setTextColor(Color.BLACK);
	                        hTextView.setBackgroundColor(Color.WHITE);
	                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/Lato-Black.ttf"));
	                        hTextView.setAnimateType(HTextViewType.SCALE);
	                        break;
	                    case R.id.evaporate:
	                        hTextView.setTextColor(Color.BLACK);
	                        hTextView.setBackgroundColor(Color.WHITE);
	                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/PoiretOne-Regular.ttf"));
	                        hTextView.setAnimateType(HTextViewType.EVAPORATE);
	                        break;
	                    case R.id.fall:
	                        hTextView.setTextColor(Color.BLACK);
	                        hTextView.setBackgroundColor(Color.WHITE);
	                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/Mirza-Regular.ttf"));
	                        hTextView.setAnimateType(HTextViewType.FALL);
	                        break;
	                    case R.id.pixelate:
	                        hTextView.setTextColor(Color.BLACK);
	                        hTextView.setBackgroundColor(Color.WHITE);
	                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/AmaticaSC-Regular.ttf"));
	                        hTextView.setAnimateType(HTextViewType.PIXELATE);
	                        break;
	                    case R.id.sparkle:
	                        hTextView.setTextColor(Color.WHITE);
	                        hTextView.setBackgroundColor(Color.BLACK);
	                        hTextView.setTypeface(null);
	                        hTextView.setAnimateType(HTextViewType.SPARKLE);
	                        break;
	                    case R.id.anvil:
	                        hTextView.setTextColor(Color.WHITE);
	                        hTextView.setBackgroundColor(Color.BLACK);
	                        hTextView.setTypeface(null);
	                        hTextView.setAnimateType(HTextViewType.ANVIL);
	                        break;
	                    case R.id.line:
	                        hTextView.setTextColor(Color.WHITE);
	                        hTextView.setBackgroundColor(Color.BLACK);
	                        hTextView.setTypeface(null);
	                        hTextView.setAnimateType(HTextViewType.LINE);
	                        break;
	                    case R.id.typer:
	                        hTextView.setTextColor(Color.WHITE);
	                        hTextView.setBackgroundColor(Color.BLACK);
	                        hTextView.setTypeface(null);
	                        hTextView.setAnimateType(HTextViewType.TYPER);
	                        break;
	                    case R.id.rainbow:
	                        hTextView.setTextColor(Color.WHITE);
	                        hTextView.setBackgroundColor(Color.BLACK);
	                        hTextView.setTypeface(null);
	                        hTextView.setAnimateType(HTextViewType.RAINBOW);
	                        break;
	                }

	                onClick(radioGroup.findViewById(checkedId));
	            }
	        });
	    }

	    public void onClick(View v) {
	        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
	        hTextView.animateText(sentences[mCounter]);
	    }
	

}
