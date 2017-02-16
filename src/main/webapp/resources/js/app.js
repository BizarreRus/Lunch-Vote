$(function () {
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