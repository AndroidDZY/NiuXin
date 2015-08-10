package com.example.niuxin.test;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//private static Message getPostMessage(Runnable r)
//1.该方法完成了两个操作，第一生成了一个Message对象，第二，将r对象赋值给Message对象的callback属性

//第一个问题，如何能够把一个Runnable对象放置在消息队列当中：实际上是生成了一个Message对象，并将r赋值给Message对象的callback属性，然后再将Message对象
//放置在消息队列当中
//第二个问题，Looper取出了携带有r对象的Message对象之后，干了些神马？去除Message对象之后调用了dispatchMessage方法，然后判断Message的callback属性是否为空，
//此时的callback属性有值，所有执行了handleCallback(Message msg)，在该方法当中执行了msg.callback.run();

/*	
public class ExampleActivity extends Activity {

	private Button button;
	private Handler handler = new Handler(); 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button)findViewById(R.id.buttonId);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TestThread tt = new TestThread();
				tt.start();
			}
		});
    }
    
    class TestThread extends Thread{
    	
    	@Override
    	public void run(){
    		//从网络上取回数据
    		Runnable r = new Runnable(){
				@Override
				public void run() {
					//这里可以写上更新UI的代码
					String currentThreadName = Thread.currentThread().getName();
					System.out.println("当前线程的名称为:" + currentThreadName);
				}
    			
    		};
    		
    		//post(r)方法将应用r对象放置在消息队列当中，Looper对象（主线程）从消息队列当中取出了r对象
    		//我们的猜测：取出r对象之后
    		//1.Thread t = new Thread(r);
    		//2.t.start();
    		//根据测试的结果可以看出，该思路是错误的
    		//我们的问题是Looper取出了r对象之后，干了些神马？
    		handler.post(r);
    	}
    }
}
*/
