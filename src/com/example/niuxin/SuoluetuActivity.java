package com.example.niuxin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


@SuppressLint("Instantiatable")
public class SuoluetuActivity {
	
	
    public ImageButton getZhankai() {
		return zhankai;
	}

	public void setZhankai(ImageButton zhankai) {
		this.zhankai = zhankai;
	}

	public Button getEdit() {
		return edit;
	}

	public void setEdit(Button edit) {
		this.edit = edit;
	}

	public Button getBtn01() {
		return btn01;
	}

	public void setBtn01(Button btn01) {
		this.btn01 = btn01;
	}

	public Button getBtn02() {
		return btn02;
	}

	public void setBtn02(Button btn02) {
		this.btn02 = btn02;
	}

	public Button getBtn03() {
		return btn03;
	}

	public void setBtn03(Button btn03) {
		this.btn03 = btn03;
	}

	public Button getBtn04() {
		return btn04;
	}

	public void setBtn04(Button btn04) {
		this.btn04 = btn04;
	}

	public Button getBtn05() {
		return btn05;
	}

	public void setBtn05(Button btn05) {
		this.btn05 = btn05;
	}

	public Button getBtn06() {
		return btn06;
	}

	public void setBtn06(Button btn06) {
		this.btn06 = btn06;
	}

	public Button getBtn07() {
		return btn07;
	}

	public void setBtn07(Button btn07) {
		this.btn07 = btn07;
	}

	public Button getBtn08() {
		return btn08;
	}

	public void setBtn08(Button btn08) {
		this.btn08 = btn08;
	}

	private Button edit,btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08;
	private ImageButton zhankai;
	public SuoluetuActivity(final Activity act) {

		zhankai=(ImageButton)act.findViewById(R.id.btn_open);
		edit=(Button)act.findViewById(R.id.btn_edit);
		btn01=(Button)act.findViewById(R.id.btn01);
		btn02=(Button)act.findViewById(R.id.btn02);
		btn03=(Button)act.findViewById(R.id.btn03);
		btn04=(Button)act.findViewById(R.id.btn04);
		btn05=(Button)act.findViewById(R.id.btn05);
		btn06=(Button)act.findViewById(R.id.btn06);
		btn07=(Button)act.findViewById(R.id.btn07);
		btn08=(Button)act.findViewById(R.id.btn08);

		// 点击展开按钮跳转到看盘详情页五档 
		zhankai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				act.startActivity(new Intent (act, kanpan_wudangActivity.class) );
			}
		});
		
		// 点击编辑自选按钮跳转到编辑自选界面 
		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				act.startActivity(new Intent (act, edit_zixuanActivity.class) );
			}
		});
		
		//点击btn01-btn08都跳转到添加自选界面
		btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
	}

}

