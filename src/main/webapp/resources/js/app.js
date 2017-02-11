/* Login js */
$(function () {

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
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

function removeElement(id) {
    var elem = document.getElementById(id);
    var parent = elem.parentNode;
    if (parent.childElementCount == 1){
        jQuery('<div/>', {
            text: 'List is empty',
            class: 'panel-body'
        }).appendTo(parent);
    }
    return parent.removeChild(elem);
}