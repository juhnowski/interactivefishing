package tv.interactivefishing.video;

import java.util.TimerTask;

import tv.interactivefishing.header.Timer;
import tv.interactivefishing.target.TargetTest;


public class VideoTimerTask extends TimerTask {

	private TargetTest targetTest;
	
	@Override
	public void run() {
		System.out.println("==================================================================");
		targetTest.showImage();
	}
	
	public VideoTimerTask(TargetTest tt)
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		targetTest = tt;
	}

}
