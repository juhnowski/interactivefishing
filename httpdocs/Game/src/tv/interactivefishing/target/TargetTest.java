package tv.interactivefishing.target;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.gstreamer.ClockTime;
import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;
import org.gstreamer.swing.VideoComponent;
import tv.interactivefishing.context.Settings;
import tv.interactivefishing.context.SettingsXMLParser;
import tv.interactivefishing.video.VideoTimerTask;
import tv.interactivefishing.video.protocol.MetaDataXMLParser;


@SuppressWarnings({ "deprecation", "serial" })
public class TargetTest extends JFrame {

	public int screenHeight;
	public int screenWidth;
	int imageHeight;
	int imageWidth;
	 
	
	public static PlayBin playbin;
	public static VideoComponent videoComponent;
	public static TargetPanel tp;
	
	public static Target currentTarget;
	
	public static TargetTest frame;
	
	public static boolean isStart = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		settingsReopen("http://interactivefishing.tv/Game/session.xml",args);
	}

	/**
	 * Скачивает и открывает файл настроек
	 * @param uri адрес файла настроек
	 */
	public static void settingsOpen(String uri)
	{
		try
		{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SettingsXMLParser saxp = new SettingsXMLParser();

		parser.parse(uri, saxp); //чтобы открыть локальный файл нужно использовать new File() вместо String
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void settingsReopen(String uri, String[] args)
	{
		if (isStart)
		{
			isStart = false;
		}
		else
		{
			frame.setVisible(false);
			Settings.settings.clear();
			settingsOpen(uri);
		}
//------------------------------------------------------
		settingsOpen(uri);
		
		args = Gst.init("VideoPlayer", args);
        playbin = new PlayBin("VideoPlayer");

		videoOpen("http://interactivefishing.tv/Game/video/1.mov");
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame = new TargetTest();

/*					
					frame.imageHeight = tp.screen.imageHeight;
					frame.imageWidth = tp.screen.imageWidth; 
					
					if (frame.screenHeight > frame.imageHeight)
					{
						frame.screenHeight = frame.imageHeight;
					}

					
					if (frame.screenWidth > frame.imageWidth)
					{
						frame.screenWidth = frame.imageWidth;
					}
*/
					frame.screenHeight = 760;
					frame.screenWidth = 1080;

					tp.screen.screenWidth = frame.screenWidth;
					tp.screen.screenHeight = frame.screenHeight;
					
					frame.setSize((int)frame.screenWidth, (int)frame.screenHeight);
					tp.setBounds(new Rectangle((int)frame.screenWidth, (int)frame.screenHeight));
					
					tp.screenWidth = (int)frame.screenWidth;
					tp.screenHeigh = (int)frame.screenHeight;
					
					System.out.println("tp.screenWidth =" + tp.screenWidth + " tp.screenHeigh" + tp.screenHeigh);
					frame.getContentPane().add(tp);
	                videoComponent = new VideoComponent();
	                playbin.setVideoSink(videoComponent.getElement());
	                videoComponent.setVisible(false);
	                frame.getContentPane().add(videoComponent, BorderLayout.CENTER);
	                
	                videoComponent.addPropertyChangeListener(new 
	        				PropertyChangeListener()
					{
						@Override
						public void propertyChange(PropertyChangeEvent event) {
							System.out.println("Property:" + event.getPropertyName() + " changed: " + event.getNewValue());
						}
					}
				);
	                
					frame.setVisible(true);
					frame.moving(0, 0, Integer.parseInt(Settings.settings.get("initial_sx")), Integer.parseInt(Settings.settings.get("initial_sy")));
					frame.zooming(1,Double.parseDouble(Settings.settings.get("initial_zoom")));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
//------------------------------------------------------		
	}
	
	/**
	 * Скачивает и открывает видеофайл и метаданные к нему.
	 * Задается только адрес видеофайла, метаданные получаются по адресу uri+".xml"
	 * @param uri адрес видео файла
	 */
	public static void videoOpen(String uri)
	{
        try
        {
        	playbin.setURI(new URI(uri));
    		SAXParserFactory factoryMD = SAXParserFactory.newInstance();
    		SAXParser parserMD = factoryMD.newSAXParser();
    		MetaDataXMLParser saxmd = new MetaDataXMLParser();
    		parserMD.parse(uri+".xml", saxmd); //чтобы открыть локальный файл нужно использовать new File() вместо String

        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		
	}
	
	/**
	 * Create the frame.
	 */
	public TargetTest() {
		tp = new TargetPanel();
		
		tp.addPropertyChangeListener(
			new PropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent event)
				{
					System.out.println("TP Change state!!!");
					showVideo();
				}
			}
		);
		setType(Type.UTILITY);
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		setLocation(0, 0);
		setTitle("Fishing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		tp.setBackground(Color.BLACK);
		tp.setOpaque(false);
	}

	/**
	 * Функция запуска видео
	 */
	public void showVideo()
	{
		new Thread() 
		{
	        public void run() 
	        {
	        	int to_vsx;
	        	int to_vsy;
	        	int from_vsx;
	        	int from_vsy;
	        	
	        	//Позиционируем панораму
	        	//TODO Позиционируем панораму, после чего скрываем ее
	        	
	        	double from_zoom = tp.screen.getZoom();
	        	
	        	int id = 0;
	        	for(Target t: TargetController.targets)
	        	{
	        		if (t.isSelected)
	        		{
	        			currentTarget = t;
	        			id  = currentTarget.id;
	        		}
	        	}
	        	
	        	videoOpen(Settings.settings.get("video_"+id));
	        	System.out.println("video_"+id +" = " + Settings.settings.get("video_"+id));
	        	
	        	//смещаемся
	        	to_vsx = Integer.parseInt(Settings.settings.get("vsx_"+id));
	        	to_vsy = Integer.parseInt(Settings.settings.get("vsy_"+id));
	        	from_vsx = (int)(tp.screen.getX());
	        	from_vsy = (int)(tp.screen.getY());
	        	moving(from_vsx, from_vsy, to_vsx, to_vsy);

	        	//зумируем
	        	double to_vsz = Double.parseDouble(Settings.settings.get("vsz_"+id));
	        	zooming(from_zoom, to_vsz);

	        	//смещаемся
	        	to_vsx = Integer.parseInt(Settings.settings.get("vsx_"+id));
	        	to_vsy = Integer.parseInt(Settings.settings.get("vsy_"+id));
	        	from_vsx = (int)(tp.screen.getX());
	        	from_vsy = (int)(tp.screen.getY());
	        	
	        	moving(from_vsx, from_vsy, to_vsx, to_vsy);
	        }
	        

	    }.run();
		
		
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                playbin.seek(0,TimeUnit.SECONDS);
            	playbin.play();
                
                currentTarget.isVisible = false;
                currentTarget.isAvailable = false;
                currentTarget.isSelected = false;
                
                tp.hdr.cast.cast--;
                
                tv.interactivefishing.header.Timer.videoStarted(currentTarget.video_duration*1000);

                Timer timer = new Timer();
                timer.schedule(new VideoTimerTask(frame), currentTarget.video_duration*1000);
                
                videoComponent.setVisible(true);
                
                //try	{Thread.sleep(2000);	} catch(InterruptedException ex){}
                tp.isPanVisible = false;
                tp.mnu.visible = false;
                
            }
        });



/*
		new Thread() 
		{
	        public void run() 
	        {
	        	try	{Thread.sleep(2000);	} catch(InterruptedException ex){}
	            System.out.println("showVideo");

	        }
		}.start();
*/
	}
	
    private void zooming(double from, double to)
    {
    	int ticks = Integer.parseInt(Settings.settings.get("prep_ticks"));
    	long ptt = Integer.parseInt(Settings.settings.get("prep_ticks_time"));
    	
    	double delta_z = (to-from) / ticks;
        
    	if(delta_z!=0)
    	{
    		for (int i =1; i < ticks+1; i++ )
    		{
    			tp.imageScale(from + delta_z*i);
    			tp.paintImmediately(0,0,tp.getWidth(),tp.getHeight());
    			
    			try{Thread.sleep(ptt);}	catch(InterruptedException ex){}
    		}
    	}
    		        	
    }
    
    private void moving(int fromX, int fromY, int toX, int toY)
    {
    	int ticks = Integer.parseInt(Settings.settings.get("prep_ticks"));
    	long ptt = Integer.parseInt(Settings.settings.get("prep_ticks_time"));
    	
    	double delta_x = (toX - fromX ) / ticks;
    	double delta_y = (toY - fromY) / ticks;

    	int newX =0;
    	int newY = 0;
    	
    	if( (delta_x==0) && (delta_y==0) )
    	{
    		return;
    	}
    		for (double i = 1; i < ticks+1; i++ )
    		{
    			newX = (int)(fromX + delta_x * i);
    			newY = (int)(fromY + delta_y * i);
    			tp.imageSet(newX,newY);
    			tp.paintImmediately(0,0,tp.getWidth(),tp.getHeight());
    			try	{Thread.sleep(ptt);	} catch(InterruptedException ex){}
    		}
    }
	
	/**
	 * Функция отображения панорамы
	 */
	public void showImage()
	{
        tp.isPanVisible = true;
        if (currentTarget.hasFish)
        {
        	tp.hdr.fish.fish++;
        	tp.hdr.m.m += currentTarget.mFish;
        	
        }
		playbin.setState(State.NULL);
		
		videoComponent.setVisible(false);
		tp.mnu.visible = true;
		System.out.println("showImage");
	}

}
