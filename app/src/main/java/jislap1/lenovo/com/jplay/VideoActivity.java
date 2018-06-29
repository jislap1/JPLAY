package jislap1.lenovo.com.jplay;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = (VideoView) findViewById(R.id.vviewhobbit);
        String vidAddress = "https://www3307.playercdn.net/185/1/G4enxfzWvitcOhIo7YbsdA/1530247795/180430/719FQV51C2555KZINNUFP.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        videoView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);
        videoView.start();

    }
}
