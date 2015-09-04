package org.sayor.instagramclient;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;


public class SpeakingActivity extends ActionBarActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech mytts;
    private int MY_DATA_CHECK_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);
        Intent checkTTSintent = new Intent();
        checkTTSintent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSintent,MY_DATA_CHECK_CODE);

        Speak("Sathish");
    }

    private void Speak(String test) {
        mytts.speak(test, TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MY_DATA_CHECK_CODE){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
                mytts = new TextToSpeech(this,this);
        }
        else{
            Intent installTTS = new Intent();
            installTTS.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installTTS);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speaking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            if(mytts.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                mytts.setLanguage(Locale.US);
        }
            else if (status == TextToSpeech.ERROR){
                Toast.makeText(this,"Sorry, error in processing speech",Toast.LENGTH_LONG).show();
        }
    }
}
