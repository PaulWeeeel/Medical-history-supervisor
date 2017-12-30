function uploadImg(url, imgData, callback) {
    $.ajax({
        type: "POST",
        url: "/recognize/face",
        data: JSON.stringify({
            'image': imgData
        }),
        dataType: "json",
        contentType:"application/json;charset=utf-8",
        timeout: 5000,
        success: function (response) {
            if(response.status == "200") {
                $('#patient-id').html(response.result.id);
                $('#patient-name').html(response.result.name);
                $('#last-connect').html(response.time);

                document.getElementById("patient-detail").removeAttribute("disabled");
            }
            else{

            }
        },
        error: function () {
            clearInterval(timer);
            alert("与服务器连接错误，请刷新页面！");
        },
        complete: function () {

        }
    });
}
