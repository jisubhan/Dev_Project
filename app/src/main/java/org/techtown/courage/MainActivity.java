package org.techtown.courage;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
   /* private NoticeListAdapter adapter;
    private List<Notice> noticeList;*/
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment_menu1 fragment_menu1;
    Activity activity1;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userID = getIntent().getStringExtra("userID");

        TextView wbtn = (TextView)findViewById(R.id.wbtn);
        wbtn.setText(userID);

        /*noticeList = new ArrayList<Notice>();
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        fragment1 = new Fragment1();
        //fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment_menu1 = new Fragment_menu1();



        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("전화번호부"));
        //tabs.addTab(tabs.newTab().setText("할 일"));
        tabs.addTab(tabs.newTab().setText("달력"));


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment1;
                } /*else if (position == 1) {
                    selected = fragment_menu1;
                }*/ else if (position == 1) {
                    selected = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
