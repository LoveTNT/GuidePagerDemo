package com.zhy.guidepagerlib;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GuideContoler {
	private Context mContext;
	private ViewPager mViewPager;
	/**ViewPagerҪ��ʾ����ͼ����**/
	private List<View> mViews;
	private GuideViewPagerAdapter mPagerAdapter;
	/**װָʾ����LinearLayout**/
	private LinearLayout mIndicatorGroup;
	/**ָʾ���ļ���**/
	private View[] indicators;
	/**��ָʾ��ѡ�����ʱ��Ĭ�ϵĿ�**/
	private static final int INDICATOR_WIDTH_FOR_RECT = 40;
	/**��ָʾ��ѡ�����ʱ��Ĭ�ϵĸ�**/
	private static final int INDICATOR_HEIGHT_FOR_RECT = 5;
	/**��ָʾ��ѡ��Բ��ʱ��Ĭ�ϵĿ�**/
	private static final int INDICATOR_WIDTH_FOR_OVAL = 25;
	/**��ָʾ��ѡ��Բ��ʱ��Ĭ�ϵĸ�**/
	private static final int INDICATOR_HEIGHT_FOR_OVAL = 25;
	
	/**ָʾ���Ŀ�**/
	private int mIndicatorWidth ;
	/**ָʾ���ĸ�**/
	private int mIndicatorHeight;
	
	private int mIndicatorBgResForSelected;
	private int mIndicatorBgResForUnselected;
	/**ָʾ��Ĭ��ΪԲ��**/
	private ShapeType mShapeType;
	/**
	 * @param context
	 */
	public GuideContoler(Context context) {
		super();
		this.mContext = context;
		
	}
	/***
	 * ��������,������ǰ��ҳ����ͼƬ�����һ��ҳ����һ��layout����
	 * @param imgIds ͼƬ��id����
	 * @param view
	 */
	public void init(int[] imgIds,View view){
		mViews = new ArrayList<View>();
		for (int i = 0; i < imgIds.length; i++) {
			ImageView iv = new ImageView(mContext);
			iv.setImageResource(imgIds[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			mViews.add(iv);
		}
		mViews.add(view);
		set();
	}
	/**��������,��ҳ����ͼȫ����layout������ʱ������**/
	public void init(List<View> views){
		mViews = views;
		set();
	}
	
	/**����ViewPager��ָʾ��**/
	private void set(){
		setViewPager();
		setIndicators();
	}
	/**����ViewPager**/
	private void setViewPager() {
		mViewPager = (ViewPager) ((Activity)mContext).findViewById(R.id.viewPager_lib);
		mPagerAdapter = new GuideViewPagerAdapter(mViews);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(pageChangeListener);
	}
	/**����ָʾ��**/
	private void setIndicators() {
		setConfigure(mShapeType);
		mIndicatorGroup = (LinearLayout) ((Activity)mContext).findViewById(R.id.indicatorGroup_lib);
		indicators = new View[mViews.size()];
		LayoutParams params = new LayoutParams(mIndicatorWidth, mIndicatorHeight);
		params.setMargins(0, 0, 15, 0);
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new View(mContext);
			if(i == 0){
				indicators[i].setBackgroundResource(mIndicatorBgResForSelected);
			}else{
				indicators[i].setBackgroundResource(mIndicatorBgResForUnselected);
			}
			indicators[i].setLayoutParams(params);
			mIndicatorGroup.addView(indicators[i]);
		}
	}
	/**����ö�ٲ���������ָʾ���ı����Ϳ��**/
	private void setConfigure(ShapeType shapeType) {
		if(shapeType != null){
			if(shapeType == ShapeType.OVAL){
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL : mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL : mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
			}else if(shapeType == ShapeType.RECT){
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_RECT : mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_RECT : mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_rect;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_rect;
			}
		}else{
			mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL : mIndicatorWidth;
			mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL : mIndicatorHeight;
			mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
			mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
		}
	}
	/**����ViewPager��ҳ��仯**/
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < indicators.length; i++) {
				if(i == arg0){
					indicators[i].setBackgroundResource(mIndicatorBgResForSelected);
				}else{
					indicators[i].setBackgroundResource(mIndicatorBgResForUnselected);
				}
			}
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	/***
	 * ����һ��ö��(����ָ��ָʾ������״)
	 * @author zhy
	 */
	public enum ShapeType{
		RECT,OVAL
	}
	
	public void setmIndicatorWidth(int mIndicatorWidth) {
		this.mIndicatorWidth = mIndicatorWidth;
	}
	public void setmIndicatorHeight(int mIndicatorHeight) {
		this.mIndicatorHeight = mIndicatorHeight;
	}
	public void setmShapeType(ShapeType mShapeType) {
		this.mShapeType = mShapeType;
	}
	
	
}
