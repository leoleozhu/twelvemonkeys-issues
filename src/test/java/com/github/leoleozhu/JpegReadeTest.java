package com.github.leoleozhu;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.util.Iterator;


public class JpegReadeTest {


    private String[] jpgs = {
            "images/1.jpg",
            "images/2.jpg",
            "images/3.jpg",
            "images/4.jpg",
            "images/5.jpg",
    };

    @Test
    public void testJpegReader() throws Exception {
        for (String jpg:jpgs) {
            System.out.println("==== Reading " + jpg + " ====");
            ImageIO.read(resourceFile(jpg));
        }
    }


    @Test
    public void testWithAllJpegReaders() throws Exception {

        Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByFormatName("JPEG");

        while(imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            for (String jpg : jpgs) {
                System.out.println(String.format("==== Reading %s with %s ====", jpg, reader.getClass()));

                try(ImageInputStream iis = ImageIO.createImageInputStream(resourceFile(jpg))) {
                    reader.setInput(iis);
                    reader.read(0, reader.getDefaultReadParam());
                } catch(Exception e) {
                    System.out.println("Error:" + e.getMessage());
                }

            }
        }
    }




    private File resourceFolder = new File("src/test/resources");

    private File resourceFile(String name) {
        return new File(resourceFolder, name);
    }



}
