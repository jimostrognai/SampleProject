package com.inin.testing.testservice;

import com.google.common.util.concurrent.Uninterruptibles;
import com.inin.testing.test.BaseBrowserTest;
import com.inin.testing.testservice.ui.AndersonPage;
import com.inin.testing.ui.browsersupport.html.Link;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by brianschultz on 3/15/16.
 */
public class UITest extends BaseBrowserTest{

    protected final String userName1 = "BrianSchultz";
    protected final String userName2 = "JoshFrederick";

    /**
     * Don't do this in a real test.
     * I wanted to be able to interact with a page and load it without serving it
     * @return String
     */
    private String getUrl() {

        String filePath = "../web-project/ui/index.html";

        //converts the string to a path that will be passed properly in the format of the OS that's running
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Error(String.format("File %s does not exist", filePath));
        }

        String fullPath = null;
        try {
            fullPath = String.format("%s%s", "file://", file.getCanonicalPath());

        } catch (IOException e) {
            throw new Error(e);
        }

        return fullPath;
    }


    /***
     * Runs before each test
     * Loads your browser and goes to the webpage
     * Requirements
     *  -> Run -> Edit Configuration -> Defaults -> TestNG -> Parameters
     *      -> add environment: local
     *      -> add browser_config: chrome_localhost
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        getBrowser().goTo(getUrl());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        log.info("Page Loaded");
    }


    @Test
    public void testVerifyLink(){
        Link link = new AndersonPage().getUserNameLink(userName1);
        Assert.assertTrue(link.text().equals(userName1), String.format("Expected '%s', but received '%s'", userName1, link.text()));
    }



}
