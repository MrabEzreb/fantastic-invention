package mrabezreb.darzil.gfx;

import java.awt.Graphics;
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
	
	public Image resize(int w, int h) {
		Image ret = new Image(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = ret.getGraphics();
		g.drawImage(this, 0, 0, w, h, null);
		g.dispose();
		return ret;
	}
	
	public Image scale(Double factor) {
		return resize((int)Math.round(factor * getWidth()), (int)Math.round(factor * getHeight()));
	}
	
	public Image append(Image below) {
		Image ret = new Image(Math.max(below.getWidth(), this.getWidth()), below.getHeight() + this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = ret.getGraphics();
		g.drawImage(this, ret.getWidth() / 2 - this.getWidth() / 2, 0, null);
		g.drawImage(below, ret.getWidth() / 2 - below.getWidth() / 2, this.getHeight(), null);
		g.dispose();
		return ret;
	}
	
	public Image addBackground(Image background) {
		Image ths = copy(background);
		Graphics g = ths.getGraphics();
		g.drawImage(this, 0, 0, background.getWidth(), background.getHeight(), null);
		g.dispose();
		return ths;
	}

}
