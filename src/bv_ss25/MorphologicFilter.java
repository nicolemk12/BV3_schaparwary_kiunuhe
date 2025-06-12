// BV Ue3 SS2025 Vorgabe
//
// Copyright (C) 2025 by Klaus Jung
// All rights reserved.
// Date: 2025-04-02
 		   	  	 	 	

package bv_ss25;

public class MorphologicFilter {
 		   	  	 	 	
	// filter implementations go here:
	
	public void copy(RasterImage src, RasterImage dst) {
		// TODO: just copy the image
	}
	
	public void dilation(RasterImage src, RasterImage dst, boolean[][] kernel) {
		// kernel's first dimension: y (row), second dimension: x (column)
		// TODO: dilate the image using the given kernel
	}
 		   	  	 	 	
	public void erosion(RasterImage src, RasterImage dst, boolean[][] kernel) {
		// This is already implemented. Nothing to do.
		// It will function once you implemented dilation and RasterImage invert()
		src.invert();
		dilation(src, dst, kernel);
		dst.invert();
		src.invert();
	}
	
	public void opening(RasterImage src, RasterImage dst, boolean[][] kernel) {
		// TODO: implement opening by using dilation() and erosion()
	}
	
	public void closing(RasterImage src, RasterImage dst, boolean[][] kernel) {
		// TODO: implement closing by using dilation() and erosion()
	}
	
	
 		   	  	 	 		
	

}
 		   	  	 	 	




