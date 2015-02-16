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
package pl.exsio.plupload.examples;

import com.vaadin.server.Page;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorLanguage;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorTheme;

/**
 *
 * @author exsio
 */
public abstract class AbstractExample extends HorizontalLayout {

    public AbstractExample() {

        this.setSpacing(true);
        this.setMargin(true);
        this.setSizeFull();
        VerticalLayout manager = this.getActionPane();
        VerticalLayout code = this.getCodePane();
        this.addComponent(manager);
        this.addComponent(code);
        this.setExpandRatio(code, 0.65f);
        this.setExpandRatio(manager, 0.35f);
    }

    private VerticalLayout getActionPane() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setHeightUndefined();
        this.decorateActionPane(layout);
        return layout;
    }

    protected abstract void decorateActionPane(VerticalLayout pane);

    protected abstract void decorateCodePane(CodeMirror code);

    private VerticalLayout getCodePane() {

        VerticalLayout layout = new VerticalLayout();
        final CodeMirror code = new CodeMirror();
        this.decorateCodePane(code);
        code.setLanguage(CodeMirrorLanguage.JAVA);
        layout.addComponent(code);
        layout.setSizeFull();
        code.setTheme(CodeMirrorTheme.ECLIPSE);
        code.setSizeFull();
        // code.setHeight("500px");
        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {

            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                code.setHeight((event.getHeight() - 120) + "px");
                 code.setWidth((event.getWidth() - 550) + "px");
            }
        });
        code.setHeight((Page.getCurrent().getBrowserWindowHeight() - 120) + "px");
        code.setWidth((Page.getCurrent().getBrowserWindowWidth() - 550) + "px");
        return layout;
    }
}
