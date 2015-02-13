package pl.exsio.plupload.examples;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadError;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.helper.filter.PluploadFilter;
import pl.exsio.plupload.manager.PluploadManager;

/**
 *
 * @author exsio
 */
public class UploadManagerWithFileFilterExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        PluploadManager manager = Util.createManager();
        manager.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded an audio file: " + file.getName());
            }
        });
        manager.getUploader().addFilter(new PluploadFilter("audio files", "mp3, flac, wav"));

        VerticalLayout dropZone = new VerticalLayout() {
            {
                addComponent(new Label("Additional drop zone for music files"));
                setId("music-drop-zone");
            }
        };

        manager.getUploader().addDropZone(dropZone);

        manager.getUploader().addErrorListener(new Plupload.ErrorListener() {

            @Override
            public void onError(PluploadError error) {
                Notification.show("There was an error: " + error.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
        pane.addComponent(manager);
        pane.addComponent(dropZone);
        manager.getUploader().setChunkSize("3mb");
    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "PluploadManager manager = new PluploadManager();\n"
                + "manager.getUploader().setMaxFileSize(\"5mb\");\n"
                + "manager.getUploader().addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "       @Override\n"
                + "       public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded an audio file: \" + file.getName());\n"
                + "       }\n"
                + "});\n\n"
                + "VerticalLayout dropZone = new VerticalLayout() {\n"
                + "       {\n"
                + "             addComponent(new Label(\"Additional drop zone for music files\"));\n"
                + "             //Very important! A drop zone must have an unique id!\n"
                + "             setId(\"music-drop-zone\");\n"
                + "        }\n"
                + "};\n"
                + "manager.getUploader().addDropZone(dropZone);\n\n"
                + "manager.getUploader().addErrorListener(new Plupload.ErrorListener() {\n"
                + "       @Override\n"
                + "       public void onError(PluploadError error) {\n"
                + "             Notification.show(\"There was an error: \"\n"
                + "                 + error.getMessage(), Notification.Type.ERROR_MESSAGE);\n"
                + "       }\n"
                + "});\n\n"
                + "manager.getUploader().setChunkSize(\"3mb\");\n"
                + "manager.getUploader().addFilter(new PluploadFilter(\"audio files\", \"mp3, flac, wav\"));");
    }
}
