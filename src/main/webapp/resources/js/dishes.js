function deleteDish(menuId, dishId) {
    var ajaxUrl = 'restaurants/mock/menus/' + menuId + '/dishes/' + dishId;
    ajaxRequest(ajaxUrl, dishId);
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