package demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import ui.Login_UI;

public class demo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		} catch (Exception e) {
			// TODO exception
		}
      new Login_UI(); 
	}

}
