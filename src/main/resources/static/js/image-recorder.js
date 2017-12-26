function uploadImg(url, img, callback) {
    var xhr = new XMLHttpRequest();
    if (callback) {
        xhr.upload.addEventListener('progress', function (e) {
            callback('uploading', e);
        }, false);
        xhr.addEventListener('load', function (e) {
            callback('ok', e);
        }, false);
        xhr.addEventListener('error', function (e) {
            callback('error', e);
        }, false);
        xhr.addEventListener('abort', function (e) {
            callback('cancel', e);
        }, false);
    }
    xhr.open('POST', url);
    xhr.send(img);
}
