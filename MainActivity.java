package android.example.com.dominosscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int homeWins=0;
    private int visitorWins=0;
    private int homeScore=0;
    private int visitorScore=0;
    private int subtotal=0;
    private TextView textHomeScore;
    private TextView textVisitorScore;
    private TextView textHomeWins;
    private TextView textVisitorWins;
    private TextView textSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);
        textHomeScore = findViewById (R.id.textHomePoints);
        textVisitorScore = findViewById (R.id.textVisitorPoints);
        textHomeWins = findViewById (R.id.textHomeWins);
        textVisitorWins = findViewById (R.id.textVisitorWins);
        textSubtotal =  findViewById (R.id.textSubtotal);
    }

    public void addPoints(View view){
        try {
            String tag = (String) view.getTag();
            //textSubtotal.setText("debug tag:" + tag);
            int command = Integer.valueOf(tag);
            int op = command / 10;
            //textSubtotal.setText("debug tag:" + tag+" op: "+op+"");
            int score = command % 10;
            switch (op) {
                case 1: {
                    subtotal += score;
                    updateScores();
                    break;
                }
                //30=clear
                case 3: {
                    subtotal = 0;
                    updateScores();
                    break;
                }
                //40->home
                case 4: {
                    homeScore += subtotal;
                    subtotal=0;
                    if (homeScore >= 100) {
                        homeScore = 0;
                        visitorScore = 0;
                        homeWins++;
                    }
                    updateScores();
                    break;
                }
                //50->points go to visitor
                case 5: {
                    visitorScore += subtotal;
                    subtotal=0;
                    if (visitorScore >= 100) {
                        homeScore = 0;
                        visitorScore = 0;
                        visitorWins++;
                    }
                    updateScores();
                    break;
                }
                case 6:{
                    homeWins=0;
                    visitorWins=0;
                    homeScore=0;
                    visitorScore=0;
                    subtotal=0;
                    updateScores();
                    break;
                }
            }
        }catch (Exception e){
            textSubtotal =  findViewById (R.id.textSubtotal);
            textSubtotal.setText(e.getMessage());
        }
    }

    public void updateScores(){
            textHomeScore.setText (homeScore+"");
            textVisitorScore.setText (visitorScore+"");
            textHomeWins.setText (homeWins+"");
            textVisitorWins.setText (visitorWins+"");
            textSubtotal.setText(subtotal+" ");
    }
}
