import java.io.*;
import java.awt.*;
import java.lang.Math;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;

public class filters{
	public static void main(String[] args) throws IOException {
		try{
			double threeFilter[][] = {{0.0, 0.0,0.0},
									  {0.0, 1.0, 0.0},
									  {0.0, 0.0, 0.0}};;
			int filterSize = 3;
			int arraySize = filterSize * filterSize;
			int count = args.length;
			boolean medianOn = false;
			boolean contrastOn = false;
			boolean blackWhite = false;
			if(count < 1 || args[0].length() != 1){
				System.out.println("Input a command for the first argument:");
				System.out.println("B/b ---- Blur");
				System.out.println("D/d ---- Edge Detection");
				System.out.println("S/s ---- Sharpen");
				System.out.println("E/e ---- Emboss");
				System.out.println("C/c ---- Contrast Enhancement");
				System.out.println("M/m ---- Median Filter");
				System.out.println("W/w ---- Black/White");
			}
			else{
				String type = "";
				if(args[0].equals("C") || args[0].equals("c")){
					type = "Contrast Enhancement_";
					contrastOn = true;
				}
				else if(args[0].equals("m") || args[0].equals("M")){
					type = "Median filter_";
					medianOn = true;
				}
				else if(args[0].equals("w") || args[0].equals("W")){
					type = "BW_";
					blackWhite = true;
				}
				else if(args[0].equals("s") || args[0].equals("S")){
					type = "Sharpen_";
					threeFilter[0][0] = 0.0;
					threeFilter[1][0] = -1.0;
					threeFilter[2][0] = 0.0;
					threeFilter[0][1] = -1.0;
					threeFilter[1][1] = 5.0;
					threeFilter[2][1] = -1.0;
					threeFilter[0][2] = 0.0;
					threeFilter[1][2] = -1.0;
					threeFilter[2][2] = 0.0;
				}
				else if(args[0].equals("b") || args[0].equals("B")){
					type = "Blur_";
					threeFilter[0][0] = 1/16.0;
					threeFilter[1][0] = 2/16.0;
					threeFilter[2][0] = 1/16.0;
					threeFilter[0][1] = 2/16.0;
					threeFilter[1][1] = 4/16.0;
					threeFilter[2][1] = 2/16.0;
					threeFilter[0][2] = 1/16.0;
					threeFilter[1][2] = 2/16.0;
					threeFilter[2][2] = 1/16.0;
				}
				else if(args[0].equals("D") || args[0].equals("d")){
					type = "Edge_Detection_";
					threeFilter[0][0] = -1.0;
					threeFilter[1][0] = -1.0;
					threeFilter[2][0] = -1.0;
					threeFilter[0][1] = -1.0;
					threeFilter[1][1] = 8.0;
					threeFilter[2][1] = -1.0;
					threeFilter[0][2] = -1.0;
					threeFilter[1][2] = -1.0;
					threeFilter[2][2] = -1.0;
				}
				else if(args[0].equals("E") || args[0].equals("e")){
					type = "Emboss_";
					threeFilter[0][0] = -2.0;
					threeFilter[1][0] = -1.0;
					threeFilter[2][0] = 0.0;
					threeFilter[0][1] = -1.0;
					threeFilter[1][1] = 1.0;
					threeFilter[2][1] = 1.0;
					threeFilter[0][2] = 0.0;
					threeFilter[1][2] = 1.0;
					threeFilter[2][2] = 2.0;
				}
				for(int i = 1; i < count; i++){
					File file = new File(args[i]);
					BufferedImage image = ImageIO.read(file);
					int width = image.getWidth();
					int height = image.getHeight();
					BufferedImage modified = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					if(contrastOn){
						double factor = (double)(255.0 / (height * width));
						int r[] = new int[256];
						int g[] = new int[256];
						int b[] = new int[256];
						int rEQ[] = new int[256];
						int bEQ[] = new int[256];
						int gEQ[] = new int[256];
						for(int j = 0; j < 256; j++){
							r[j] = 0;
							g[j] = 0;
							b[j] = 0;
							rEQ[j] = 0;
							bEQ[j] = 0;
							gEQ[j] = 0;
						}
						for(int j = 0; j < width; j++){
							for(int k = 0; k < height; k++){
								int red = new Color(image.getRGB(j,k)).getRed();
								int green = new Color(image.getRGB(j,k)).getBlue();
								int blue = new Color(image.getRGB(j,k)).getGreen();
								r[red]++;
								b[blue]++;
								g[green]++;
							}
						}
						int sumR = 0, sumG = 0, sumB = 0;
						for(int j = 0; j < 256; j++){
							sumR += r[j];
							sumG += r[j];
							sumB += r[j];
							int valR = (int)(sumR * factor);
							int valG = (int)(sumG * factor);
							int valB = (int)(sumB * factor);
							if(valR > 255) rEQ[j] = 255;
							else rEQ[j] = valR;
							if(valG > 255) gEQ[j] = 255;
							else gEQ[j] = valG;
							if(valB > 255) bEQ[j] = 255;
							else bEQ[j] = valB;
						}
						for(int j = 0; j < width; j++)
							for(int k = 0; k < height; k++){
								int red = new Color(image.getRGB(j,k)).getRed();
								int green = new Color(image.getRGB(j,k)).getGreen();
								int blue = new Color(image.getRGB(j,k)).getBlue();
								int r1 = rEQ[red];
								int g1 = gEQ[green];
								int b1 = bEQ[blue];
								int rgb = (r1 << 16) | (g1 << 8) | b1;
								modified.setRGB(j,k,rgb);
						}
					}
					else{
						for(int j = 0; j < width; j++){
							for(int k = 0; k < height; k++){
								double red = 0.0, green = 0.0, blue = 0.0;
								int r[] = new int[arraySize];
								int g[] = new int[arraySize];
								int b[] = new int[arraySize];
								int iterator = 0;
								for(int filtX = 0; filtX < filterSize; filtX++){
									for(int filtY = 0; filtY < filterSize; filtY++){
										int x = (j - filterSize/2 + filtX + width) % width;
										int y = (k - filterSize/2 + filtY + height) % height;
										Color temp = new Color(image.getRGB(x,y));
										if(!medianOn){
											red += temp.getRed() * threeFilter[filtX][filtY];
											blue += temp.getBlue() * threeFilter[filtX][filtY];
											green += temp.getGreen() * threeFilter[filtX][filtY];
										}
										else{
											r[iterator] += temp.getRed();	
											g[iterator] += temp.getGreen();
											b[iterator++] += temp.getBlue();
										}
									}
								}
								int rgb;
								if(medianOn){
									Arrays.sort(r);
									Arrays.sort(g);
									Arrays.sort(b);
									rgb = ((int)r[arraySize/2] << 16) | ((int)g[arraySize/2] << 8) | (int)b[arraySize/2];
								}
								else{
									red = Math.max(Math.min(red,255),0);
									green = Math.max(Math.min(green,255),0);
									blue = Math.max(Math.min(blue,255), 0);
									if(blackWhite){
										double w = (red + blue + green) / 3.0;
										rgb = ((int)w << 16) | ((int)w << 8) | (int)w;
									}
									else{
										rgb = ((int)red << 16) | ((int)green << 8) | (int)blue;
									}
								}
								modified.setRGB(j,k, rgb);
							}
						}
					}
					ImageIO.write(modified, "jpg", new File(type + args[i]));
				}
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: Unable to read JPG.");
		}
	}
}