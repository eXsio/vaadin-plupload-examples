package pl.exsio.plupload.examples;

import com.vaadin.data.Buffered;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadError;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.field.PluploadField;

/**
 *
 * @author exsio
 */
public class ValidationUploaderFieldExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        final PluploadField<byte[]> field = Util.createField(byte[].class);
        field.getUploader().setMaxFileSize("5mb");
        field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded file: " + file.getName() + " (size: " + field.getValue().length + ")");
            }
        });

        field.getUploader().addErrorListener(new Plupload.ErrorListener() {

            @Override
            public void onError(PluploadError error) {
                Notification.show("There was an error: " + error.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });

        field.addValidator(new NullValidator("the file must not be null", false));
        final Form form = new Form();
        form.addField("file", field);
        Button commit = new Button("Commit Form", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    form.commit();
                    Notification.show("Form commit successful!");
                } catch (Buffered.SourceException | Validator.InvalidValueException ex) {
                    Notification.show("There was an error during form commit: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        pane.addComponent(form);
        pane.addComponent(commit);

    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "final PluploadField<byte[]> field = new PluploadField(byte[].class);\n"
                + "field.getUploader().setMaxFileSize(\"5mb\");\n\n"
                + "//field value is an instance of byte[]\n"
                + "field.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "       @Override\n"
                + "       public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded file: \" + file.getName()\n"
                + "                 + \"(size: \" + field.getValue().length + \")\");\n"
                + "       }\n"
                + "});\n\n"
                + "//handle errors\n"
                + "field.getUploader().addErrorListener(new Plupload.ErrorListener() {\n"
                + "       @Override\n"
                + "       public void onError(PluploadError error) {\n"
                + "             Notification.show(\"There was an error: \"\n"
                + "                 + error.getMessage(), Notification.Type.ERROR_MESSAGE);\n"
                + "       }\n"
                + "});\n\n"
                + "//create form\n"
                + "final Form form = new Form();\n"
                + "       form.addField(\"file\", field);\n"
                + "       Button commit = new Button(\"Commit Form\", new Button.ClickListener() {\n"
                + "       @Override\n"
                + "       public void buttonClick(Button.ClickEvent event) {\n"
                + "             try {\n"
                + "                 form.commit();\n"
                + "                 Notification.show(\"Form commit successful!\");\n"
                + "             } catch (Buffered.SourceException | Validator.InvalidValueException ex) {\n"
                + "                 Notification.show(\"There was an error during form commit: \"\n"
                + "                      + ex.getMessage(), Notification.Type.ERROR_MESSAGE);\n"
                + "             }\n"
                + "       }\n"
                + "});");
    }
}
