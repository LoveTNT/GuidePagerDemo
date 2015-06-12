package com.zhy.guidepagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zhy.guidepagerlib.GuideContoler;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    /**ʹ��д�õĿ��ʼ������ҳ��**/
	private void initViewPager() {
		GuideContoler contoler = new GuideContoler(this);
//		contoler.setmShapeType(ShapeType.RECT);//����ָʾ������״Ϊ���Σ�Ĭ����Բ��
		int[] imgIds = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.pager_four,null);
		contoler.init(imgIds, view);
		view.findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "����", Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
