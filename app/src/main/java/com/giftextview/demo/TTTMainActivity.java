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

public class TTTMainActivity extends Activity {

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

		String data1 = "<n>【0】.摘要：在20<><5$gandashideren><>16年，随着实时大数据处理技术被更多的公司接纳使用，"
				+ "必定会对企业的业务分析、人员调整、政策方面带来深<><5$gandashideren><>远的影响。那些曾在大数据投资的企业也"
				+ "将会在2016年得<><5$gandashideren><>到回报，<><5$ee_000><>实时大数据分析技术将成为成败的关键。"
				+ "<><2$jiexinshangni><><><5$jiexinshangni><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/12.gif><><><5$jiexinshangni><><n>"
				+ "【1】.很难相信2016年即将要来临，如果社会和商业形势如同电影行业里所预测那样，我们早已驾驶飞行汽"
				+ "车出行……当然，尽管在燃油效率、电动汽车方面取得巨大进展，目前仍旧没有实现飞行汽车的梦想。<><5$ee_003><>"
				+ "不过有一点可以肯定，<><5$ee_003><>在2016年一定会出现一些对企业和社会有着重大的影响新兴的技术。"
				+ "以下是我的一些“预测”：<><2$http://192.168.191.1:8080/WebTest/12.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/13.gif><><><5$ee_004><><><5$kuailaizanwo><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/14.gif><><><5$ee_005><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/15.gif><><><5$ee_006><><n>"
				+ "【2】.实时分析将大放异彩<><2$http://192.168.191.1:8080/WebTest/16.gif><>"
				+ "在2016年层出不穷的新技术之中，<><2$http://192.168.191.1:8080/WebTest/17.gif><>"
				+ "实时大数据分析<><5$gandashideren><>绝对是最为耀眼的那颗珍珠。<><2$http://192.168.191.1:8080/WebTest/18.gif><>"
				+ "Instantly-actionable <><2$http://192.168.191.1:8080/WebTest/18.gif><>"
				+ "分析与Rear-view 数据分析相比不再是一个可选项（而是必备选项）尤其是考虑到消费者和企业的状况。"
				+ "<><2$http://img.kuaixun360.com/phiz/my/jiexinshangni.gif><><><5$ee_007><><n>"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "Google 或者Amazon等大型云供应商上垄断（目前它已成为主流）。<><5$ee_007><>"
				+ "在2016年，各行各业的公司都<><5$jiexinshangni><>有机会获得前所未有的机遇，如改善病人护理、<><5$ee_008><>"
				+ "增加农作物产量以便养活更多的人口。总而言之，各个公司将会更加明智地做出商业决策。<><5$ee_008><><n>"
				+ "【4】.不可预见的领域将会出现新的<><5$jiexinshangni><>威胁进而增加了用户的需求<><2$http://192.168.191.1:8080/WebTest/20.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/111.jpg><>随着实时大数据处理的时代来临，新业务的挑战也会随之出现。"
				+ "<><2$http://192.168.191.1:8080/WebTest/21.gif><><><2$http://192.168.191.1:8080/WebTest/22.gif><>"
				+ "<><2$http://192.168.191.1:8080/WebTest/23.gif><>巨大的竞争威胁将自行出现的（而最大的威胁可能来自企业的核心产业），"
				+ "即使那些与你公司业务无关的或者那些从未想到会成为竞争对手的企业将会蚕食你的市场份额。<><5$ee_008><><><5$ee_009><>"
				+ "所以企业必须具<><5$jiexinshangni><>备分析数据的能力，预测新兴的威胁，并制定相应的策略应对；<><2$http://192.168.191.1:8080/WebTest/24.gif><>"
				+ "与此同时，企业也应重构和重新评<><5$jiexinshangni><>估与客户的互动过程，以保持客户的忠诚度。<><2$http://192.168.191.1:8080/WebTest/25.gif><><n>"
				+ "<n>【5】.多年来企<><5$gandashideren><>业一直以客户为中心而努力。然而，对大多数客户而言，他们从未看到过投资的回报，并且在当今的大数据时代，“好”已经远远不足以满足客户体验。"
				+ "在2016年，随着新的实时大<><5$jiexinshangni><>数据技术到来，更多的公司将会真正地影响当今最为重要的客户体验。<><2$http://192.168.191.1:8080/WebTest/25.gif><>"
				+ "企业能够利用技<><5$gandashideren><>术推送个性化信息、优惠和服务，以便实现更好的整体客户体验。将日常消费当做重要的事情处理是每个公司都应为之努力的方向；"
				+ "现在，随着实时大数据应用，客户在首次使用时就会感觉到不同企业的差异。<n>"
				+ "【新增1】用<><5$gandashideren><>来测试会异常吗，哈哈<n>"
				+ "【6】.CIO将<><5$gandashideren><>加速离职<><2$http://192.168.191.1:8080/WebTest/26.gif><><><1$http://baidu.com/><百度><>"
				+ "在2016年，成功与失败的CIOs之间<><5$jiexinshangni><>的差距将会越拉越大。<><2$http://192.168.191.1:8080/WebTest/27.gif><>"
				+ "那些开创性地使用云和大数据的公司<><5$jiexinshangni><>CIOs会将这些技术推广更加实用化，并对商业规则的改变有着独特的见解。<><5$ee_010><>"
				+ "那些对此类技术不敏<><5$kuailaizanwo><>感的CIOs将会和他们的公司一道落后于时代的竞争。<><2$http://192.168.191.1:8080/WebTest/28.gif><>"
				+ "那些早已建立自己大数据平台的公司在2016年的大数据冲刺时将有着巨大的优势。<><2$http://192.168.191.1:8080/WebTest/29.gif><>"
				+ "随着Spark和 Spark 流的到来，<><5$kuailaizanwo><>他们能够充分发挥在Hadoop上的投资建立的数据仓库的真正潜力。<><5$ee_011><><><5$ee_012><>"
				+ "大数据的拓荒者将在2016年得到他们的投资回报，并且成败CIOs之间的差距将越拉越大。<><2$http://192.168.191.1:8080/WebTest/30.gif><><n>"
				+ "【7】.随着差距的增大，对高素质CIO人才的需求将会进一步提高。随着CIO人才争夺战开始，高水平的CIO将会被哄抢，而水平较低的将会被淘汰。"
				+ "在Talend Connect会议上，已经讨论一些将在2016年数据集成前沿的领军企业。这些领导者采用新的方式将不断增长的数据转化为可操作信息，"
				+ "这不仅提高了他们的<><5$gandashideren><>业务，而<><5$kuailaizanwo><>且在很多情况下，也惠及了更广泛的用户。对于那些目前处于落后的公司来说，幸运的是现有的数据集成技术能使得部署 Spark能力更加快速简洁，"
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
				+ "并将其作为实现贵公<><5$kuailaizanwo><>司未来一年的成功所在。<><2$http://192.168.191.1:8080/WebTest/36.gif><><><5$ee_015><><n>"
				+ "【11】.期望崭新的一年和<><5$zhangl><><><5$zhangl><><><5$zhangl><>技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年和技术的<><5$zhangl><>创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的<><5$kuailaizanwo><>一<><5$zhangl><>年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一<><5$zhangl><>年和技术的创新如<><5$gandashideren><>约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年和技<><5$tingbudongdeyangzi><>术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年和<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望<><5$kuailaizanwo><>崭新的一年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望<><5$kuailaizanwo><>崭新的一年和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新<><5$kuailaizanwo><>的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://192.168.191.1:8080/WebTest/37.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术<><2$http://img.kuaixun360.com/phiz/my/bangbangde.gif><>的创新如约而至！<><2$http://img.kuaixun360.com/phiz/my/bangbangde.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术<><2$http://img.kuaixun360.com/phiz/my/bangbangde.gif><>的创新如约而至！<><2$http://img.kuaixun360.com/phiz/my/bangbangde.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://img.kuaixun360.com/phiz/my/gandashideren.gif><><><5$ee_016><>"
				+ "【11】.期望崭新的一年<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>和技术的创新如约而至！<><2$http://img.kuaixun360.com/phiz/my/gandashideren.gif><><><5$ee_016><>"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，<><5$kuailaizanwo><>人人都期<><5$zhangl><>\n" +
				"\t\t<><5$jiayou><>\n" +
				"\t\t<><5$ainiyo><>\n" +
				"\t\t<><5$dhsk><>\n" +
				"\t\t<><5$hbhm><>\n" +
				"\t\t<><5$hkx><>\n" +
				"\t\t<><5$ly><>\n" +
				"\t\t<><5$wanmei><>\n" +
				"\t\t<><5$tingbudongdeyangzi><>\n" +
				"\t\t<><5$nixingnishanga><>\n" +
				"\t\t<><5$kuailaizanwo><>\n" +
				"\t\t<><5$bangbangde><>\n" +
				"\t\t<><5$xdl><>望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，<><5$kuailaizanwo><>人<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，<><5$kuailaizanwo><>人人都期望相关且<><5$tingbudongdeyangzi><><><5$tingbudongdeyangzi><>个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关且个性化的信息。幸运的是，此类数据的<><5$zhangl><>\n" +
				"\t\t<><5$jiayou><>\n" +
				"\t\t<><5$ainiyo><>\n" +
				"\t\t<><5$dhsk><>\n" +
				"\t\t<><5$bangbangde><>\n" +
				"\t\t<><5$xdl><>和处理不再被Netflix、"
				+ "【3】.现在，<><5$kuailaizanwo><>人人都期望相关且个性化的信息。幸运<><5$hkx><>\n" +
				"\t\t<><5$ly><>的是，此类数据<><5$hkx><>\n" +
				"\t\t<><5$ly><>的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关<><5$tingbudongdeyangzi><>且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，<><5$kuailaizanwo><>人人都期望相关且<><5$tingbudongd<><5$hkx><>\n" +
				"\t\t<><5$ly><>eyangzi><>个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望相关且个性化<><5$hkx><>\n" +
				"\t\t<><5$ly><>的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$kuailaizanwo><>相<><5$bangbangde><>\n" +
				"\t\t<><5$xdl><>\n" +
				"\t\t<><5$mirendewo><>关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都<><5$ainiyo><>期望相关且个性化的信<><5$gandashideren><>息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人<><5$xdl><>都期<><5$kuailaizanwo><>望相关且个性化的信息。幸运的是，此<><5$xdl><>类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"

				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"

				+ "【3】.现在，人人<><5$zhangl><>\n" +
				"\t\t<><5$jiayou><>\n" +
				"\t\t<><5$ainiyo><>都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人<><5$zhangl><>\n" +
				"\t\t<><5$ainiyo><>都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【3】.现在，人人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【12】.现在，人人<><5$zhangl><>\n" +
				"\t\t<><5$jiayou><>\n" +
				"\t\t<><5$ainiyo><>都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"
				+ "【13】.现在，人<><5$zhangl><>\n" +
				"\t\t<><5$jiayou><>\n" +
				"\t\t<><5$ainiyo><>人都期望<><5$ainiyo><>相关且个性化的信息。幸运的是，此类数据的和处理不再被Netflix、"




				+ "<><2$http://192.168.191.1:8080/WebTest/38.gif><><><5$ee_017><><><5$ee_018><><n>";


	/*	<><5$bbanm><>
		<><5$hkx><>
		<><5$ly><>
		<><5$xdl><>
		<><5$zhangl><>
		<><5$jiayou><>
		<><5$ainiyo><>
		<><5$dhsk><>
		<><5$hbhm><>
		<><5$hkx><>
		<><5$ly><>
		<><5$wanmei><>
		<><5$tingbudongdeyangzi><>
		<><5$nixingnishanga><>
		<><5$kuailaizanwo><>
		<><5$bangbangde><>
		<><5$xdl><>
		<><5$mirendewo><>
		<><5$kuailaizanwo><>
		<><5$jiexinshangni><>
		<><5$gandashideren><>*/


		String  jj ="【10】“20.gif”=\"[x_min_抓狂]\"\n" +
				"\"pb9.gif\"=\"[pb_min_鄙视]\"\n" +
				"\"pb48.gif\"=\"[pb_min_噢呵呵]\"\n" +
				"\"pb11.gif\"=\"[pb_min_亲亲]\"\n" +
				"\"pb52.gif\"=\"[pb_min_爆头]\"\n" +
				"\"h_thumb.gif\"=\"[df_黑线]\"\n" +
				"\"37.gif\"=\"[x_min_疑问]\"\n" +
				"\"41.gif\"=\"[x_min_再次生气]\"\n" +
				"\"qq_thumb.gif\"=\"[df_亲亲]\"\n" +
				"\"pb49.gif\"=\"[pb_min_流鼻血]\"\n" +
				"\"pb12.gif\"=\"[pb_min_流口水]\"\n" +
				"\"pb53.gif\"=\"[pb_min_僵尸]\"\n" +
				"\"04.gif\"=\"[x_min_生气]\"\n" +
				"\"bs_thumb.gif\"=\"[df_悲伤]\"\n" +
				"\"25.gif\"=\"[x_min_路过]\"\n" +
				"\"sleepa_thumb.gif\"=\"[df_睡觉]\"\n" +
				"\"pb13.gif\"=\"[pb_min_巨汗]\"\n" +
				"\"shamea_thumb.gif\"=\"[df_害羞]\"\n" +
				"\"lovea_thumb.gif\"=\"[df_爱你]\"\n" +
				"\"yw_thumb.gif\"=\"[df_疑问]\"\n" +
				"\"angrya_thumb.gif\"=\"[df_怒]\"\n" +
				"\"pb14.gif\"=\"[pb_min_开心]\"\n" +
				"\"cj_thumb.gif\"=\"[df_吃惊]\"\n" +
				"\"zhh_thumb.gif\"=\"[df_左哼哼]\"\n" +
				"\"09.gif\"=\"[x_min_目瞪口呆]\"\n" +
				"\"13.gif\"=\"[x_min_感动]\"\n" +
				"\"gza_thumb.gif\"=\"[df_鼓掌]\"\n" +
				"\"pb15.gif\"=\"[pb_min_吐]\"\n" +
				"\"34.gif\"=\"[x_min_痛]\"\n" +
				"\"sweata_thumb.gif\"=\"[df_汗]\"\n" +
				"\"01.gif\"=\"[x_min_诅咒]\"\n" +
				"\"pb16.gif\"=\"[pb_min_飘过]\"\n" +
				"\"pb20.gif\"=\"[pb_min_烧香]\"\n" +
				"\"sb_thumb.gif\"=\"[df_生病]\"\n" +
				"\"18.gif\"=\"[x_min_委屈]\"\n" +
				"\"22.gif\"=\"[x_min_喜欢]\"\n" +
				"\"pb17.gif\"=\"[pb_min_吃饭]\"\n" +
				"\"pb21.gif\"=\"[pb_min_吓]\"\n" +
				"\"39.gif\"=\"[x_min_无语]\"\n" +
				"\"ye_thumb.gif\"=\"[df_耶]\"\n" +
				"\"x_thumb.gif\"=\"[df_嘘]\"\n" +
				"\"pb18.gif\"=\"[pb_min_打死你]\"\n" +
				"\"pb22.gif\"=\"[pb_min_好喜欢]\"\n" +
				"\"06.gif\"=\"[x_min_爱心]\"\n" +
				"\"10.gif\"=\"[x_min_得瑟]\"\n" +
				"\"wq_thumb.gif\"=\"[df_委屈]\"\n" +
				"\"cool_thumb.gif\"=\"[df_酷]\"\n" +
				"\"27.gif\"=\"[x_min_又发火]\"\n" +
				"\"31.gif\"=\"[x_min_飘过]\"\n" +
				"\"pb19.gif\"=\"[pb_min_路过]\"\n" +
				"\"pb23.gif\"=\"[pb_min_弹]\"\n" +
				"\"kl_thumb.gif\"=\"[df_可怜]\"\n" +
				"\"pb0.gif\"=\"[pb_min_哟西]\"\n" +
				"\"pb24.gif\"=\"[pb_min_拜拜]\"\n" +
				"\"15.gif\"=\"[x_Duang]\"\n" +
				"\"yhh_thumb.gif\"=\"[df_右哼哼]\"\n" +
				"\"zy_thumb.gif\"=\"[df_挤眼]\"\n" +
				"\"36.gif\"=\"[x_min_得瑟到不行]\"\n" +
				"\"pb25.gif\"=\"[pb_min_晕]\"\n" +
				"\"40.gif\"=\"[x_min_又路过]\"\n" +
				"\"pb1.gif\"=\"[pb_min_捏脸]\"\n" +
				"\"ldln_thumb.gif\"=\"[df_懒得理你]\"\n" +
				"\"03.gif\"=\"[x_min_怒]\"\n" +
				"\"good_thumb.gif\"=\"[df_good]\"\n" +
				"\"pb26.gif\"=\"[pb_min_咻]\"\n" +
				"\"pb30.gif\"=\"[pb_min_害怕]\"\n" +
				"\"heia_thumb.gif\"=\"[df_偷笑]\"\n" +
				"\"24.gif\"=\"[x_min_go]\"\n" +
				"\"vw_thumb.gif\"=\"[df_威武]\"\n" +
				"\"pb27.gif\"=\"[pb_min_舔屏]\"\n" +
				"\"pb31.gif\"=\"[pb_min_闭嘴]\"\n" +
				"\"tza_thumb.gif\"=\"[df_可爱]\"\n" +
				"\"pb2.gif\"=\"[pb_min_委屈]\"\n" +
				"\"sk_thumb.gif\"=\"[df_思考]\"\n" +
				"\"ok_thumb.gif\"=\"[df_ok]\"\n" +
				"\"08.gif\"=\"[x_min_倒霉]\"\n" +
				"\"pb28.gif\"=\"[pb_min_瞌睡]\"\n" +
				"\"pb32.gif\"=\"[pb_min_囧]\"\n" +
				"\"12.gif\"=\"[x_min_汗]\"\n" +
				"\"geili_thumb.gif\"=\"[df_给力]\"\n" +
				"\"29.gif\"=\"[x_min_得意]\"\n" +
				"\"33.gif\"=\"[x_min_不好意思]\"\n" +
				"\"bba_thumb.gif\"=\"[df_抱抱]\"\n" +
				"\"unheart.gif\"=\"[df_伤心]\"\n" +
				"\"pb29.gif\"=\"[pb_min_潜水]\"\n" +
				"\"pb3.gif\"=\"[pb_min_伤心]\"\n" +
				"\"pb33.gif\"=\"[pb_min_哇咔咔]\"\n" +
				"\"00.gif\"=\"[x_min_飞行]\"\n" +
				"\"pb34.gif\"=\"[pb_min_么么哒]\"\n" +
				"\"17.gif\"=\"[x_min_哦耶]\"\n" +
				"\"21.gif\"=\"[x_min_发火]\"\n" +
				"\"cza_thumb.gif\"=\"[df_馋嘴]\"\n" +
				"\"kbsa_thumb.gif\"=\"[df_挖鼻屎]\"\n" +
				"\"38.gif\"=\"[x_min_生病]\"\n" +
				"\"pb4.gif\"=\"[pb_min_喵呜]\"\n" +
				"\"pb35.gif\"=\"[pb_min_别过来]\"\n" +
				"\"42.gif\"=\"[x_min_兴奋]\"\n" +
				"\"laugh.gif\"=\"[df_哈哈]\"\n" +
				"\"sleepya_thumb.gif\"=\"[df_困]\"\n" +
				"\"hearta_thumb.gif\"=\"[df_心]\"\n" +
				"\"05.gif\"=\"[x_min_吐]\"\n" +
				"\"bz_thumb.gif\"=\"[df_闭嘴]\"\n" +
				"\"pb36.gif\"=\"[pb_min_切]\"\n" +
				"\"pb40.gif\"=\"[pb_min_鼓掌]\"\n" +
				"\"26.gif\"=\"[x_min_惊吓]\"\n" +
				"\"30.gif\"=\"[x_min_又生气]\"\n" +
				"\"sada_thumb.gif\"=\"[df_泪]\"\n" +
				"\"pb5.gif\"=\"[pb_min_娇羞]\"\n" +
				"\"pb37.gif\"=\"[pb_min_悠闲]\"\n" +
				"\"pb41.gif\"=\"[pb_min_自残]\"\n" +
				"\"88_thumb.gif\"=\"[df_拜拜]\"\n" +
				"\"money_thumb.gif\"=\"[df_钱]\"\n" +
				"\"crazya_thumb.gif\"=\"[df_抓狂]\"\n" +
				"\"smilea_thumb.gif\"=\"[df_呵呵]\"\n" +
				"\"14.gif\"=\"[x_min_拽]\"\n" +
				"\"hsa_thumb.gif\"=\"[df_花心]\"\n" +
				"\"pb38.gif\"=\"[pb_min_哼]\"\n" +
				"\"pb42.gif\"=\"[pb_min_呸]\"\n" +
				"\"pb6.gif\"=\"[pb_min_开枪]\"\n" +
				"\"35.gif\"=\"[x_min_抠鼻]\"\n" +
				"\"nm_thumb.gif\"=\"[df_怒骂]\"\n" +
				"\"pb39.gif\"=\"[pb_min_津津有味]\"\n" +
				"\"pb43.gif\"=\"[pb_min_血腥]\"\n" +
				"\"02.gif\"=\"[x_min_吓]\"\n" +
				"\"bs2_thumb.gif\"=\"[df_鄙视]\"\n" +
				"\"sw_thumb.gif\"=\"[df_失望]\"\n" +
				"\"pb44.gif\"=\"[pb_min_抠鼻]\"\n" +
				"\"19.gif\"=\"[x_min_拜拜]\"\n" +
				"\"23.gif\"=\"[x_min_石化]\"\n" +
				"\"pb7.gif\"=\"[pb_min_哇哦]\"\n" +
				"\"tootha_thumb.gif\"=\"[df_嘻嘻]\"\n" +
				"\"hatea_thumb.gif\"=\"[df_哼]\"\n" +
				"\"k_thumb.gif\"=\"[df_打哈欠]\"\n" +
				"\"pb45.gif\"=\"[pb_min_佩服]\"\n" +
				"\"07.gif\"=\"[x_min_愤怒]\"\n" +
				"\"11.gif\"=\"[x_min_旋转]\"\n" +
				"\"cry.gif\"=\"[df_衰]\"\n" +
				"\"wg_thumb.gif\"=\"[df_围观]\"\n" +
				"\"dizzya_thumb.gif\"=\"[df_晕]\"\n" +
				"\"pb46.gif\"=\"[pb_min_大哭]\"\n" +
				"\"pb8.gif\"=\"[pb_min_无语]\"\n" +
				"\"pb50.gif\"=\"[pb_min_吸血鬼]\"\n" +
				"\"28.gif\"=\"[x_min_侠客]\"\n" +
				"\"32.gif\"=\"[x_min_撒娇]\"\n" +
				"\"yx_thumb.gif\"=\"[df_阴险]\"\n" +
				"\"mb_thumb.gif\"=\"[df_太开心]\"\n" +
				"\"t_thumb.gif\"=\"[df_吐]\"\n" +
				"\"pb47.gif\"=\"[pb_min_吃我一拳]\"\n" +
				"\"pb10.gif\"=\"[pb_min_难吃]\"\n" +
				"\"pb51.gif\"=\"[pb_min_中毒]\"\n" +
				"\"16.gif\"=\"[x_min_可怜]\"\n" +
				"\"bbanm.gif\"=[mh_宝宝爱你们]\n" +
				"\"bx.gif\"=[mh_不屑]\n" +
				"\"dhsk.gif\"=[mh_多还是空]\n" +
				"\"hbhm.gif\"=[mh_好不好嘛]\n" +
				"\"hkx.gif\"=[mh_好开心]\n" +
				"\"ly.gif\"=[mh_来呀]\n" +
				"\"xdl.gif\"=[mh_想到了]\n" +
				"\"zhangl.gif\"=[mh_涨了]\n" +
				"\"jiayou.gif\"=[my_加油]\n" +
				"\"ainiyo.gif\"=[my_爱你呦]\n" +
				"\"bangbangde.gif\"=[my_棒棒的]\n" +
				"\"gandashideren.gif\"=[my_干大事的人]\n" +
				"\"jiexinshangni.gif\"=[my_姐欣赏你]\n" +
				"\"kuailaizanwo.gif\"=[my_快来赞我]\n" +
				"\"mirendewo.gif\"=[my_迷人的我]\n" +
				"\"nixingnishanga.gif\"=[my_你行你上啊]\n" +
				"\"tingbudongdeyangzi.gif\"=[my_听不懂的样子]\n" +
				"\"wanmei.gif\"=[my_完美]";

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

		mDataList = ParseUtil.parsingByAutoSplit(data1);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setVisibility(View.VISIBLE);
		listView.setAdapter(new ListBaseAdapter(this, mDataList));
		mHelper.setAutoDownload(true);

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
				convertView = View.inflate(TTTMainActivity.this,
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
				Toast.makeText(TTTMainActivity.this, "正在打开图片", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
			} else {
				Toast.makeText(TTTMainActivity.this, "正在下载图片", Toast.LENGTH_SHORT)
						.show();
				mHelper.displayPicture(gifTextView, entity);
			}
		}

		@Override
		public void clickGif(GifTextView gifTextView, SpanEntity entity) {
			Log.i("My", "【clickGif】");
			File file = mHelper.getDownloadPictureFile(entity);
			if (file != null) {
				Toast.makeText(TTTMainActivity.this, "正在打开图片", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
			} else {
				Toast.makeText(TTTMainActivity.this, "正在下载图片", Toast.LENGTH_SHORT)
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
