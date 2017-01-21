package org.usfirst.frc.team2399.robot;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

public class Editing {
	private Mat hslOutput = new Mat();
	
	
	public void drawTarget(Mat source, Mat output, Point center, int radius, Scalar circleColor, Point vStart, Point vEnd, Point hStart, Point hEnd, Scalar lineColor)
	{
	Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
	Imgproc.circle(output, center, 30, circleColor);
	Imgproc.line(output, vStart, vEnd, lineColor);
	Imgproc.line(output, hStart, hEnd, lineColor);
	}
}
