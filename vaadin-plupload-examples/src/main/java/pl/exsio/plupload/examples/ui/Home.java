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
        Label l = new Label("<h1>Welcome to the Plupload add-on for Vaadin examples!</h1><p>You'll find here a couple of basic examples, covering main features of the add-on.<br>"
                + "Here are a couple of things, that you should know about:</p><ul>"
                + "<li>For the sake of performance and security the uploaded <b>file size</b> in all examples is restricted to <b>5MB</b></li>"
                + "<li>After the upload is completed, files are <b>immediately deleted</b></li>"
                + "<li>Despite the above, for your own comfort it is recommended, that You <b>don't upload</b> files containing <b>fragile/secret informations</b></li>"
                + "<li><b>PluploadManager</b> and <b>PluploadField</b> are 100% server-side components created around the basic <b>Plupload</b> component</li>"
                + "<li>Source code of the add-on is available <a target=\"_blank\" href=\"https://github.com/eXsio/vaadin-plupload\">here</a></li>"
                + "<li>Source code of the examples app is available <a target=\"_blank\" href=\"https://github.com/eXsio/vaadin-plupload-examples\">here</a></li>"
                + "</ul><h3>Cheers!</h3>", ContentMode.HTML);
        this.addComponent(l);
        this.setComponentAlignment(l, Alignment.MIDDLE_CENTER);
        this.setMargin(true);
    }
}
