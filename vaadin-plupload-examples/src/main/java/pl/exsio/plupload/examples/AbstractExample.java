package pl.exsio.plupload.examples;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorLanguage;

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
        this.setExpandRatio(code, 0.75f);
        this.setExpandRatio(manager, 0.25f);
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
        CodeMirror code = new CodeMirror();
        this.decorateCodePane(code);
        code.setLanguage(CodeMirrorLanguage.JAVA);
        layout.addComponent(code);
        layout.setSizeFull();

        code.setWidth("900px");
        code.setHeight("500px");
        return layout;
    }
}
