$(function () {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $("#visitDate").datetimepicker({
        timepicker: false,
        format: 'Y-m-d'
    });
});

function ajaxRequest(ajaxUrl, id) {
    $.ajax({
        url: ajaxUrl,
        type: 'DELETE',
        success: function () {
            removeElement(id);
        }
    });
}