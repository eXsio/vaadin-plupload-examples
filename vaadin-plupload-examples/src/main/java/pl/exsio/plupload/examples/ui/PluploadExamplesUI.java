/* 
 * The MIT License
 *
 * Copyright 2015 exsio.
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
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;
import pl.exsio.plupload.examples.AdvancedUploaderExample;
import pl.exsio.plupload.examples.ValidationByteArrayUploaderFieldExample;
import pl.exsio.plupload.examples.FileUploaderFieldExample;
import pl.exsio.plupload.examples.SimpleUploadManagerExample;
import pl.exsio.plupload.examples.SimpleUploaderExample;
import pl.exsio.plupload.examples.UploadManagerWithFileFilterExample;
import pl.exsio.plupload.examples.UploadManagerWithImageResizeExample;

@Theme("valo")
@Title("Plupload add-on for Vaadin examples")
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
        this.setSizeFull();

        TabSheet container = new TabSheet();
        container.addTab(new Home(), "Home");
        container.addTab(new SimpleUploaderExample(), "Simple \"Plupload\"");
        container.addTab(new AdvancedUploaderExample(), "Advanced \"Plupload\"");
        container.addTab(new SimpleUploadManagerExample(), "Simple \"PluploadManager\"");
        container.addTab(new UploadManagerWithFileFilterExample(), "\"PluploadManager\" with file filter and drop zone");
        container.addTab(new UploadManagerWithImageResizeExample(), "\"PluploadManager\" with image resize");
        container.addTab(new FileUploaderFieldExample(), "\"PluploadField\" with File value");
        container.addTab(new ValidationByteArrayUploaderFieldExample(), "\"PluploadField\" with byte[] value and form validation");

        mainLayout.addComponent(container);
        container.setSizeFull();
        mainLayout.setSizeFull();

        this.setContent(mainLayout);

    }
}
