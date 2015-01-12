package pl.exsio.plupload.examples;

import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.helper.filter.PluploadFilter;
import pl.exsio.plupload.helper.resize.PluploadImageResize;
import pl.exsio.plupload.manager.PluploadManager;

/**
 *
 * @author exsio
 */
public class UploadManagerWithImageResizeExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        PluploadManager manager = Util.createManager();
        manager.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded and resized an image: " + file.getName());
            }
        });
        manager.getUploader().addFilter(new PluploadFilter("image files", "jpg, png, jpeg"));
        manager.getUploader().setImageResize(new PluploadImageResize()
                .setEnabled(true).setCrop(true).setWidth(200).setHeight(100));
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
                + "             Notification.show(\"I've just uploaded and resized an image: \" + file.getName());\n"
                + "       }\n"
                + "});\n"
                + "manager.getUploader().addFilter(new PluploadFilter(\"image files\", \"jpg, png, jpeg\"));\n"
                + "manager.getUploader().setImageResize(new PluploadImageResize()\n"
                + "        setEnabled(true).setCrop(true).setWidth(200).setHeight(100));");
    }
}