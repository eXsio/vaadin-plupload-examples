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
