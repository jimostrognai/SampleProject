package com.inin.testing.testservice.ui;

import com.inin.testing.ui.browsersupport.html.BaseElement;
import com.inin.testing.ui.browsersupport.html.Button;
import com.inin.testing.ui.browsersupport.html.Link;

/**
 * Created by brianschultz on 3/15/16.
 */
public class AndersonPage {

    Integer defaultTimeout = 5;

    //selenium uses lazy evaluation, meaning that it doesn't try to locate it until you interact with it.
    //Items that have a static css class, attr, or id can be safely referenced here
    Button refreshButton = new Button(BaseElement.LOCATOR_TYPE.CSS_SELECTOR, "[data-t=refresh-button]");


    /***
     * Gets the specific link for a username
     * This is an example of an attr that's dynamically defined.  You'd want to reference it in the method
     * @param userName
     * @return Link
     */
    public Link getUserNameLink(String userName){
        Link nameLink = new Link(BaseElement.LOCATOR_TYPE.CSS_SELECTOR, String.format("[data-t=user-%s]", userName));
        nameLink.waitUntilDisplayed(defaultTimeout);
        return nameLink;
    }

    /***
     * clicks the refresh button
     */
    public void clickRefreshButton(){
        refreshButton.waitUntilCanInteract(defaultTimeout);
        refreshButton.click();
    }




}
