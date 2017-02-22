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
                var unVotedId = $.parseJSON(data);
                refreshBadgeElement(unVotedId, -1);
            }
        }
    });
}

function refreshBadgeElement(id, trigger) {
    var badge = $("#" + id).find('span.badge');
    var count = parseInt(badge.text());
    badge.text(count + trigger);
}

function removeElement(id) {
    var elem = document.getElementById(id);
    var parent = elem.parentNode;
    if (parent.childElementCount == 1) {
        $('#chooseMsg').remove();
        jQuery('<div/>', {
            text: 'List of restaurants is empty for today.',
            class: 'description-message'
        }).appendTo(parent);
    }
    return parent.removeChild(elem);
}

function failNoty(event, jqXHR, options, jsExc) {
    var errorInfo = $.parseJSON(jqXHR.responseText);
    var mess = errorInfo.detail;
    $("#message").text(mess);
    $('#errorModal').modal();
}