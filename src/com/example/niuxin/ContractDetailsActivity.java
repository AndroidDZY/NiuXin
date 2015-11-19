package com.example.niuxin;

import com.niuxin.util.Constants;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ContractDetailsActivity extends Activity {
	private ToggleButton togBtnCollect, togBtnShield;
	private Button btnBack;
	private TextView tvContract ,tvOperation, tvPrice, tvHandnum, tvPosition, tvMinnum, tvMaxnum, tvRemark, tvSenderName;
	private ImageView ivPictureUrl, ivSenderHead;
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_contract_details);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);
		
		initView();
	}
	
	/*
	 * 控件初始化
	 */
	private void initView() {
		/*
		 * 文本控件*/
		tvContract = (TextView)findViewById(R.id.tv_contract_details_contract);	//合约类型
		tvOperation = (TextView)findViewById(R.id.tv_contract_details_operation);	//操作类型
		tvPrice = (TextView)findViewById(R.id.tv_contract_details_price);	//价格
		tvHandnum = (TextView)findViewById(R.id.tv_contract_details_handnum);	//手数
		tvPosition = (TextView)findViewById(R.id.tv_contract_details_position);	//仓位
		tvMinnum = (TextView)findViewById(R.id.tv_contract_details_minnum);	//止盈止损最小值
		tvMaxnum = (TextView)findViewById(R.id.tv_contract_details_maxnum);	//止盈止损最大值
		tvRemark = (TextView)findViewById(R.id.tv_contract_details_remark);	//备注
		tvSenderName = (TextView)findViewById(R.id.tv_contract_details_sender_name);	//盈止损最大值
		
		/*
		 * 图像控件*/
		ivPictureUrl = (ImageView)findViewById(R.id.iv_contract_details_pictureurl);	//配图
		ivSenderHead = (ImageView)findViewById(R.id.iv_contract_details_sender_head);	//报单者头像
		
		/*
		 * 按钮控件*/
		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_collect);	//“收藏该报单者”按钮
		togBtnShield = (ToggleButton) findViewById(R.id.tog_btn_shield);	//“屏蔽该报单者”按钮
		btnBack = (Button) findViewById(R.id.btn_contract_details_back);	//“返回”按钮
		// 收藏报单者切换按钮
		togBtnCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "收藏了该报单者", Toast.LENGTH_SHORT);
					toast.show();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		// 屏蔽报单者切换按钮
		togBtnShield.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "屏蔽了该报单者", Toast.LENGTH_SHORT);
					toast.show();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消屏蔽", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		// 返回按钮
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	
}
