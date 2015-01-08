package pl.exsio.plupload.examples.util;

import com.vaadin.server.FontAwesome;
import java.io.File;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.PluploadOption;
import pl.exsio.plupload.field.PluploadField;
import pl.exsio.plupload.manager.PluploadManager;

/**
 *
 * @author exsio
 */
public class Util {

    public static PluploadManager createManager() {
        PluploadManager manager = new PluploadManager();
        manager.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, "5mb");
        manager.getUploader().addUploadCompleteListener(getDisposeListener(manager.getUploader()));
        return manager;
    }

    public static PluploadField createField(Class fieldClass) {
        PluploadField field = new PluploadField(fieldClass);
        field.getUploader().setOption(PluploadOption.MAX_FILE_SIZE, "5mb");
        field.getUploader().addUploadCompleteListener(getDisposeListener(field.getUploader()));
        return field;
    }

    public static Plupload createUploader() {
        Plupload uploader = new Plupload("Browse", FontAwesome.FILES_O);
        uploader.setOption(PluploadOption.MAX_FILE_SIZE, "5mb");
        uploader.addUploadCompleteListener(getDisposeListener(uploader));
        return uploader;
    }

    private static Plupload.UploadCompleteListener getDisposeListener(final Plupload uploader) {
        return new Plupload.UploadCompleteListener() {

            @Override
            public void onUploadComplete() {
                for (PluploadFile f : uploader.getUploadedFiles()) {
                    f.getUploadedFile().delete();
                }
            }
        };
    }
}
