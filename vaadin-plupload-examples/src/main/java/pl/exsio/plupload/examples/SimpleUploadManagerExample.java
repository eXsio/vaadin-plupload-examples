package pl.exsio.plupload.examples;

import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.manager.PluploadManager;

/**
 *
 * @author exsio
 */
public class SimpleUploadManagerExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        PluploadManager manager = Util.createManager();
        manager.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded file: " + file.getName());
            }
        });
        pane.addComponent(manager);
    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "PluploadManager manager = new PluploadManager();\n"
                + "manager.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, \"5mb\");\n"
                + "manager.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "       @Override\n"
                + "       public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded file: \" + file.getName());\n"
                + "       }\n"
                + "});");
    }
}