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

import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import pl.exsio.plupload.Plupload;
import pl.exsio.plupload.PluploadError;
import pl.exsio.plupload.PluploadFile;
import pl.exsio.plupload.examples.util.Util;
import pl.exsio.plupload.helper.filter.PluploadFilter;
import pl.exsio.plupload.helper.resize.PluploadImageResize;

/**
 *
 * @author exsio
 */
public class AdvancedUploaderExample extends AbstractExample {

    @Override
    protected void decorateActionPane(VerticalLayout pane) {
        final Plupload uploader = Util.createUploader();
        uploader.setChunkSize("2mb");
        uploader.setMaxRetries(5);
        uploader.setMultiSelection(true);
        uploader.setPreventDuplicates(true);
        uploader.addFilter(new PluploadFilter("image files", "jpg,png,jpeg"));
        uploader.setImageResize(new PluploadImageResize().setEnabled(true)
                .setCrop(true).setHeight(100).setWidth(200));
        uploader.addFileUploadedListener(new Plupload.FileUploadedListener() {

            @Override
            public void onFileUploaded(PluploadFile file) {
                Notification.show("I've just uploaded file: " + file.getName());
            }
        });

        final Label info = new Label();
        uploader.addUploadProgressListener(new Plupload.UploadProgressListener() {

            @Override
            public void onUploadProgress(PluploadFile file) {
                info.setValue("I'm uploading " + file.getName() + " and I'm at " + file.getPercent() + "%");
            }
        });

        uploader.addFilesAddedListener(new Plupload.FilesAddedListener() {

            @Override
            public void onFilesAdded(PluploadFile[] files) {
                uploader.start();
            }
        });

        uploader.addUploadCompleteListener(new Plupload.UploadCompleteListener() {

            @Override
            public void onUploadComplete() {
                info.setValue("upload is completed!");
            }
        });

        uploader.addErrorListener(new Plupload.ErrorListener() {

            @Override
            public void onError(PluploadError error) {
                Notification.show("There was an error: " + error.getMessage() + " (" + error.getType() + ")", Notification.Type.ERROR_MESSAGE);
            }
        });

        ComboBox chunkSizeChoice = new ComboBox("Choose chunk size");
        for (double i = 0.5; i <= 3; i += 0.5) {
            chunkSizeChoice.addItem(i + "mb");
        }
        chunkSizeChoice.setNullSelectionAllowed(false);
        chunkSizeChoice.setValue("2.0mb");
        chunkSizeChoice.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String chunkSize = event.getProperty().getValue().toString();
                uploader.setChunkSize(chunkSize);
                Notification.show("Chunk size was changed to " + chunkSize);
            }
        });

        pane.setSpacing(true);
        pane.addComponent(chunkSizeChoice);
        pane.addComponent(uploader);
        pane.addComponent(info);
    }

    @Override
    protected void decorateCodePane(CodeMirror code) {
        code.setCode(""
                + "final Plupload uploader = new Plupload(\"Browse\", FontAwesome.FILES_O);\n"
                + "final Label info = new Label();\n\n"
                + "uploader.setMaxFileSize(\"5mb\");\n\n"
                + "//show notification after file is uploaded\n"
                + "uploader.addFileUploadedListener(new Plupload.FileUploadedListener() {\n"
                + "       @Override\n"
                + "       public void onFileUploaded(PluploadFile file) {\n"
                + "             Notification.show(\"I've just uploaded file: \" + file.getName());\n"
                + "       }\n"
                + "});\n\n"
                + "//update upload progress\n"
                + "uploader.addUploadProgressListener(new Plupload.UploadProgressListener() {\n"
                + "       @Override\n"
                + "       public void onUploadProgress(PluploadFile file) {\n"
                + "             info.setValue(\"I'm uploading \"+file.getName()\n"
                + "                 +\" and I'm at \"+file.getPercent()+\"%\");\n"
                + "       }\n"
                + "});"
                + "});\n\n"
                + "//autostart the uploader after addind files\n"
                + "uploader.addFilesAddedListener(new Plupload.FilesAddedListener() {\n"
                + "       @Override\n"
                + "       public void onFilesAdded(PluploadFile[] files) {\n"
                + "             uploader.start();\n"
                + "       }\n"
                + "});\n\n"
                + "//notify, when the upload process is completed\n"
                + "uploader.addUploadCompleteListener(new Plupload.UploadCompleteListener() {\n"
                + "       @Override\n"
                + "       public void onUploadComplete() {\n"
                + "             info.setValue(\"upload is completed!\");\n"
                + "       }\n"
                + "});\n\n"
                + "//handle errors\n"
                + "uploader.addErrorListener(new Plupload.ErrorListener() {\n"
                + "       @Override\n"
                + "       public void onError(PluploadError error) {\n"
                + "             Notification.show(\"There was an error: \"\n"
                + "                 + error.getMessage() + \" (\" + error.getType() + \")\",\n"
                + "                 Notification.Type.ERROR_MESSAGE);\n"
                + "       }\n"
                + "});\n\n"
                + "uploader.setChunkSize(\"2mb\");\n"
                + "uploader.setMaxRetries(5);\n"
                + "uploader.setMultiSelection(true);\n"
                + "uploader.setPreventDuplicates(true);\n"
                + "uploader.addFilter(new PluploadFilter(\"image files\", \"jpg,png,jpeg\"));\n"
                + "uploader.setImageResize(new PluploadImageResize().setEnabled(true)\n"
                + "        .setCrop(true).setHeight(100).setWidth(200));\n\n"
                + "//create combo for chunk size change\n"
                + "ComboBox chunkSizeChoice = new ComboBox(\"Choose chunk size\");\n"
                + "for (double i = 0.5; i <= 3; i += 0.5) {\n"
                + "    chunkSizeChoice.addItem(i + \"mb\");\n"
                + "}\n"
                + "chunkSizeChoice.setNullSelectionAllowed(false);\n"
                + "chunkSizeChoice.setValue(\"2.0mb\");\n"
                + "chunkSizeChoice.addValueChangeListener(new Property.ValueChangeListener() {\n"
                + "    @Override\n"
                + "    public void valueChange(Property.ValueChangeEvent event) {\n"
                + "        String chunkSize = event.getProperty().getValue().toString();\n"
                + "        uploader.setChunkSize(chunkSize);\n"
                + "        Notification.show(\"Chunk size was changed to \" + chunkSize);\n"
                + "    }\n"
                + "});");
    }
}
