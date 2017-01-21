package org.usfirst.frc.team2399.robot;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraTest {
	private UsbCamera camera;
	
	private CvSink cvSink;
    private  CvSource outputStream;
     
    private Mat source = new Mat();
    private Mat output = new Mat();
    
    private Mat gray = new Mat();
    private Mat HSV = new Mat();
    private Mat mOut = new Mat();
    
    private Point center;
    private Point hStart;
    private Point hEnd;
    private Point vStart;
    private Point vEnd;
    private Point tc;
    private Point bc;
    
    private Scalar color1;
    private Scalar color2;
    
	private ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	private Point[] p ;
    
    public CameraTest()
    {
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	camera.setResolution(RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
    	cvSink =  CameraServer.getInstance().getVideo();
    	outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
    	
    }
   
    protected void HSV(Mat source0, Mat output0)
    {
    	double[] hue = {0.0, 89.89760896858502};
		double[] sat = {0.0, 2.610928245371933};
		double[] val = {197.21223568315986, 255.0};
		
		source = source0;
		HSV = output0;
		Mat temp = new Mat();
		Imgproc.cvtColor(source, temp, Imgproc.COLOR_BGR2HSV);
		Core.inRange(temp, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), HSV);
    }
    
    public Mat getHSV()
    {
    	return HSV;
    }
    
    public void grayscaleWithTarget(int radius, Mat source0, Mat output0)
    {
    	source = source0;
    	output = output0;
    	center = new Point(160, 120);
    	hStart = new Point(160 - radius, 120);
    	hEnd = new Point(160 + radius, 120);
    	vStart = new Point(160, 120 - radius);
    	vEnd = new Point(160, 120 + radius);
    	color1 = new Scalar(255, 0, 0);
                 
    	Imgproc.cvtColor(source, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.circle(gray, center, radius, color1);
        Imgproc.line(gray, hStart, hEnd, color1, 2);
        Imgproc.line(gray, vStart, vEnd, color1, 2);      
 }
    public Mat getGray()
    {
    	return gray;
    }
    
    public void HSVwithMask(Mat source0, Mat mOut0)
    {
    	source = source0;
    	mOut = mOut0; 
    	HSV(source, output);
    	Mat hsv = getHSV();
    	hsv.convertTo(hsv, CvType.CV_8UC1);
		Core.bitwise_xor(mOut, mOut, mOut);
		source.copyTo(mOut, hsv);
    }
    
    public Mat getMask()
    {
    	return mOut;
    }
    
   
    
    public void rectanglePointsFromContours(Mat source0, Mat output0, List<MatOfPoint> contoours)
    {
    	source = source0;
    	output = output0;
    	HSV(source, output);
    	Mat hierarchy = new Mat();
		contours.clear();
		int mode;
			mode = Imgproc.RETR_LIST;
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(getHSV(), contoours, hierarchy, mode, method);
		p = new Point[contoours.size() * 2];
		int t = 0;
		if(contoours.size()>0)
		{
		for(int i = 0; i < contoours.size(); i++)
		{
			Rect r = Imgproc.boundingRect(contoours.get(i));
        	for(int c = 0; c < 2; c++)
        	{
        		if(c % 2 == 0)
        		{
        		p[c + t] = new Point(r.x, r.y);
        		}
        		else{
        		p[c + t] = new Point(r.x + r.width, r.y + r.height);
        		}
        	}
        	t = t + 2;
		}
		}
    }
    
    public void drawRect()
    {

   	 new Thread(() -> {
             while(!OI.isStopButtPressed()) {
                cvSink.grabFrame(source);
                rectanglePointsFromContours(source, output, contours);  
                for(int i = 0; i < p.length; i++)
                {
                	tc = p[i];
                	bc = p[i + 1];
                	Imgproc.rectangle(source, tc, bc, color1);
                }
                outputStream.putFrame(source);
            }
        }).start();
    }
    
    public void drawRectWithTarget()
    {
   	 new Thread(() -> {
             while(!OI.isStopButtPressed()) {
                cvSink.grabFrame(source);
                rectanglePointsFromContours(source, output, contours); 
                Mat together  = new Mat();
                grayscaleWithTarget(10, source, together);
                for(int i = 0; i < p.length; i++)
                {
                	tc = p[i];
                	bc = p[i + 1];
                	Imgproc.rectangle(together, tc, bc, color1);
                }
                outputStream.putFrame(together);
            }
        }).start();
    }
    public void runGrayscaleWithTarget(int radius)
    {
    	 new Thread(() -> {
              while(!OI.isStopButtPressed()) {
                 cvSink.grabFrame(source);
                 grayscaleWithTarget(radius, source, output);
                 outputStream.putFrame(getGray());
             }
         }).start();
 }
    
    public void runHSVwithMask()
    {
    	new Thread(() ->{
			while(!OI.isStopButtPressed())
			{
			cvSink.grabFrame(source);
			HSVwithMask(source, mOut);
			source.copyTo(mOut, HSV);
			outputStream.putFrame(mOut);
			}
		}).start();
    }
    }
    
   
