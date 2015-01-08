/* 
 * The MIT License
 *
 * Copyright 2014 exsio.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.exsio.plupload.examples.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;
import pl.exsio.plupload.examples.AdvancedUploaderExample;
import pl.exsio.plupload.examples.ByteArrayUploaderFieldExample;
import pl.exsio.plupload.examples.FileUploaderFieldExample;
import pl.exsio.plupload.examples.SimpleUploadManagerExample;
import pl.exsio.plupload.examples.SimpleUploaderExample;
import pl.exsio.plupload.examples.UploadManagerWithFileFilterExample;
import pl.exsio.plupload.examples.UploadManagerWithImageResizeExample;

@Theme("valo")
public class PluploadExamplesUI extends UI {

    @WebServlet(value = "/*", asyncSupported = false)
    @VaadinServletConfiguration(productionMode = false, ui = PluploadExamplesUI.class, widgetset = "pl.exsio.plupload.examples.ui.PluploadExamplesWidgetSet")
    public static class DevServlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        Accordion acc = new Accordion();
        acc.addTab(new Home(), "Home");
        acc.addTab(new SimpleUploaderExample(), "Simple \"Plupload\" example");
        acc.addTab(new AdvancedUploaderExample(), "\"Plupload\" example with all options");
        acc.addTab(new SimpleUploadManagerExample(), "Simple \"UploadManager\" example");
        acc.addTab(new UploadManagerWithFileFilterExample(), "\"UploadManager\" with file filter example");
        acc.addTab(new UploadManagerWithImageResizeExample(), "\"UploadManager\" with image resize example");
        acc.addTab(new FileUploaderFieldExample(), "\"UploadField\" example with java.io.File value");
        acc.addTab(new ByteArrayUploaderFieldExample(), "\"UploadField\" example with byte[] value");

        mainLayout.addComponent(acc);
        acc.setSizeFull();
        mainLayout.setSizeFull();

        this.setContent(mainLayout);

    }
}
