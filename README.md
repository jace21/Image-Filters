# Image-Filters
Filters for image enhancements using convolution matrices designed in Java

#Features
The type of image enhancements available as of currently are blur, edge detection, sharpen, emboss, contrast enhancement, median filter which fixes dots in an image, and black/white. 

#Design
Images are read into a Java bufferedimage and a 3x3 size matrix is applied to the images red, green, and blue channels of a pixel. The 3x3  matrix may vary depending on the type of filter that is being applied. For Contrast Enhancement a different method called histogram equalization is applied to add more depth to the range of colors of an image by spreading out the values of a pixel so that the reds, greens, and blue values are not as condensed, i.e. stretching the histogram. 

#Methodology
Images are often not perfect without a little enhancement. Most image enhancements are done via a kernel. A prime example used today and by companies like Apple and Samsung are image stabilization through a convolution matrix, also known as a kernel. Meaning they modulate images using a stabilization matrix to readjust a blurred image into a clear image. We can modify the quality of an image such as color, size, and many other things through the use of kernels.

#Upcoming Features
Feature Detection is an eventual idea that I would like to dive into because a majority of Digital Image Processing is done through feature detection as we can see today in cell phone cameras and sensors. I feel that Feature detection is the future because being able to determine what an object's features are allows us to understand what the picture is and how we can process it. Another possible feature is a port over to android because I want to learn more about android development in an ever growing mobile industry.

##Example
Powershell:	java filters w example.jpg example1.jpg
For other options: java filters
