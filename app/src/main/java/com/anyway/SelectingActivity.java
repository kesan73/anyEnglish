package com.anyway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SelectingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting);

        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText("「I am just about to + verb infinitive」 is an expression that can be used 「when planning to do something in the very near future」. For example, if a friend calls to ask, “What are you doing now?”, and you were planning to eat lunch just then, you can say, 「I am just about to have lunch.」. Another example is, 「I'm just about to write a note on Facebook.」, which can be used when 「지금 막 페이스북에 글을 쓸려고 했어요.」 " + "\n" + "「I am just about to + 동사원형…」형태로 「아주 가까운 미래에 어떤 일을 하려고 할 때」 쓸 수 있는 표현입니다. 예를 들어 친구에게 전화가 와서 &apos;지금 뭐해?&apos; 라고 묻는다면, 마침 점심을 먹으려고 했을 때에는「I am just about to have lunch.」라고 표현합니다. 다른 예문으로는 「I&apos;m just about to write a note on Facebook.」「지금 막 페이스북에 글을 쓸려고 했어요.」라고 표현할 수도 있습니다.\n");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selecting, menu);
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
}
