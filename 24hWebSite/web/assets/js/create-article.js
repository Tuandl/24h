$(function () {
    $('textarea#new-article').froalaEditor({
        imageUploadURL: '/24hWebSite/upload_image',
        imageUploadParams: {
            id: 'my_editor'
        },
        imageAllowedTypes: ['jpeg', 'jpg', 'png'],

        fileUploadURL: '/24hWebSite/upload_file',
        fileUploadParams: {
            id: 'my_editor'
        },
        imageManagerLoadURL: '/24hWebSite/load_images',
        imageManagerDeleteURL: "/24hWebSite/delete_image",
        imageManagerDeleteMethod: "POST"
    })
            // Catch image removal from the editor.
            .on('froalaEditor.image.removed', function (e, editor, $img) {
                $.ajax({
                    // Request method.
                    method: "POST",
                    // Request URL.
                    url: "/24hWebSite/delete_image",
                    // Request params.
                    data: {
                        src: $img.attr('src')
                    }
                })
                        .done(function (data) {
                            console.log('image was deleted');
                        })
                        .fail(function (err) {
                            console.log('image delete problem: ' + JSON.stringify(err));
                        })
            })
            // Catch image removal from the editor.
            .on('froalaEditor.file.unlink', function (e, editor, link) {
                $.ajax({
                    // Request method.
                    method: "POST",
                    // Request URL.
                    url: "/24hWebSite/delete_file",
                    // Request params.
                    data: {
                        src: link.getAttribute('href')
                    }
                })
                        .done(function (data) {
                            console.log('file was deleted');
                        })
                        .fail(function (err) {
                            console.log('file delete problem: ' + JSON.stringify(err));
                        })
            })
            .on('froalaEditor.image.beforeUpload', function (e, editor, images) {
                // Return false if you want to stop the image upload.
//                alert("before upload");
            })
            .on('froalaEditor.image.uploaded', function (e, editor, response) {
                // Image was uploaded to the server.
//                window.alert("uploaded");
            })
            .on('froalaEditor.image.inserted', function (e, editor, $img, response) {
                // Image was inserted in the editor.
            })
            .on('froalaEditor.image.replaced', function (e, editor, $img, response) {
                // Image was replaced in the editor.
            })
            .on('froalaEditor.image.error', function (e, editor, error, response) {
                // Bad link.
                if (error.code == 1) {
                    alert("bad link");
                }

                // No link in upload response.
                else if (error.code == 2) {
                    alert("nothing in upload response");
                }

                // Error during image upload.
                else if (error.code == 3) {
                    alert("error during image upload")
                }

                // Parsing response failed.
                else if (error.code == 4) {
                    alert("parsing response failed");
                }

                // Image too text-large.
                else if (error.code == 5) {
                    alert("image too text-large");
                }

                // Invalid image type.
                else if (error.code == 6) {
                    alert("Invalid image type.");
                }

                // Image can be uploaded only to same domain in IE 8 and IE 9.
                else if (error.code == 7) {
                    alert("Image can be uploaded only to same domain in IE 8 and IE 9");
                }
                alert(error.message);
                // Response contains the original server response to the request if available.
            })
    ;
});