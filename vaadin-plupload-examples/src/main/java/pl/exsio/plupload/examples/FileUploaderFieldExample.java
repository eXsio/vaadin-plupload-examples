package pl.exsio.plupload.examples;

import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import java.io.File;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.PluploadOption;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.field.PluploadField;

/**
 *
 * @author exsio
 */
public class FileUploaderFieldExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        final PluploadField<File> field = Util.createField(File.class);
        field.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, "5mb");
        field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded file: " + file.getName() + " (local name: " + field.getValue().getName() + ")");
            }
        });

        pane.addComponent(field);

    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "final PluploadField<File> field = new PluploadField(File.class);\n"
                + "field.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, \"5mb\");\n\n"
                + "//field value is an instance of java.io.File\n"
                + "field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "     @Override\n"
                + "         public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded file: \" + file.getName()\n"
                + "                 + \"(local name: \" + field.getValue().getName() + \")\");\n"
                + "         }\n"
                + "     });");
    }
}
