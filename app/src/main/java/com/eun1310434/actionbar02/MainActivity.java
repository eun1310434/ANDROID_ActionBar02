/*=====================================================================
06.03.2018
eun1310434@naver.com
https://blog.naver.com/eun1310434
참고) Do it android app programming
=====================================================================*/
package com.eun1310434.actionbar02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        View v = menu.findItem(R.id.menu_search).getActionView(); // menu_item의 menu_search 갖고 옮
        if (v != null) {
            editText = (EditText) v.findViewById(R.id.editText);
            if (editText != null) {
                editText.setOnEditorActionListener(onSearchListener); // 키입력 리스너를 설정하여 완료 버튼(KeyEvent.ACTION_UP)을 누름 활용
            }
        } else {
            textView.setText("ActionView is null.");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //옵션메뉴 선택
        switch(item.getItemId()) {
            case R.id.menu_refresh:
                textView.setText("Refresh Menu Choice : ");
                break;
            case R.id.menu_search:
                textView.setText("Search Menu Choice : ");
                break;
            case R.id.menu_settings:
                textView.setText("Search Settings Choice : ");
                break;
            default:
                break;
        }
        textView.append(Integer.toString(item.getOrder()));// item.getOrder() -> XML의 "android:orderInCategory"를 말함
        return super.onOptionsItemSelected(item);
    }


    //키 입력 리스너
    private TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            if (event == null || event.getAction() == KeyEvent.ACTION_UP) { //KeyEvent.ACTION_UP <-완료버튼 누름
                // 검색 메소드 호출
                search();

                // 키패드 닫기
                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

            return (true);
        }
    };

    //검색 메소드
    private void search() {
        String searchStr = editText.getEditableText().toString();
        textView.setText(searchStr);
    }

}
