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
		System.arraycopy(src.argb, 0, dst.argb, 0, src.argb.length);
	}

	public void dilation(RasterImage src, RasterImage dst, boolean[][] kernel) {
		int kHeight = kernel.length;
		int kWidth = kernel[0].length;
		int kCenterY = kHeight / 2;
		int kCenterX = kWidth / 2;


		for (int i = 0; i < dst.argb.length; i++) {
			dst.argb[i] = 0xffffffff;
		}


		for (int y = 0; y < src.height; y++) {
			for (int x = 0; x < src.width; x++) {

				boolean black = false;


				for (int ky = 0; ky < kHeight && !black; ky++) {
					for (int kx = 0; kx < kWidth && !black; kx++) {


						int imgY = y + ky - kCenterY;
						int imgX = x + kx - kCenterX;

						// Nur wenn innerhalb des Bildes
						if (imgY >= 0 && imgY < src.height && imgX >= 0 && imgX < src.width) {
							int imgIndex = imgY * src.width + imgX;

							boolean isBlack = src.argb[imgIndex] == 0xFF000000;
							boolean kernelAktiv = kernel[ky][kx];

							if (isBlack && kernelAktiv) {
								black = true;
								break;

							}
						}
					}
					if (black) break;
				}
				int dstIndex = y * dst.width + x;
				if (black) {
					dst.argb[dstIndex] = 0xFF000000; // Schwarz
				} else {
					dst.argb[dstIndex] = 0xFFFFFFFF; // Weiß
				}
			}
		}
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

		// Temporäres Bild für den Zwischenstand
		RasterImage temp = new RasterImage(src.width, src.height);
		//Erst Erosion, dann Dilatation
		erosion(src, temp, kernel);
		dilation(temp, dst, kernel);
	}
	
	public void closing(RasterImage src, RasterImage dst, boolean[][] kernel) {
		// TODO: implement closing by using dilation() and erosion()
		RasterImage temp = new RasterImage(src.width, src.height);
		dilation(src, temp, kernel);
		erosion(temp, dst, kernel);
	}
	


}
 		   	  	 	 	




