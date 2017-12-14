package org.techtown.courage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by jisub on 2017-12-01.
 */

public class Fragment_menu1 extends Fragment {
    private View m_View;
    private EditText m_EditText;
    private ListView m_ListView;
    private ArrayAdapter<String>    m_Adapter;

    public Fragment_menu1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_View = inflater.inflate(R.layout.fragment_1, container, false);

        // 단어장 이름을 입력받을 EditText.
        m_EditText = (EditText) m_View.findViewById(R.id.edit_wordbook);
        m_Adapter = new ArrayAdapter<String>(m_View.getContext(), android.R.layout.simple_list_item_1);
        m_ListView = (ListView) m_View.findViewById(R.id.list_wordbook);
        m_ListView.setAdapter(m_Adapter);
        // ListView 이벤트 등록.
        m_ListView.setOnItemLongClickListener(onItemLongClickEvent);
        // 단어장 추가 버튼.
        Button btn = (Button) m_View.findViewById(R.id.btn_add_wordbook);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if ( m_EditText.getText().equals("") ) {
                    return;
                }

                m_Adapter.add( m_EditText.getText().toString() );

                m_Adapter.notifyDataSetChanged();

            }
        });
        /*btn.setOnClickListener(onClickAddWordBook);*/

        // ListView.





        return m_View;
    }


    // ListView 아이템을 길게 터치하면 발생하는 이벤트.
    OnItemLongClickListener onItemLongClickEvent = new OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // 롱 클릭 이벤트 발생 시 옵션 다이얼로그를 띄운다.
            ShowMenu(m_Adapter.getItem(arg2));
            return true;
        }
    };

    // 단어장을 길게 터치했을때 단어장을 삭제 할 것인지 묻는 팝업창을 출력한다.
    private void ShowMenu(final String _wordbook) {
        AlertDialog.Builder alert = new AlertDialog.Builder(m_View.getContext());
        alert.setTitle("메뉴");
        alert.setMessage(_wordbook + " 단어장을 삭제 하시겠습니까?");
        alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 삭제 선택 시 해당 단어장을 제거한다.
                m_Adapter.remove(_wordbook);
                m_Adapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}

