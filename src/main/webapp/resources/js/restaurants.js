var ajaxRestaurantUrl = 'restaurants/';

function deleteRestaurant(id) {
    var ajaxUrl = ajaxRestaurantUrl + id;
    ajaxRequest(ajaxUrl, id);
}

function vote(id) {
    $.ajax({
        url: ajaxRestaurantUrl + id + "/vote",
        type: 'GET',
        success: function (data) {
            refreshBadgeElement(id, 1);
            if (data) {
                var myObjJSON = JSON.stringify(data);
                var unVotedId = $.parseJSON(myObjJSON).unVotedId;
                refreshBadgeElement(unVotedId, -1);
            }
        },
        error: function (request, status, error) {
            failNoty();
        }
    });
}

function refreshBadgeElement(id, trigger) {
    var badge = $("#" + id).find('span.badge');
    var count = parseInt(badge.text());
    badge.text(count + trigger);
}

function failNoty() {
    var currentdate = new Date();
    var hours = currentdate.getHours();
    var message = hours > 10 ? "You can vote only before 11:00 AM." : "You can vote only one time per day.";
    $("#message").text(message);
    $('#errorModal').modal();
}