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
        manager.getUploader().addFilter(new PluploadFilter("audio files", "mp3,flac,wav"));

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
                Notification.show("There was an error: " + error.getMessage() + " (" + error.getType() + ")", Notification.Type.ERROR_MESSAGE);
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
                + "                 + error.getMessage() + \" (\" + error.getType() + \")\",\n"
                + "                 Notification.Type.ERROR_MESSAGE);\n"
                + "       }\n"
                + "});\n\n"
                + "manager.getUploader().setChunkSize(\"3mb\");\n"
                + "manager.getUploader().addFilter(new PluploadFilter(\"audio files\", \"mp3,flac,wav\"));");
    }
}
