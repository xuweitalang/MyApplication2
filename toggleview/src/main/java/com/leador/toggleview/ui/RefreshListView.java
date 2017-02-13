package com.leador.toggleview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leador.toggleview.R;


/**
 * Created by xuwei on 2016/12/18.
 */

public class RefreshListView extends ListView {
    private View headerView;
    private float moveY;
    private float downY;
    public static final int PULL_REFRESH = 0; //下拉刷新
    public static final int RELEASE_REFRESH = 1; //释放刷新
    public static final int REFRESHING = 2; //刷新中
    private int currentState = PULL_REFRESH;
    private ImageView ivArrow;
    private TextView tvTitle;
    private TextView tvTime;
    private ProgressBar bar;
    private RotateAnimation rotateUpAnim;
    private RotateAnimation rotateDownAnim;

    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化头布局/脚布局，设置监听
     */
    private void init() {
        initHeadView();
        initAnimation();
    }

    //初始化头布局动画
    private void initAnimation() {
        //向上转围绕自己的中心逆时针旋转180度
        rotateUpAnim =new RotateAnimation(0,-180f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateUpAnim.setDuration(300);
        rotateUpAnim.setFillAfter(true); //动画停留在结束位置
        //向下转围绕自己的中心逆时针旋转180度
        rotateDownAnim =new RotateAnimation(-180f,-360f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateDownAnim.setDuration(300);
        rotateDownAnim.setFillAfter(true); //动画停留在结束位置
    }

    /**
     * 初始化头布局
     */
    private void initHeadView() {
        headerView = View.inflate(getContext(), R.layout.layout_head_listview, null);
        ivArrow = (ImageView)headerView.findViewById(R.id.ivArrow);
        tvTitle = (TextView)headerView.findViewById(R.id.tvTitle);
        bar = (ProgressBar) headerView.findViewById(R.id.progressBar);
        tvTime = (TextView) headerView.findViewById(R.id.tvTime);
        //提前手动测量宽高
        headerView.measure(0, 0); //按照设置的规则测量
        int measureHeight = headerView.getMeasuredHeight(); //获取测量后的高度

        //设置内边距，用于隐藏头布局，设置为头布局的高度
        headerView.setPadding(0, -measureHeight, 0, 0);
        addHeaderView(headerView);
    }

    /**
     * 判断滑动距离，给headView设置PaddingTop
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                float offsetX = moveY - downY; //移动的偏移量

                //只有偏移量大于0，并且第一个可见条目索引为0时才放大头部
                if (offsetX > 0 && getFirstVisiblePosition() == 0) {

//                paddingTop = -自身高度 + 偏移量
                    int paddingTop = (int) (-headerView.getMeasuredHeight() + offsetX);
                    headerView.setPadding(0, paddingTop, 0, 0);
                    if (paddingTop >= 0 && currentState != RELEASE_REFRESH) {   //切换成刷新模式
                        currentState = RELEASE_REFRESH;
                        updateHeader();
                    } else if (paddingTop < 0 && currentState != PULL_REFRESH) { // 切换成下拉刷新模式
                        currentState = PULL_REFRESH;
                        updateHeader();
                    }
                    return true; // 表示当前事件被处理并消费
                }
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }

    //更加最新的状态更新头布局
    private void updateHeader() {
        switch(currentState) {
            case PULL_REFRESH: //切换成下拉刷新
                ivArrow.startAnimation(rotateDownAnim);
                tvTitle.setText("下拉刷新");
                break;
            case RELEASE_REFRESH: // 切换成释放刷新
                //做动画，改标题
                ivArrow.startAnimation(rotateUpAnim);
                tvTitle.setText("释放刷新");
                break;
            case REFRESHING: // 切换中刷新中
                break;
        }
    }
}
