
package pl.exsio.plupload.examples.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author exsio
 */
public class Home extends VerticalLayout {
    
    public Home() {
        Label l = new Label("<h1>Welcome to the Plupload add-on for Vaadin examples!</h1><p>Below You have couple of basic examples, covering main features of the add-on.<br>"
                + "For the sake of performance and security the uploaded file size in all examples is restricted to 5MB. <br>"
                + "After the upload is completed, files are immediately deleted.<br>"
                + "For your own comfort we recommend that You don't upload files containing fragile/secret informations.<br><br>"
                + "Cheers!</p>",ContentMode.HTML);
        this.addComponent(l);
        this.setComponentAlignment(l, Alignment.MIDDLE_CENTER);
        this.setMargin(true);
    }
}
