// BV Ue3 SS2025 Vorgabe
//
// Copyright (C) 2025 by Klaus Jung
// All rights reserved.
// Date: 2025-04-02
 		   	  	 	 	

package bv_ss25;

import java.io.File;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class RasterImage {
 		   	  	 	 		
	private static final int gray  = 0xffa0a0a0;

	public int[] argb;	// pixels represented as ARGB values in scanline order
	public int width;	// image width in pixels
	public int height;	// image height in pixels
	
	public RasterImage(int width, int height) {
		// creates an empty RasterImage of given size
		this.width = width;
		this.height = height;
		argb = new int[width * height];
		Arrays.fill(argb, gray);
	}
 		   	  	 	 		
	public RasterImage(File file) {
		// creates an RasterImage by reading the given file
		Image image = null;
		if(file != null && file.exists()) {
			image = new Image(file.toURI().toString());
		}
		if(image != null && image.getPixelReader() != null) {
			width = (int)image.getWidth();
			height = (int)image.getHeight();
			argb = new int[width * height];
			image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
		} else {
			// file reading failed: create an empty RasterImage
			this.width = 256;
			this.height = 256;
			argb = new int[width * height];
			Arrays.fill(argb, gray);
		}
	}
	
	public RasterImage(ImageView imageView) {
		// creates a RasterImage from that what is shown in the given ImageView
		Image image = imageView.getImage();
		width = (int)image.getWidth();
		height = (int)image.getHeight();
		argb = new int[width * height];
		image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
	}
	
	public void setToView(ImageView imageView) {
		// sets the current argb pixels to be shown in the given ImageView
		if(argb != null) {
			WritableImage wr = new WritableImage(width, height);
			PixelWriter pw = wr.getPixelWriter();
			pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
			imageView.setImage(wr);
		}
	}
 		   	  	 	 		
	
	// image point operations to be added here

	public void binarize(int threshold) {
		// TODO: binarize the image with given threshold
		for (int i = 0; i < argb.length; i++)
		{ 	int pos = argb[i];
			//RGB-Komponenten
			int r = (pos >> 16) & 0xFF;
			int g = (pos >> 8) & 0xFF;
			int b = pos & 0xFF;

			//Graustufe berechnen
			int grey = (r + g + b)/3;
			//Pixel auf weiss oder schwarz setzen
			if(grey >= threshold)
			{
				argb[i] = 0xffffffff;
			} else
			{
				argb[i] = 0xff000000;
			}

		}
	}

	public void invert() {
		// TODO: invert the image (assuming a binary image)
		for(int i = 0; i < argb.length; i++){
			if(argb[i] == 0xff000000){
				argb[i] = 0xffffffff;
			}
			else if(argb[i] == 0xffffffff){
				argb[i] = 0xff000000;
			}

		}
	}



}
 		   	  	 	 	




