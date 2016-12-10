package com.leador.picassodemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 异步事件处理类，此类获取实例时需传入一个MagusLoadListener对象.
 * 继承了BaseActivity的直接操作magusLoad对象 , magusLoad.load() 然后重载doInBackground(), doInUI()方法 ;
 * 否则 先 用MagusLoad.getInstance()获取对象，再调用load()方法。
 *	@author Administrator
 */
public class AsyncLoad{	
	/**
	 * 默认加载框样式
	 */
	public static int defaultLoadStyle = -1;
	/**
	 * 默认加载框布局
	 */
	public static int defaultLoadResource= -1;	
	private AsyncLoadListener listener;	
	private static AsyncLoad load;
	
	private AsyncLoad(){
	}
	
	/**
	 * 获取异步处理类实例, 此方法为单例模式，调用此方法获得的都是同一个对象，
	 * 也就是说，前面调用此方法得到的对象的listener都会被替换为最后一次调用的listener。
	 * 如对象非一次性操作，使用时请小心，需根据情况设置正确的listener。
	 * 如不确定使用场景，为了保险起见，请使用getAloneInstance(MagusLoadListener listener)，该方法每次都返回一个新的对象。
	 * @param listener 异步处理监听
	 * @return 异步处理类实例
	 */
	@Deprecated
	protected static synchronized AsyncLoad getInstance(AsyncLoadListener listener){
		if(load==null){
			load = new AsyncLoad();
		}
		load.listener = listener;
		return load;
	}
	
	/**
	 * 获取异步处理类实例,此方法每次都返回一个单独的新的对象。
	 * @param listener 异步处理监听
	 * @return 异步处理类实例
	 */
	public static AsyncLoad getAloneInstance(AsyncLoadListener listener){
		AsyncLoad load = new AsyncLoad();
		load.listener = listener;
		return load;
	}
	
	
	/**
	 * 开启异步加载事件
	 * @param context
	 * @param hasLoding  是否显示加载框
	 * @param loadCodes  事件的表示码
	 */
	public void load(Context context , String hasLodingMessage , final boolean hasLoding,Integer... loadCodes ) {
		final ProgressDialog dialog = new ProgressDialog(context);
		if(hasLoding){
			dialog.setMessage(hasLodingMessage);//数据正在加载中,请稍后...
			
			if(defaultLoadResource != -1){
				dialog.setContentView(defaultLoadResource);
			}			
			if(defaultLoadStyle !=-1){
				dialog.setIndeterminateDrawable(context.getResources().getDrawable(defaultLoadStyle));
			}	
			dialog.setCancelable(false);
			dialog.show();
		}
		final ArrayList<Integer> list =  new ArrayList<Integer>(Arrays.asList(loadCodes));
		int j = loadCodes.length;
		for(int i=0;i<j;i++){
	    	final int loadCode = loadCodes[i];
			new AsyncTask<String, Integer, Object>() {	
				protected Object doInBackground(String... params) {
					if(listener != null){
					  return listener.doInBackground(loadCode);
					}else{
						return null;
					}
					
				}
				@Override
				protected void onPostExecute(Object result) {
					if(hasLoding){
						if(dialog.isShowing()){
							list.remove(0);
							if(list.size()==0){
								dialog.dismiss();
							}
						}else{
							return;
						}
					}
					if(listener != null){
						listener.doInUI(result,loadCode);
					}
				}
			}.execute("");
	    }
		
	}
	
	
	/**
	 * 开启异步加载事件
	 * @param context
	 * @param hasLoding  是否显示加载框
	 * @param loadCodes  事件的表示码
	 */
	public void loadunCancel(Context context,final boolean hasLoding,Integer... loadCodes) {
		final ProgressDialog dialog = new ProgressDialog(context);
		if(hasLoding){
			dialog.setMessage("数据正在加载中,请稍后...");
			
			if(defaultLoadResource != -1){
				dialog.setContentView(defaultLoadResource);
			}			
			if(defaultLoadStyle !=-1){
				dialog.setIndeterminateDrawable(context.getResources().getDrawable(defaultLoadStyle));
			}	
			dialog.setCancelable(false);
			dialog.show();
		}
		final ArrayList<Integer> list =  new ArrayList<Integer>(Arrays.asList(loadCodes));
		int j = loadCodes.length;
		for(int i=0;i<j;i++){
	    	final int loadCode = loadCodes[i];
			new AsyncTask<String, Integer, Object>() {	
				protected Object doInBackground(String... params) {
					if(listener != null){
					  return listener.doInBackground(loadCode);
					}else{
						return null;
					}
					
				}
				@Override
				protected void onPostExecute(Object result) {
					if(hasLoding){
						if(dialog.isShowing()){
							list.remove(0);
							if(list.size()==0){
								dialog.dismiss();
							}
						}else{
							return;
						}
					}
					if(listener != null){
						listener.doInUI(result,loadCode);
					}
				}
			}.execute("");
	    }
		
	}
	
	/**
	 * 开启异步加载事件，默认有加载框
	 * @param context
	 * @param hasLodingMessage 加载框提示信息
	 * @param loadCodes 事件的标识码
	 */
	public void load(Context context, String hasLodingMessage , Integer...loadCodes) {
	    load(context, hasLodingMessage , true, loadCodes);
	}
	
	/**
	 * 设置加载框默认的样式
	 * @param defaultLoadStyle 加载框样式文件
	 */
	public static void setDefaultLoadStyle(int defaultLoadStyle) {
		AsyncLoad.defaultLoadStyle = defaultLoadStyle;
	}
     
	/**
	 * 设置加载框默认的布局
	 * @param defaultLoadResource 布局文件
	 */
	public static void setDefaultLoadResource(int defaultLoadResource) {
		AsyncLoad.defaultLoadResource = defaultLoadResource;
	}
	
	/**
	 * 设置加载监听。
	 * @param listener
	 */
	public void setListener(AsyncLoadListener listener) {
		this.listener = listener;
	}
	
	/**
	 * 此类是异步加载类MagusLoad的监听器。
	 * @author Administrator
	 *
	 */
	public interface AsyncLoadListener {
		/**
		 * 后台处理比较耗时的工作
		 * @param loadCode 处理事件的标识码，根据此标识码来区分所要处理的事情
		 * @return 返回给UI中要处理的对象
		 */
		Object doInBackground(int loadCode);	
		
		/**
		 * 后台工作处理完后，对应的在UI中要处理的工作
		 * @param result 后台数据处理完后返回过来的对象。
		 * @param loadCode 标识码，根据此标识码来对应的处理事情。
		 */
		void doInUI(Object result, int loadCode);
		
	}

}
