package com.giftextview.demo;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.giftextview.R;
import com.giftextview.demo.ParseUtil.ViewDataEntity;
import com.giftextview.entity.SpanEntity;
import com.giftextview.span.OnTextSpanClickListener;
import com.giftextview.view.GifTextView;
import com.giftextview.view.GifTextViewHelper;

public class MainActivity extends Activity {

	private int mScreenWidth = 0;
	private TextSpanClickListener mListener = null;
	private GifTextViewHelper mHelper = null;
	private ArrayList<ViewDataEntity> mDataList = null;
	private Handler mHandler = null;
	private int mGroupIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		mListener = new TextSpanClickListener();
		mHelper = GifTextViewHelper.getInstance(this, mScreenWidth,
				mScreenWidth);
		mHandler = new Handler();

		test2();

	}

	@Override
	protected void onResume() {
		super.onResume();
		mHelper.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mHelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDataList.clear();
		mHelper.onDestroy();
		mHandler.removeCallbacksAndMessages(null);
	}

	public void showDialog(View v) {
		Intent intent = new Intent(this, DialogActivity.class);
		startActivity(intent);
	}

	// *************************************************************************
	// 测试1：单个GifTextView

	private void test1() {

		String data = "捕获Android文本我们需要在ClickSpan的onClick方法中加入自己的控制逻辑，"
				+ "<><2$http://192.168.191.1:8080/WebTest/11.gif><>本文将一个超级"
				+ "<><2$http://192.168.191.1:8080/WebTest/12.gif><>找到你所找, 得到你所想<><1$http://baidu.com/><百度><>"
				+ "totalMemory()这个方法返回的是java虚拟机现在已经从操作系统那里挖过来的内存大小，也就是java虚拟机这个进程当时所占用的所有 内存。如果在运行java的时候没有添加-Xms参数，那"
				+ "<><6$http://192.168.191.1:8080/WebTest/22.rar><>直挖到maxMemory()为止，"
				+ "<><2$http://192.168.191.1:8080/WebTest/13.gif><>所以totalMemory()是慢慢增大的。如果用了-Xms参数，程序在启动"
				+ "<><1$http://192.168.191.1:8080/WebTest/2214.gif><百度知道><>freeMemory()是什么呢，刚才讲到如果在运行java的时候没有添加-Xms参数，那么，在java程"
				+ "<><2$http://192.168.191.1:8080/WebTest/14.gif><><><2$http://192.168.191.1:8080/WebTest/15.gif><>"
				+ "&lt;&gt;&lt;&gt;&lt;&gt;&nbsp; &lt;&gt;&nbsp;<><2$http://192.168.191.1:8080/WebTest/16.gif><>"
				+ " &lt;&gt; &lt;&gt;&nbsp; * @ !打扫打扫打扫&nbsp; 打扫打扫大大松大苏打《》&lt;&gt;das&lt;&gt;"
				+ " 打扫打扫大苏打阿大使大大 asdaｄａｓａｓｄｄａｓｄ２大苏打<><2$http://192.168.191.1:8080/WebTest/17.gif><>"
				+ "　ａｄａｓｄｄａｓｄａｓｄａｓｄ大苏打大苏打．＜＞＜＞＜＞大苏打大苏打２３１３３１２８＆％×％……％＃565%&amp;……&amp;"
				+ "￥￥（*（￥￥%６７６￥＠＃＠！大赛<><-1$mailto:sd!@$#%大大松%^%<><>&amp;*$%$><<>&amp;*$%$\""
				+ " target=_blank>sd!@$#%大大松%^%&lt;&gt;&lt;&gt;&amp;<><2$http://192.168.191.1:8080/WebTest/18.gif><>"
				+ "*$%$><>#@!@!*&amp;&amp;&lt;&gt;&lt;&gt;《》，．＜＞；dasdad大苏打大撒大大"
				+ "<><2$http://192.168.191.1:8080/WebTest/19.gif><>序运行的过程的，内存总是慢慢的从操作系统那里挖的，"
				+ "基本上是用多少挖多少，但是java虚拟机100％的情况下是会稍微多挖一点的，这些挖过来而又没有用上的内存，"
				+ "实际上就是 freeMemory()，<><2$http://192.168.191.1:8080/WebTest/20.gif><><><2$http://192.168.191.1:8080/WebTest/22.gif><>"
				+ "所以freeMemory()的值一般情况下都是很小的，但是如果你在运行java程序的时候使用了-Xms，这个时候因为程序在启动的时候就会无条件的从操作系统中挖-Xms后面定义的内存数，"
				+ "这个时候，<><2$http://192.168.191.1:8080/WebTest/111.jpg><><><2$http://192.168.191.1:8080/WebTest/21.gif><>挖过来的内存可能大部分没用上，所以这个时候freeMemory()可能会有些大。";

		mDataList = ParseUtil.parsing(data);
		ViewDataEntity dataEntity = mDataList.get(0);

		GifTextView gifTextView = (GifTextView) findViewById(R.id.gifTextView);
		gifTextView.setVisibility(View.VISIBLE);
		gifTextView.initView(mGroupIndex, 1, dataEntity.text,
				dataEntity.spanList, mScreenWidth, mScreenWidth, mListener);
	}

	// *************************************************************************
	// 测试2：放入ListView中

	private void test2() {

		String data1=" <ul class='rl_exp_main clearfix rl_selected'>\n" +
				"                        <li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/geili_thumb.gif\" alt=\"给力\" title=\"给力\" name=\"df_给力\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/wg_thumb.gif\" alt=\"围观\" title=\"围观\" name=\"df_围观\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/vw_thumb.gif\" alt=\"威武\" title=\"威武\" name=\"df_威武\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/smilea_thumb.gif\" alt=\"呵呵\" title=\"呵呵\" name=\"df_呵呵\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/tootha_thumb.gif\" alt=\"嘻嘻\" title=\"嘻嘻\" name=\"df_嘻嘻\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/laugh.gif\" alt=\"哈哈\" title=\"哈哈\" name=\"df_哈哈\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/tza_thumb.gif\" alt=\"可爱\" title=\"可爱\" name=\"df_可爱\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/kl_thumb.gif\" alt=\"可怜\" title=\"可怜\" name=\"df_可怜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/kbsa_thumb.gif\" alt=\"挖鼻屎\" title=\"挖鼻屎\" name=\"df_挖鼻屎\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/cj_thumb.gif\" alt=\"吃惊\" title=\"吃惊\" name=\"df_吃惊\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/shamea_thumb.gif\" alt=\"害羞\" title=\"害羞\" name=\"df_害羞\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/zy_thumb.gif\" alt=\"挤眼\" title=\"挤眼\" name=\"df_挤眼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/bz_thumb.gif\" alt=\"闭嘴\" title=\"闭嘴\" name=\"df_闭嘴\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/bs2_thumb.gif\" alt=\"鄙视\" title=\"鄙视\" name=\"df_鄙视\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/lovea_thumb.gif\" alt=\"爱你\" title=\"爱你\" name=\"df_爱你\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sada_thumb.gif\" alt=\"泪\" title=\"泪\" name=\"df_泪\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/heia_thumb.gif\" alt=\"偷笑\" title=\"偷笑\" name=\"df_偷笑\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/qq_thumb.gif\" alt=\"亲亲\" title=\"亲亲\" name=\"df_亲亲\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sb_thumb.gif\" alt=\"生病\" title=\"生病\" name=\"df_生病\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/mb_thumb.gif\" alt=\"太开心\" title=\"太开心\" name=\"df_太开心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/ldln_thumb.gif\" alt=\"懒得理你\" title=\"懒得理你\" name=\"df_懒得理你\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/yhh_thumb.gif\" alt=\"右哼哼\" title=\"右哼哼\" name=\"df_右哼哼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/zhh_thumb.gif\" alt=\"左哼哼\" title=\"左哼哼\" name=\"df_左哼哼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/x_thumb.gif\" alt=\"嘘\" title=\"嘘\" name=\"df_嘘\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/cry.gif\" alt=\"衰\" title=\"衰\" name=\"df_衰\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/wq_thumb.gif\" alt=\"委屈\" title=\"委屈\" name=\"df_委屈\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/t_thumb.gif\" alt=\"吐\" title=\"吐\" name=\"df_吐\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/k_thumb.gif\" alt=\"打哈欠\" title=\"打哈欠\" name=\"df_打哈欠\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/bba_thumb.gif\" alt=\"抱抱\" title=\"抱抱\" name=\"df_抱抱\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/angrya_thumb.gif\" alt=\"怒\" title=\"怒\" name=\"df_怒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/yw_thumb.gif\" alt=\"疑问\" title=\"疑问\" name=\"df_疑问\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/cza_thumb.gif\" alt=\"馋嘴\" title=\"馋嘴\" name=\"df_馋嘴\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/88_thumb.gif\" alt=\"拜拜\" title=\"拜拜\" name=\"df_拜拜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sk_thumb.gif\" alt=\"思考\" title=\"思考\" name=\"df_思考\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sweata_thumb.gif\" alt=\"汗\" title=\"汗\" name=\"df_汗\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sleepya_thumb.gif\" alt=\"困\" title=\"困\" name=\"df_困\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sleepa_thumb.gif\" alt=\"睡觉\" title=\"睡觉\" name=\"df_睡觉\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/money_thumb.gif\" alt=\"钱\" title=\"钱\" name=\"df_钱\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/sw_thumb.gif\" alt=\"失望\" title=\"失望\" name=\"df_失望\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/cool_thumb.gif\" alt=\"酷\" title=\"酷\" name=\"df_酷\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/hsa_thumb.gif\" alt=\"花心\" title=\"花心\" name=\"df_花心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/hatea_thumb.gif\" alt=\"哼\" title=\"哼\" name=\"df_哼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/gza_thumb.gif\" alt=\"鼓掌\" title=\"鼓掌\" name=\"df_鼓掌\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/dizzya_thumb.gif\" alt=\"晕\" title=\"晕\" name=\"df_晕\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/bs_thumb.gif\" alt=\"悲伤\" title=\"悲伤\" name=\"df_悲伤\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/crazya_thumb.gif\" alt=\"抓狂\" title=\"抓狂\" name=\"df_抓狂\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/h_thumb.gif\" alt=\"黑线\" title=\"黑线\" name=\"df_黑线\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/yx_thumb.gif\" alt=\"阴险\" title=\"阴险\" name=\"df_阴险\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/nm_thumb.gif\" alt=\"怒骂\" title=\"怒骂\" name=\"df_怒骂\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/hearta_thumb.gif\" alt=\"心\" title=\"心\" name=\"df_心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/unheart.gif\" alt=\"伤心\" title=\"伤心\" name=\"df_伤心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/ok_thumb.gif\" alt=\"ok\" title=\"ok\" name=\"df_ok\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/ye_thumb.gif\" alt=\"耶\" title=\"耶\" name=\"df_耶\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/df/good_thumb.gif\" alt=\"good\" title=\"good\" name=\"df_good\" width=\"22px\" height=\"22px\" />\n" +
				"                </li>        </ul>            <ul class='rl_exp_main clearfix' style=\"display:none;\">            <li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb0.gif\" alt=\"哟西\" title=\"哟西\" name=\"pb_min_哟西\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb1.gif\" alt=\"捏脸\" title=\"捏脸\" name=\"pb_min_捏脸\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb2.gif\" alt=\"委屈\" title=\"委屈\" name=\"pb_min_委屈\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb3.gif\" alt=\"伤心\" title=\"伤心\" name=\"pb_min_伤心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb4.gif\" alt=\"喵呜\" title=\"喵呜\" name=\"pb_min_喵呜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb5.gif\" alt=\"娇羞\" title=\"娇羞\" name=\"pb_min_娇羞\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb6.gif\" alt=\"开枪\" title=\"开枪\" name=\"pb_min_开枪\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb7.gif\" alt=\"哇哦\" title=\"哇哦\" name=\"pb_min_哇哦\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb8.gif\" alt=\"无语\" title=\"无语\" name=\"pb_min_无语\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb9.gif\" alt=\"鄙视\" title=\"鄙视\" name=\"pb_min_鄙视\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb10.gif\" alt=\"难吃\" title=\"难吃\" name=\"pb_min_难吃\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb11.gif\" alt=\"亲亲\" title=\"亲亲\" name=\"pb_min_亲亲\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb12.gif\" alt=\"流口水\" title=\"流口水\" name=\"pb_min_流口水\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb13.gif\" alt=\"巨汗\" title=\"巨汗\" name=\"pb_min_巨汗\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb14.gif\" alt=\"开心\" title=\"开心\" name=\"pb_min_开心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb15.gif\" alt=\"吐\" title=\"吐\" name=\"pb_min_吐\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb16.gif\" alt=\"飘过\" title=\"飘过\" name=\"pb_min_飘过\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb17.gif\" alt=\"吃饭\" title=\"吃饭\" name=\"pb_min_吃饭\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb18.gif\" alt=\"打死你\" title=\"打死你\" name=\"pb_min_打死你\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb19.gif\" alt=\"路过\" title=\"路过\" name=\"pb_min_路过\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb20.gif\" alt=\"烧香\" title=\"烧香\" name=\"pb_min_烧香\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb21.gif\" alt=\"吓\" title=\"吓\" name=\"pb_min_吓\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb22.gif\" alt=\"好喜欢\" title=\"好喜欢\" name=\"pb_min_好喜欢\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb23.gif\" alt=\"弹\" title=\"弹\" name=\"pb_min_弹\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb24.gif\" alt=\"拜拜\" title=\"拜拜\" name=\"pb_min_拜拜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb25.gif\" alt=\"晕\" title=\"晕\" name=\"pb_min_晕\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb26.gif\" alt=\"咻\" title=\"咻\" name=\"pb_min_咻\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb27.gif\" alt=\"舔屏\" title=\"舔屏\" name=\"pb_min_舔屏\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb28.gif\" alt=\"瞌睡\" title=\"瞌睡\" name=\"pb_min_瞌睡\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb29.gif\" alt=\"潜水\" title=\"潜水\" name=\"pb_min_潜水\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb30.gif\" alt=\"害怕\" title=\"害怕\" name=\"pb_min_害怕\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb31.gif\" alt=\"闭嘴\" title=\"闭嘴\" name=\"pb_min_闭嘴\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb32.gif\" alt=\"囧\" title=\"囧\" name=\"pb_min_囧\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb33.gif\" alt=\"哇咔咔\" title=\"哇咔咔\" name=\"pb_min_哇咔咔\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb34.gif\" alt=\"么么哒\" title=\"么么哒\" name=\"pb_min_么么哒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb35.gif\" alt=\"别过来\" title=\"别过来\" name=\"pb_min_别过来\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb36.gif\" alt=\"切\" title=\"切\" name=\"pb_min_切\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb37.gif\" alt=\"悠闲\" title=\"悠闲\" name=\"pb_min_悠闲\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb38.gif\" alt=\"哼\" title=\"哼\" name=\"pb_min_哼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb39.gif\" alt=\"津津有味\" title=\"津津有味\" name=\"pb_min_津津有味\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb40.gif\" alt=\"鼓掌\" title=\"鼓掌\" name=\"pb_min_鼓掌\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb41.gif\" alt=\"自残\" title=\"自残\" name=\"pb_min_自残\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb42.gif\" alt=\"呸\" title=\"呸\" name=\"pb_min_呸\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb43.gif\" alt=\"血腥\" title=\"血腥\" name=\"pb_min_血腥\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb44.gif\" alt=\"抠鼻\" title=\"抠鼻\" name=\"pb_min_抠鼻\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb45.gif\" alt=\"佩服\" title=\"佩服\" name=\"pb_min_佩服\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb46.gif\" alt=\"大哭\" title=\"大哭\" name=\"pb_min_大哭\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb47.gif\" alt=\"吃我一拳\" title=\"吃我一拳\" name=\"pb_min_吃我一拳\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb48.gif\" alt=\"噢呵呵\" title=\"噢呵呵\" name=\"pb_min_噢呵呵\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb49.gif\" alt=\"流鼻血\" title=\"流鼻血\" name=\"pb_min_流鼻血\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb50.gif\" alt=\"吸血鬼\" title=\"吸血鬼\" name=\"pb_min_吸血鬼\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb51.gif\" alt=\"中毒\" title=\"中毒\" name=\"pb_min_中毒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb52.gif\" alt=\"爆头\" title=\"爆头\" name=\"pb_min_爆头\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/pb_min/pb53.gif\" alt=\"僵尸\" title=\"僵尸\" name=\"pb_min_僵尸\" width=\"22px\" height=\"22px\" />\n" +
				"                </li>        </ul>            <ul class='rl_exp_main clearfix' style=\"display:none;\">            <li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/00.gif\" alt=\"飞行\" title=\"飞行\" name=\"x_min_飞行\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/01.gif\" alt=\"诅咒\" title=\"诅咒\" name=\"x_min_诅咒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/02.gif\" alt=\"吓\" title=\"吓\" name=\"x_min_吓\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/03.gif\" alt=\"怒\" title=\"怒\" name=\"x_min_怒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/04.gif\" alt=\"生气\" title=\"生气\" name=\"x_min_生气\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/05.gif\" alt=\"吐\" title=\"吐\" name=\"x_min_吐\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/06.gif\" alt=\"爱心\" title=\"爱心\" name=\"x_min_爱心\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/07.gif\" alt=\"愤怒\" title=\"愤怒\" name=\"x_min_愤怒\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/08.gif\" alt=\"倒霉\" title=\"倒霉\" name=\"x_min_倒霉\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/09.gif\" alt=\"目瞪口呆\" title=\"目瞪口呆\" name=\"x_min_目瞪口呆\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/10.gif\" alt=\"得瑟\" title=\"得瑟\" name=\"x_min_得瑟\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/11.gif\" alt=\"旋转\" title=\"旋转\" name=\"x_min_旋转\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/12.gif\" alt=\"汗\" title=\"汗\" name=\"x_min_汗\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/13.gif\" alt=\"感动\" title=\"感动\" name=\"x_min_感动\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/14.gif\" alt=\"拽\" title=\"拽\" name=\"x_min_拽\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/15.gif\" alt=\"Duang\" title=\"Duang\" name=\"x_min_Duang\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/16.gif\" alt=\"可怜\" title=\"可怜\" name=\"x_min_可怜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/17.gif\" alt=\"哦耶\" title=\"哦耶\" name=\"x_min_哦耶\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/18.gif\" alt=\"委屈\" title=\"委屈\" name=\"x_min_委屈\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/19.gif\" alt=\"拜拜\" title=\"拜拜\" name=\"x_min_拜拜\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/20.gif\" alt=\"抓狂\" title=\"抓狂\" name=\"x_min_抓狂\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/21.gif\" alt=\"发火\" title=\"发火\" name=\"x_min_发火\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/22.gif\" alt=\"喜欢\" title=\"喜欢\" name=\"x_min_喜欢\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/23.gif\" alt=\"石化\" title=\"石化\" name=\"x_min_石化\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/24.gif\" alt=\"go\" title=\"go\" name=\"x_min_go\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/25.gif\" alt=\"路过\" title=\"路过\" name=\"x_min_路过\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/26.gif\" alt=\"惊吓\" title=\"惊吓\" name=\"x_min_惊吓\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/27.gif\" alt=\"又发火\" title=\"又发火\" name=\"x_min_又发火\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/28.gif\" alt=\"侠客\" title=\"侠客\" name=\"x_min_侠客\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/29.gif\" alt=\"得意\" title=\"得意\" name=\"x_min_得意\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/30.gif\" alt=\"又生气\" title=\"又生气\" name=\"x_min_又生气\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/31.gif\" alt=\"飘过\" title=\"飘过\" name=\"x_min_飘过\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/32.gif\" alt=\"撒娇\" title=\"撒娇\" name=\"x_min_撒娇\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/33.gif\" alt=\"不好意思\" title=\"不好意思\" name=\"x_min_不好意思\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/34.gif\" alt=\"痛\" title=\"痛\" name=\"x_min_痛\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/35.gif\" alt=\"抠鼻\" title=\"抠鼻\" name=\"x_min_抠鼻\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/36.gif\" alt=\"得瑟到不行\" title=\"得瑟到不行\" name=\"x_min_得瑟到不行\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/37.gif\" alt=\"疑问\" title=\"疑问\" name=\"x_min_疑问\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/38.gif\" alt=\"生病\" title=\"生病\" name=\"x_min_生病\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/39.gif\" alt=\"无语\" title=\"无语\" name=\"x_min_无语\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/40.gif\" alt=\"又路过\" title=\"又路过\" name=\"x_min_又路过\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/41.gif\" alt=\"再次生气\" title=\"再次生气\" name=\"x_min_再次生气\" width=\"22px\" height=\"22px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/x_min/42.gif\" alt=\"兴奋\" title=\"兴奋\" name=\"x_min_兴奋\" width=\"22px\" height=\"22px\" />\n" +
				"                </li>        </ul>            <ul class='rl_exp_main clearfix' style=\"display:none;\">            <li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/jiayou.gif\" alt=\"加油\" title=\"加油\" name=\"my_加油\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/ainiyo.gif\" alt=\"爱你呦\" title=\"爱你呦\" name=\"my_爱你呦\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/bangbangde.gif\" alt=\"棒棒的\" title=\"棒棒的\" name=\"my_棒棒的\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/gandashideren.gif\" alt=\"干大事的人\" title=\"干大事的人\" name=\"my_干大事的人\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/jiexinshangni.gif\" alt=\"姐欣赏你\" title=\"姐欣赏你\" name=\"my_姐欣赏你\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/kuailaizanwo.gif\" alt=\"快来赞我\" title=\"快来赞我\" name=\"my_快来赞我\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/mirendewo.gif\" alt=\"迷人的我\" title=\"迷人的我\" name=\"my_迷人的我\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/nixingnishanga.gif\" alt=\"你行你上啊\" title=\"你行你上啊\" name=\"my_你行你上啊\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/tingbudongdeyangzi.gif\" alt=\"听不懂的样子\" title=\"听不懂的样子\" name=\"my_听不懂的样子\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/my/wanmei.gif\" alt=\"完美\" title=\"完美\" name=\"my_完美\" width=\"60px\" height=\"60px\" />\n" +
				"                </li>        </ul>            <ul class='rl_exp_main clearfix' style=\"display:none;\">            <li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/bbanm.gif\" alt=\"宝宝爱你们\" title=\"宝宝爱你们\" name=\"mh_宝宝爱你们\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/bx.gif\" alt=\"不屑\" title=\"不屑\" name=\"mh_不屑\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/dhsk.gif\" alt=\"多还是空\" title=\"多还是空\" name=\"mh_多还是空\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/hbhm.gif\" alt=\"好不好嘛\" title=\"好不好嘛\" name=\"mh_好不好嘛\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/hkx.gif\" alt=\"好开心\" title=\"好开心\" name=\"mh_好开心\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/ly.gif\" alt=\"来呀\" title=\"来呀\" name=\"mh_来呀\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/xdl.gif\" alt=\"想到了\" title=\"想到了\" name=\"mh_想到了\" width=\"60px\" height=\"60px\" />\n" +
				"                </li><li class=\"rl_exp_item\">\n" +
				"                    <img src=\"http://img.kuaixun360.com/phiz/mh/zhangl.gif\" alt=\"涨了\" title=\"涨了\" name=\"mh_涨了\" width=\"60px\" height=\"60px\" />\n" +
				"                </li>        </ul>\n" +
				"    <a href=\"javascript:void(0);\" class=\"close\"></a>";

	/*	http://img.kuaixun360.com/phiz/mh/zhangl.gif
		http://img.kuaixun360.com/phiz/mh/tingbudongdeyangzi.gif
		http://img.kuaixun360.com/phiz/mh/xdl.gif
		http://img.kuaixun360.com/phiz/mh/hkx.gif
		http://img.kuaixun360.com/phiz/mh/dhsk.gif
		http://img.kuaixun360.com/phiz/mh/bbanm.gif*/
		final String data2 = "<n>【12】.25个Java机器学习工具&库<><2$http://192.168.191.1:8080/WebTest/38.gif><>"
				+ "摘要：本文总结了25个Java机器学习工具&库：Weka集成了数据挖掘工作的机器学习算法、面向数据流挖掘的流行开源框架（MOA）、"
				+ "新型的柔性工作流引擎ADAMS、基于Java的面向文本文件的机器学习工具包Mallet等。<><5$ee_019><><n>"
				+ "【13】.本列表总结了25个Java机器学习工具&库：<><2$http://192.168.191.1:8080/WebTest/39.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/40.gif><>1. Weka集成了数据挖掘工作的机器学习算法。"
				+ "这些算法可以直接应用于一个数据集上或者你可以自己编写代码来调用。Weka包括一系列的工具，如数据预处理、分类、回归、"
				+ "聚类、关联规则以及可视化。<><2$http://192.168.191.1:8080/WebTest/41.gif><><><5$ee_020><><n>"
				+ "【14】.2.Massive Online Analysis（MOA）是一个面向数据流挖掘的流行开源框架，有着非常活跃的成长社区。"
				+ "它包括一系列的机器学习算法（分类、回归、聚类、异常检测、概念漂移检测和推荐系统）和评估工具。关联了WEKA项目，"
				+ "MOA也是用Java编写的，其扩展性更强。<><2$http://192.168.191.1:8080/WebTest/42.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/43.gif><><><2$http://192.168.191.1:8080/WebTest/44.gif><><n>"
				+ "【新增3】异常就苦b了<n>"
				+ "【15】.3.MEKA项目提供了一个面向多标签学习和评价方法的开源实现。在多标签分类中，我们要预测每个输入实例的多个输出变量。"
				+ "这与“普通”情况下只涉及一个单一目标变量的情形不同。此外，MEKA基于WEKA的机器学习工具包。<><5$ee_021><><n>"
				+ "【16】.<><2$http://192.168.191.1:8080/WebTest/45.gif><><><2$http://192.168.191.1:8080/WebTest/46.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/47.gif><><><2$http://192.168.191.1:8080/WebTest/48.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/49.gif><><><2$http://192.168.191.1:8080/WebTest/50.gif><><n>"
				+ "【17】.<><2$http://192.168.191.1:8080/WebTest/50.gif><>4. Advanced Data mining And Machine "
				+ "learning System（ADAMS）是一种新型的柔性工作流引擎，旨在迅速建立并保持真实世界的复杂知识流，它是基于GPLv3发行的。<n>"
				+ "【18】.<><2$http://192.168.191.1:8080/WebTest/51.gif><><2$http://192.168.191.1:8080/WebTest/52.gif>"
				+ "5. Environment for Developing KDD-Applications Supported by Index-Structure（ELKI）是一款基于Java的开源"
				+ "（AGPLv3）数据挖掘软件。ELKI主要集中于算法研究，重点研究聚类分析中的无监督方法和异常检测。<><5$ee_022><><n>"
				+ "【新增4】没异常<n>"
				+ "【19】.<><2$http://192.168.191.1:8080/WebTest/53.gif><><><2$http://192.168.191.1:8080/WebTest/54.gif><>"
				+ "6. Mallet是一个基于Java的面向文本文件的机器学习工具包。<><2$http://192.168.191.1:8080/WebTest/55.gif><>"
				+ "Mallet支持分类算法，如最大熵、朴素贝叶斯和决策树分类。<><2$http://192.168.191.1:8080/WebTest/56.gif><><n>"
				+ "【20】.<><5$ee_023><>7. Encog是一个先进的机器学习框架，集成了支持向量机（SVM）、人工神经网络、遗传算法、贝叶斯网络、隐马尔可夫模型（HMM）、"
				+ "遗传编程和遗传算法。<n>"
				+ "【21】.<><2$http://192.168.191.1:8080/WebTest/57.gif><>8. Datumbox机器学习框架是一个用Java编写的开源框架，允许快速地开发机器学习和统计应用。"
				+ "该框架的核心重点包括大量的机器学习算法以及统计测试，能够处理中等规模的数据集。<><2$http://192.168.191.1:8080/WebTest/58.gif><><n>"
				+ "【22】.<><2$http://192.168.191.1:8080/WebTest/59.gif><>9. Deeplearning4j是使用Java和Scala编写的第一个商业级的、开源的、分布式深入学习库。"
				+ "其设计的目的是用于商业环境中，而不是作为一个研究工具。<><2$http://192.168.191.1:8080/WebTest/60.gif><><n>";

		final String data22 = "<n>【12】.25个Java机器学习工具&库<><2$http://img.kuaixun360.com/phiz/my/ainiyo.gif>  http://img.kuaixun360.com/phiz/mh/zhangl.gif  http://img.kuaixun360.com/phiz/mh/zhangl.gif  http://img.kuaixun360.com/phiz/mh/zhangl.gif<>"
				+ "摘要：本文总结了25个Java机器学习工具&库：Weka集成http://img.kuaixun360.com/phiz/my/bangbangde.gif了数据挖掘工作的机器学习算法、面向数据流挖掘的流行开源框架（MOA）、"
				+ "新型的柔性工作流引擎ADAMS、基于Java的面向文本文件的机器学习工具包Mallet等。<><5$ee_019><><n>"
				+ "【13】.本列表总结了25个Java机器学习工具&库：<><2$http://img.kuaixun360.com/phiz/mh/zhangl.gif><>"
				+ "<><2$http://img.kuaixun360.com/phiz/mh/zhangl.gif><>1. Weka集成了数据挖掘工作的机器学习算法。"
				+ "这些算法可以直接应用于一个数据集上或者你可以自己编写代码来调用。Weka包括一系列的工具，如数据预处理、分类、回归、"
				+ "聚类、关联规则以及可视化。<><2$http://192.168.191.1:8080/WebTest/41.gif><><><5$ee_020><><n>"
				+ "【14】.2.Massive Online Analysis（MOA）是一个面向数据流挖掘的流行开源框架，有着非常活跃的成长社区。"
				+ "它包括一系列的机器学习算法（分类、回归、聚类、异常检测、概念漂移检测和推荐系统）和评估工具。关联了WEKA项目，"
				+ "MOA也是用Java编写的，其扩展性更强。<><2$http://192.168.191.1:8080/WebTest/42.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/43.gif><><><2$http://192.168.191.1:8080/WebTest/44.gif><><n>"
				+ "【新增3】异常就苦b了<n>"
				+ "【15】.3.MEKA项目提供了一个面向多标签学习和评价方法的开源实现。在多标签分类中，我们要预测每个输入实例的多个输出变量。"
				+ "这与“普通”情况下只涉及一个单一目标变量的情形不同。此外，MEKA基于WEKA的机器学习工具包。<><5$ee_021><><n>"
				+ "【16】.<><2$http://192.168.191.1:8080/WebTest/45.gif><><><2$http://192.168.191.1:8080/WebTest/46.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/47.gif><><><2$http://192.168.191.1:8080/WebTest/48.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/49.gif><><><2$http://192.168.191.1:8080/WebTest/50.gif><><n>"
				+ "【17】.<><2$http://192.168.191.1:8080/WebTest/50.gif><>4. Advanced Data mining And Machine "
				+ "learning System（ADAMS）是一种新型的柔性工作流引擎，旨在迅速建立并保持真实世界的复杂知识流，它是基于GPLv3发行的。<n>"
				+ "【18】.<><2$http://192.168.191.1:8080/WebTest/51.gif><><2$http://192.168.191.1:8080/WebTest/52.gif>"
				+ "5. Environment for Developing KDD-Applications Supported by Index-Structure（ELKI）是一款基于Java的开源"
				+ "（AGPLv3）数据挖掘软件。ELKI主要集中于算法研究，重点研究聚类分析中的无监督方法和异常检测。<><5$ee_022><><n>"
				+ "【新增4】没异常<n>"
				+ "【19】.<><2$http://192.168.191.1:8080/WebTest/53.gif><><><2$http://192.168.191.1:8080/WebTest/54.gif><>"
				+ "6. Mallet是一个基于Java的面向文本文件的机器学习工具包。<><2$http://192.168.191.1:8080/WebTest/55.gif><>"
				+ "Mallet支持分类算法，如最大熵、朴素贝叶斯和决策树分类。<><2$http://192.168.191.1:8080/WebTest/56.gif><><n>"
				+ "【20】.<><5$ee_023><>7. Encog是一个先进的机器学习框架，集成了支持向量机（SVM）、人工神经网络、遗传算法、贝叶斯网络、隐马尔可夫模型（HMM）、"
				+ "遗传编程和遗传算法。<n>"
				+ "【21】.<><2$http://192.168.191.1:8080/WebTest/57.gif><>8. Datumbox机器学习框架是一个用Java编写的开源框架，允许快速地开发机器学习和统计应用。"
				+ "该框架的核心重点包括大量的机器学习算法以及统计测试，能够处理中等规模的数据集。<><2$http://192.168.191.1:8080/WebTest/58.gif><><n>"
				+ "【22】.<><2$http://192.168.191.1:8080/WebTest/59.gif><>9. Deeplearning4j是使用Java和Scala编写的第一个商业级的、开源的、分布式深入学习库。"
				+ "其设计的目的是用于商业环境中，而不是作为一个研究工具。<><2$http://192.168.191.1:8080/WebTest/60.gif><><n>";

		mDataList = ParseUtil.parsingByAutoSplit(data2);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setVisibility(View.VISIBLE);
		listView.setAdapter(new ListBaseAdapter(this, mDataList));
		mHelper.setAutoDownload(false);

		/*mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				ArrayList<ViewDataEntity> dataList = ParseUtil
						.parsingByAutoSplit(data2);
				mDataList.addAll(dataList);
				mHelper.notifyDataSetChanged(mGroupIndex);
				((ListBaseAdapter) listView.getAdapter())
						.notifyDataSetChanged();
				mHelper.setAutoDownload(true);
			}
		}, 10000);*/
		String data5 = "<n>【0】.摘要：在2016年，随着实时大数据处理技术被更多的公司接纳使用，"
				+ "必定会对企业的业务分析、人员调整、政策方面带来深远的影响。那些曾在大数据投资的企业也"
				+ "将会在2016年得到回报，<><5$ee_000><>实时大数据分析技术将成为成败的关键。"
				+ "<><2$http://192.168.191.1:8080/WebTest/11.gif><><><5$ee_001><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/12.gif><><><5$ee_002><><n>"
				+ "【1】.很难相信2016年即将要来临，如果社会和商业形势如同电影行业里所预测那样，我们早已驾驶飞行汽"
				+ "车出行……当然，尽管在燃油效率、电动汽车方面取得巨大进展，目前仍旧没有实现飞行汽车的梦想。<><5$ee_003><>"
				+ "不过有一点可以肯定，<><5$ee_003><>在2016年一定会出现一些对企业和社会有着重大的影响新兴的技术。"
				+ "以下是我的一些“预测”：<><2$http://192.168.191.1:8080/WebTest/12.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/13.gif><><><5$ee_004><><><5$ee_004><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/14.gif><><><5$ee_005><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/15.gif><><><5$ee_006><><n>"
				+ "【2】.实时分析将大放异彩<><2$http://192.168.191.1:8080/WebTest/16.gif><>"
				+ "在2016年层出不穷的新技术之中，<><2$http://192.168.191.1:8080/WebTest/17.gif><>"
				+ "实时大数据分析绝对是最为耀眼的那颗珍珠。<><2$http://192.168.191.1:8080/WebTest/18.gif><>"
				+ "Instantly-actionable <><2$http://192.168.191.1:8080/WebTest/18.gif><>"
				+ "分析与Rear-view 数据分析相比不再是一个可选项（而是必备选项）尤其是考虑到消费者和企业的状况。"
				+ "<><2$http://192.168.191.1:8080/WebTest/19.gif><><><5$ee_007><><n>"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "Google 或者Amazon等大型云供应商上垄断（目前它已成为主流）。<><5$ee_007><>"
				+ "在2016年，各行各业的公司都有机会获得前所未有的机遇，如改善病人护理、<><5$ee_008><>"
				+ "增加农作物产量以便养活更多的人口。总而言之，各个公司将会更加明智地做出商业决策。<><5$ee_008><><n>"
				+ "【4】.不可预见的领域将会出现新的威胁进而增加了用户的需求<><2$http://192.168.191.1:8080/WebTest/20.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/111.jpg><>随着实时大数据处理的时代来临，新业务的挑战也会随之出现。"
				+ "<><2$http://192.168.191.1:8080/WebTest/21.gif><><><2$http://192.168.191.1:8080/WebTest/22.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/23.gif><>巨大的竞争威胁将自行出现的（而最大的威胁可能来自企业的核心产业），"
				+ "即使那些与你公司业务无关的或者那些从未想到会成为竞争对手的企业将会蚕食你的市场份额。<><5$ee_008><><><5$ee_009><>"
				+ "所以企业必须具备分析数据的能力，预测新兴的威胁，并制定相应的策略应对；<><2$http://192.168.191.1:8080/WebTest/24.gif><>"
				+ "与此同时，企业也应重构和重新评估与客户的互动过程，以保持客户的忠诚度。<><2$http://192.168.191.1:8080/WebTest/25.gif><><n>"
				+ "<n>【5】.多年来企业一直以客户为中心而努力。然而，对大多数客户而言，他们从未看到过投资的回报，并且在当今的大数据时代，“好”已经远远不足以满足客户体验。"
				+ "在2016年，随着新的实时大数据技术到来，更多的公司将会真正地影响当今最为重要的客户体验。<><2$http://192.168.191.1:8080/WebTest/25.gif><>"
				+ "企业能够利用技术推送个性化信息、优惠和服务，以便实现更好的整体客户体验。将日常消费当做重要的事情处理是每个公司都应为之努力的方向；"
				+ "现在，随着实时大数据应用，客户在首次使用时就会感觉到不同企业的差异。<n>"
				+ "【新增1】用来测试会异常吗，哈哈<n>"
				+ "【6】.CIO将加速离职<><2$http://192.168.191.1:8080/WebTest/26.gif><><><1$http://baidu.com/><百度><>"
				+ "在2016年，成功与失败的CIOs之间的差距将会越拉越大。<><2$http://192.168.191.1:8080/WebTest/27.gif><>"
				+ "那些开创性地使用云和大数据的公司CIOs会将这些技术推广更加实用化，并对商业规则的改变有着独特的见解。<><5$ee_010><>"
				+ "那些对此类技术不敏感的CIOs将会和他们的公司一道落后于时代的竞争。<><2$http://192.168.191.1:8080/WebTest/28.gif><>"
				+ "那些早已建立自己大数据平台的公司在2016年的大数据冲刺时将有着巨大的优势。<><2$http://192.168.191.1:8080/WebTest/29.gif><>"
				+ "随着Spark和 Spark 流的到来，他们能够充分发挥在Hadoop上的投资建立的数据仓库的真正潜力。<><5$ee_011><><><5$ee_012><>"
				+ "大数据的拓荒者将在2016年得到他们的投资回报，并且成败CIOs之间的差距将越拉越大。<><2$http://192.168.191.1:8080/WebTest/30.gif><><n>"
				+ "【7】.随着差距的增大，对高素质CIO人才的需求将会进一步提高。随着CIO人才争夺战开始，高水平的CIO将会被哄抢，而水平较低的将会被淘汰。"
				+ "在Talend Connect会议上，已经讨论一些将在2016年数据集成前沿的领军企业。这些领导者采用新的方式将不断增长的数据转化为可操作信息，"
				+ "这不仅提高了他们的业务，而且在很多情况下，也惠及了更广泛的用户。对于那些目前处于落后的公司来说，幸运的是现有的数据集成技术能使得部署 Spark能力更加快速简洁，"
				+ "这意味着能有机会迎头赶上。<n>"
				+ "【8】.<><2$http://192.168.191.1:8080/WebTest/31.gif><><><2$http://192.168.191.1:8080/WebTest/32.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/33.gif><><><2$http://192.168.191.1:8080/WebTest/34.gif><><n>"
				+ "【9】.企业将会重组<><2$http://192.168.191.1:8080/WebTest/34.gif><><><2$http://192.168.191.1:8080/WebTest/35.gif><>"
				+ "现在，实时大数据技术已成为改变商业规则的技术了，并将在2016年产生深远的影响，并也讨论了如不接纳这些新技术带来的不良后果，企业是时候采用此技术以保持领先的地位了。<n>"
				+ "【新增2】千万不要异常<n>"
				+ "【10】.大数据的时代的到临使得企业正在重新考虑他们的组织架构。<><6$http://192.168.191.1:8080/WebTest/22.rar><>"
				+ "实时大数据正在打破传统商业所谓的最佳实践和架构的障碍，“商业+IT”的模式将让位“商业+IT=创新企业”。那些能够弄清楚商业与IT何如合作并加以盈利的公司将会获胜。"
				+ "跨部门的创新中心必将出现，由CEOs、CIOs、CDOs和新涌现的职位CMTOs将利用各种的技能相互合作。这些信息的SWAT部门能转分析为收入，<><5$ee_013><>"
				+ "并驱动着企业开创前所未有的市场，同时也符合所有安全条例和隐私法规。在2016年，公司必须打破桎梏以期接纳实时大数据的下一个阶段，<><5$ee_014><>"
				+ "并将其作为实现贵公司未来一年的成功所在。<><2$http://192.168.191.1:8080/WebTest/36.gif><><><5$ee_015><><n>"
				+ "【11】.期望崭新的一年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/38.gif><><><5$ee_017><><><5$ee_018><><n>";

	}

	private class ListBaseAdapter extends BaseAdapter {

		private ArrayList<ViewDataEntity> dataList = null;
		private LayoutInflater inflater = null;

		public ListBaseAdapter(Context context,
				ArrayList<ViewDataEntity> dataList) {
			inflater = LayoutInflater.from(context);
			this.dataList = dataList;
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		class ViewHolder {
			GifTextView gifTextView = null;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(MainActivity.this,
						R.layout.listview_item, null);
				holder.gifTextView = (GifTextView) convertView
						.findViewById(R.id.gifTextView);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();

			ViewDataEntity dataEntity = dataList.get(position);

			holder.gifTextView.initView(mGroupIndex, position, dataEntity.text,
					dataEntity.spanList, mScreenWidth, mScreenWidth, mListener);

			return convertView;
		}
	}

	private class TextSpanClickListener implements OnTextSpanClickListener {

		@Override
		public void clickLink(GifTextView gifTextView, SpanEntity entity) {
			Log.i("My", "【clickLink】");
		}

		@Override
		public void clickImage(GifTextView gifTextView, SpanEntity entity) {
			Log.i("My", "【clickImage】");
			File file = mHelper.getDownloadPictureFile(entity);
			if (file != null) {
				Toast.makeText(MainActivity.this, "正在打开图片", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
			} else {
				Toast.makeText(MainActivity.this, "正在下载图片", Toast.LENGTH_SHORT)
						.show();
				mHelper.displayPicture(gifTextView, entity);
			}
		}

		@Override
		public void clickGif(GifTextView gifTextView, SpanEntity entity) {
			Log.i("My", "【clickGif】");
			File file = mHelper.getDownloadPictureFile(entity);
			if (file != null) {
				Toast.makeText(MainActivity.this, "正在打开图片", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
			} else {
				Toast.makeText(MainActivity.this, "正在下载图片", Toast.LENGTH_SHORT)
						.show();
				mHelper.displayPicture(gifTextView, entity);
			}
		}

		@Override
		public void clickZip(GifTextView gifTextView, SpanEntity entity) {
			Log.i("My", "【clickZip】");
		}

	}

}
