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
package pl.exsio.plupload.examples.util;

import com.vaadin.server.FontAwesome;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.field.PluploadField;
import pl.exsio.plupload.manager.PluploadManager;

/**
 *
 * @author exsio
 */
public class Util {

    public static PluploadManager createManager() {
        PluploadManager manager = new PluploadManager();
        manager.getUploader().setMaxFileSize("5mb");
        manager.getUploader().addUploadCompleteListener(getDisposeListener(manager.getUploader()));
        return manager;
    }

    public static PluploadField createField(Class fieldClass) {
        PluploadField field = new PluploadField(fieldClass);
        field.getUploader().setMaxFileSize("5mb");
        field.getUploader().addUploadCompleteListener(getDisposeListener(field.getUploader()));
        return field;
    }

    public static Plupload createUploader() {
        Plupload uploader = new Plupload("Browse", FontAwesome.FILES_O);
        uploader.setMaxFileSize("5mb");
        uploader.addUploadCompleteListener(getDisposeListener(uploader));
        return uploader;
    }

    private static Plupload.UploadCompleteListener getDisposeListener(final Plupload uploader) {
        return new Plupload.UploadCompleteListener() {

            @Override
            public void onUploadComplete() {
                for (PluploadFile f : uploader.getUploadedFiles()) {
                    if (f.getUploadedFile() != null && f.getUploadedFile().exists()) {
                        f.getUploadedFile().delete(); 
                    }
                }
            }
        };
    }
}
