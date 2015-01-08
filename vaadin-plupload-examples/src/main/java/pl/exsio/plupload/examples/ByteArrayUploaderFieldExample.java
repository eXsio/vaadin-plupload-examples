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
public class ByteArrayUploaderFieldExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        final PluploadField<byte[]> field = Util.createField(byte[].class);
        field.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, "5mb");
        field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded file: " + file.getName() + " (size: " + field.getValue().length + ")");
            }
        });

        pane.addComponent(field);

    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "final PluploadField<byte[]> field = new PluploadField(byte[].class);\n"
                + "field.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, \"5mb\");\n\n"
                + "//field value is an instance of byte[]\n"
                + "field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "     @Override\n"
                + "         public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded file: \" + file.getName()\n"
                + "                 + \"(size: \" + field.getValue().length + \")\");\n"
                + "         }\n"
                + "     });");
    }
}
