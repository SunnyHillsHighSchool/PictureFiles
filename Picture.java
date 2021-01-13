import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture 
{
  /////////////////////// Fields /////////////////////////
  
  // the file name associated with the picture
  private String fileName;
  
  // buffered image to hold pixels for the picture
  private BufferedImage bufferedImage;
   
  // extension for this file (jpg or bmp)
  private String extension;

  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // the load method will initialize the fileName,
    // bufferedImage, and extension instance variables.
    load(fileName);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
   this.bufferedImage = image;
   fileName = "None";
   extension = "jpg";
  }
  
  /**
  * A constructor that takes the width and height desired for a picture and
  * creates a buffered image of that size.  This constructor doesn't 
  * show the picture.  The pixels will all be white.
  * @param width the desired width
  * @param height the desired height
  */
  public Picture(int width, int height)
  {
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    fileName = "None";
    extension = "jpg";
    setAllPixelsToAColor(Color.white);
  }

  /**
  * A constructor that takes the width and height desired for a picture and
  * creates a buffered image of that size.  It also takes the
  * color to use for the background of the picture.
  * @param width the desired width
  * @param height the desired height
  * @param theColor the background color for the picture
  */
  public Picture(int width, int height, Color theColor)
  {
    // Call the two-parameter constructor
    this(width,height);
    setAllPixelsToAColor(theColor);
  }

 
  
  

  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /**
  * Method to get the extension for this picture
  * @return the extendsion (jpg, bmp, giff, etc)
  */
  public String getExtension() { return extension; }
/**
    * Method to set the color in the picture to the passed color
    * @param color the color to set to
    */
  public void setAllPixelsToAColor(Color color)
  {
    // loop through all x
    for (int x = 0; x < this.getWidth(); x++)
    {
      // loop through all y
      for (int y = 0; y < this.getHeight(); y++)
      {
        getPixel(x,y).setColor(color);
      }
    }
  }
 
 

 
 
 
 
  /**
    * Method to get the buffered image
    * @return the buffered image 
    */
  public BufferedImage getBufferedImage() 
  {
      return bufferedImage;
  }
 
  /**
    * Method to get a graphics object for this picture to use to draw on
    * @return a graphics object to use for drawing
    */
  public Graphics getGraphics()
  {
    return bufferedImage.getGraphics();
  }
 
  /**
    * Method to get a Graphics2D object for this picture which can
    * be used to do 2D drawing on the picture
    */
  public Graphics2D createGraphics()
  {
    return bufferedImage.createGraphics();
  }
 
  /**
    * Method to get the file name associated with the picture
    * @return  the file name associated with the picture
    */
  public String getFileName() { return fileName; }
 
  /**
    * Method to set the file name
    * @param name the full pathname of the file
    */
  public void setFileName(String name)
  {
    fileName = name;
  }

  /**
    * Method to get the width of the picture in pixels
    * @return the width of the picture in pixels
    */
  public int getWidth() { return bufferedImage.getWidth(); }
 
  /**
    * Method to get the height of the picture in pixels
    * @return  the height of the picture in pixels
    */
  public int getHeight() { return bufferedImage.getHeight(); }

  /**
    * Method to get an image from the picture
    * @return  the buffered image since it is an image
    */
  public Image getImage()
  {
    return bufferedImage;
  }
 
  /**
   * Method to return the pixel value as an int for the given location
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the pixel value as an integer (alpha, red, green, blue)
   */
  public int getBasicPixel(int x, int y)
  {
      return bufferedImage.getRGB(x,y);
  }
    
  /** 
    * Method to set the value of a pixel in the picture from an int
    * @param x the x coordinate of the pixel
    * @param y the y coordinate of the pixel
    * @param rgb the new rgb value of the pixel (alpha, red, green, blue)
    */     
  public void setBasicPixel(int x, int y, int rgb)
  {
    bufferedImage.setRGB(x,y,rgb);
  }
  
  /**
    * Method to get a pixel object for the given x and y location
    * @param x  the x location of the pixel in the picture
    * @param y  the y location of the pixel in the picture
    * @return a Pixel object for this location
    */
  public Pixel getPixel(int x, int y)
  {
    // create the pixel object for this picture and the given x and y location
    Pixel pixel = new Pixel(this,x,y);
    return pixel;
  }
 
  /**
    * Method to get a two-dimensional array of Pixels for this simple picture
    * @return a two-dimensional array of Pixel objects in row-major order.
    */
  public Pixel[][] getPixels2D()
  {
    int width = getWidth();
    int height = getHeight();
    Pixel[][] pixelArray = new Pixel[height][width];
    
    // loop through height rows from top to bottom
    for (int row = 0; row < height; row++) 
      for (int col = 0; col < width; col++) 
        pixelArray[row][col] = new Pixel(this,col,row);
      
    return pixelArray;
  }
 /**
  * Method to get a one-dimensional array of Pixels for this simple picture
  * @return a one-dimensional array of Pixel objects starting with y=0
  * to y=height-1 and x=0 to x=width-1.
  */
 public Pixel[] getPixels()
 {
   int width = getWidth();
   int height = getHeight();
   Pixel[] pixelArray = new Pixel[width * height];
   
   // loop through height rows from top to bottom
   for (int row = 0; row < height; row++) 
     for (int col = 0; col < width; col++) 
       pixelArray[row * width + col] = new Pixel(this,col,row);
    
   return pixelArray;
 }
  /**
    * Method to load the buffered image with the passed image
    * @param image  the image to use
    */
  public void load(Image image)
  {
    // get a graphics context to use to draw on the buffered image
    Graphics2D graphics2d = bufferedImage.createGraphics();
    
    // draw the image on the buffered image starting at 0,0
    graphics2d.drawImage(image,0,0,null);
    
  }
 
  /**
    * Method to read the contents of the picture from a filename  
    * without throwing errors
    * @param fileName the name of the file to write the picture to
    * @return true if success else false
    */
  public boolean load(String fileName)
  {
    try {
      // set the current picture's file name
      this.fileName = fileName;
      
      // set the extension
      int posDot = fileName.indexOf('.');
      if (posDot >= 0)
        this.extension = fileName.substring(posDot + 1);
      
      File file = new File(this.fileName);

      if (!file.canRead()) 
      {
          throw new IOException(this.fileName +
                                " could not be opened. Check that you specified the path");
      }
      
      bufferedImage = ImageIO.read(file);
      return true;

    } catch (Exception ex) {
        System.out.println("There was an error trying to open " + fileName);
        bufferedImage = new BufferedImage(600,200,
                                          BufferedImage.TYPE_INT_RGB);
        return false;
    }
          
  }
 
  /**
   * Method to create a new picture by scaling the current
   * picture by the given 
   * @param rFactor the amount to scale in the height (rows)
   * @param cFactor the amount to scale in the width (columns)
   * @return the resulting picture
   */
  public Picture scale(double rFactor, double cFactor)
  {
    // set up the scale tranform
    AffineTransform scaleTransform = new AffineTransform();
    scaleTransform.scale(cFactor,rFactor);
    
    // create a new picture object that is the right size
    Picture result = new Picture((int) (getHeight() * rFactor),
                                 (int) (getWidth() * cFactor));
    
    // get the graphics 2d object to draw on the result
    Graphics graphics = result.getGraphics();
    Graphics2D g2 = (Graphics2D) graphics;
    
    // draw the current image onto the result image scaled
    g2.drawImage(this.getImage(),scaleTransform,null);
    
    return result;
  }

  /**
    * Method to write the contents of the picture to a file with 
    * the passed name without throwing errors
    * @param fileName the name of the file to write the picture to
    * @return true if success else false
    */
  public boolean write(String fileName)
  {
    String extension = this.extension; // the default is current
    
    // create the file object
    File file = new File(fileName);
  
    // get the extension
    int posDot = fileName.indexOf('.');
    if (posDot >= 0)
      extension = fileName.substring(posDot + 1);
    
    try {
      // write the contents of the buffered image to the file as jpeg
      ImageIO.write(bufferedImage, extension, file);
      return true;
    }
    catch (Exception e)
    {
      System.out.println("There was an error writing to "+fileName);
      e.printStackTrace();
      return false;
    }
  }

   ////////////////////// methods ///////////////////////////////////////
  //precondition: amount is between 0 and 1
public void decreaseBlue(double amount)
   {
   //create a pixel array
   Pixel[]pixelArray=this.getPixels();
   //create a pixel object
   Pixel p  = null;
   //loop through all of the pixels
   for(int x=0;x<pixelArray.length;x++)
   {
   //get the current pixel
   p =pixelArray[x];
    //decrease the blue value
    p.setBlue((int)(p.getBlue()*amount));
   }
   }
   
/*
The copper-puzzle.png image is a puzzle -- it shows something famous, however the image has been distored. The true image is in the blue and green values, however all the blue and green values have all be divided by 20, so the values are very small. The red values are all just random numbers, noise added on top to obscure things. Undo these distortions to reveal the true image.

First, set the red values to 0 to get that of the way. You may be able to see the image very faintly at this point, but it is very dark. Then multiply the blue and green values by 20 to get them back approximately to their proper values. What is the famous object?
*/

} // this } is the end of class Picture, put all new methods before this
