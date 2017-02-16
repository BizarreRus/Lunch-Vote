function deleteMenu(restaurantId, menuId) {
    var ajaxUrl = 'restaurants/' + restaurantId + '/menus/' + menuId;
    ajaxRequest(ajaxUrl, menuId);
}

function removeElement(id) {
    var elem = document.getElementById(id);
    var parent = elem.parentNode;
    if (parent.childElementCount == 1){
        jQuery('<div/>', {
            text: 'List is empty.',
            class: 'panel-body'
        }).appendTo(parent);
    }
    return parent.removeChild(elem);
}