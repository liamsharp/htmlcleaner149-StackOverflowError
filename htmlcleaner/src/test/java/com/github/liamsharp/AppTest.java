package com.github.liamsharp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test to expose https://sourceforge.net/p/htmlcleaner/bugs/149/
     */
    public void testStackOverflowError()
    {
        final String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator") + "test/resources/sof.html";
        try
        {
            System.out.println("filePath: " + filePath);
            final String html = FileUtils.readFileToString(new File(filePath));
            new HtmlCleaner().clean(html);
        }
        catch (StackOverflowError e)
        {
            System.err.println("StackOverflowError: " + e);
            fail("Stack overflowed!");
        }
        catch (IOException e)
        {
            System.err.println("Unable to load file: " + filePath + ", " + e);
            fail("Couldn't find file!");
        }
    }
}
