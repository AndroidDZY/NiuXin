package com.example.niuxin;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.niuxin.bean.ChatMsgEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ChatActivity extends Activity implements OnClickListener{
	
	private PopupMenu popup = null;
	private Button mBtnSend,mButtonBiaoqing,mButtonBack,mButtonMore;
	private ImageButton btn_collect,btn_share;
	private EditText mEditText;
	private ListView mListView;
	private LinearLayout layout_more;
	private int count=0;
	//定义适配器
	private ChatMsgViewAdapter mAdapter;
	//聊天数据
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.chat);
		initView();
		initData();
	}
	
	//初始化视图
	private void initView() {
		// TODO Auto-generated method stub
		//获取各种控件
		mListView = (ListView) findViewById(R.id.listview);
		mButtonBack = (Button) findViewById(R.id.btn_back);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mButtonBiaoqing = (Button) findViewById(R.id.emotion);
		mButtonMore = (Button) findViewById(R.id.gengduo);
		mEditText = (EditText) findViewById(R.id.sendmessage);
		layout_more = (LinearLayout) findViewById(R.id.layout_more);
		btn_collect = (ImageButton) findViewById(R.id.btn_collect);
		btn_share = (ImageButton) findViewById(R.id.btn_share);
		
		mButtonBack.setOnClickListener(this);
		mBtnSend.setOnClickListener(this);
		mButtonMore.setOnClickListener(this);
		btn_collect.setOnClickListener(this);
		btn_share.setOnClickListener(this);
		
	}
	private String[] msgArray = new String[]{"我刚刚交了一个女朋友", 
			"那是好事情啊", 
			"可是我家里衣柜抽屉里还有一个啊！", 
			"大哥把衣柜抽屉里面的那个给我吧，反正你用不着了" 
			};
	private final static int COUNT = 4;
	
	//初始化数据
		private void initData() {
			// TODO Auto-generated method stub
			for(int i = 0; i < COUNT; i++) {
				ChatMsgEntity entity = new ChatMsgEntity();
	    		if (i % 2 == 0)
	    		{
	    			entity.setName("丁总");
	    			entity.setMsgType(true);
	    		}else{
	    			entity.setName("杨总");
	    			entity.setMsgType(false);
	    		}
	    		
	    		entity.setText(msgArray[i]);
	    		mDataArrays.add(entity);
			}
			mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
			mListView.setAdapter(mAdapter);
		}

		//定义按钮事件
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()) {
			case R.id.btn_back:
				back();
				break;
			case R.id.btn_send:
				send();
				break;
			case R.id.gengduo:
				more();
				break;
			case R.id.btn_collect:
				collect();
				break;
			case R.id.btn_share:
				share();
				break;
			}
		}
		//展开分享
		private void share() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(ChatActivity.this,ShareActivity.class);
			startActivity(intent);
		}

		//展开收藏
		private void collect() {
			// TODO Auto-generated method stub
			
		}

		//展开更多
		private void more() {
			// TODO Auto-generated method stub
			count ++;
			if(count % 2 == 1)
			{
				layout_more.setVisibility(View.VISIBLE);//显示more
			}
			else
			{
				layout_more.setVisibility(View.GONE);//隐藏more
			}
		}

		//发送消息
		private void send() {
			// TODO Auto-generated method stub
			String contString = mEditText.getText().toString();
			if (contString.length() > 0)
			{
				ChatMsgEntity entity = new ChatMsgEntity();
				entity.setDate(getDate());
				entity.setName("");
				entity.setMsgType(false);
				entity.setText(contString);
				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();
				mEditText.setText("");
				mListView.setSelection(mListView.getCount() - 1);
			}
			
		}
		
		private void back() {
			// TODO Auto-generated method stub
			finish();//返回结束事件
		}
		
		//获取日期
		private String getDate() {
	        Calendar c = Calendar.getInstance();
	        String year = String.valueOf(c.get(Calendar.YEAR));
	        String month = String.valueOf(c.get(Calendar.MONTH));
	        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
	        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
	        String mins = String.valueOf(c.get(Calendar.MINUTE));
	        StringBuffer sbBuffer = new StringBuffer();
	        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
	        return sbBuffer.toString();
	    }
		
		
		public void onPopupButtonClick(View btn_collect)
		  {
		    //创建PopupMenu对象
		      popup=new PopupMenu(this,btn_collect);
		    //将R.menu.popup_menu菜单资源加载到popup菜单中
		    getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
		    //为popup菜单的菜单项单击事件绑定事件监听器
		    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

		      @Override
		      public boolean onMenuItemClick(MenuItem item) {
		        switch(item.getItemId())
		        {
		        case R.id.exit:
		          //隐藏该对话框
		          popup.dismiss();
		          break;
		        default:
		          //使用Toast显示用户单击的菜单项
		          Toast.makeText(ChatActivity.this, "您单击了【"+item.getTitle()+"】菜单项",Toast.LENGTH_SHORT).show();
		        
		        }
		        // TODO Auto-generated method stub
		        return false;
		      }
		      
		    });
		    popup.show();
		  }
		  
		  
		  @Override
		  public boolean onCreateOptionsMenu(Menu menu) {
		    // Inflate the menu; this adds items to the action bar if it is present.
		    getMenuInflater().inflate(R.menu.popup_menu_test, menu);
		    return true;
		  }
		
}
