package id.fadhil.resepmama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView splash;
    Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash = (ImageView) findViewById(R.id.iv_splash);
        anim1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        anim2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.antirotate);
        //anim1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.a);

        splash.startAnimation(anim2);
        anim2.setAnimationListener(new Animation.AnimationListener() {

                                       @Override
                                       public void onAnimationRepeat(Animation animation) {

                                       }

                                       @Override
                                       public void onAnimationEnd(Animation animation) {
                                           splash.startAnimation(anim1);
                                       }

                                       @Override
                                       public void onAnimationStart(Animation animation) {

                                       }
                                   });

                anim1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                        Intent i = new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
    }
}
