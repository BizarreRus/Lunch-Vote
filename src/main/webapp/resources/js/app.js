/* Login js */
var ajaxRestaurantUrl = 'restaurants/';

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

function removeElement(id) {
    var elem = document.getElementById(id);
    return elem.parentNode.removeChild(elem);
}

function deleteRestaurant(id) {
    $.ajax({
        url: ajaxRestaurantUrl + id,
        type: 'DELETE',
        success: function () {
            removeElement(id);
        }
    });
}

function vote(id) {
    $.ajax({
        url: ajaxRestaurantUrl + id + "/vote",
        type: 'GET',
        success: function (data) {
            incrementOrDecrement(id, 1);
            if (data) {
                var unVotedId = $.parseJSON(data).unVotedId;
                incrementOrDecrement(unVotedId, -1);
            }
        },
        error: function (request, status, error) {
            failNoty();
        }
    });
}

function incrementOrDecrement(id, trigger) {
    var badge = $("#" + id).find('span.badge');
    var count = parseInt(badge.text());
    badge.text(count + trigger);
}

function failNoty() {
    var currentdate = new Date();
    var hours =  currentdate.getHours();
    var message = hours > 10 ? "You can vote only before 11:00 AM." : "You can vote only one time per day.";
    $("#message").text(message);
    $('#errorModal').modal();
}