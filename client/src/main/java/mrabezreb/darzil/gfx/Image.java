package mrabezreb.darzil.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Image extends BufferedImage {

	public static Image load(String path) throws IOException {
		return copy(ImageIO.read(Image.class.getResource(path)));
	}
	
	public static Image copy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new Image(cm, raster, isAlphaPremultiplied, null);
	}
	
	public Image(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public Image(int arg0, int arg1, int arg2, IndexColorModel arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public Image(ColorModel arg0, WritableRaster arg1, boolean arg2,
			Hashtable<?, ?> arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	public Image copy() {
		return copy(this);
	}

}
