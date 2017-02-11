function deleteDish(menuId, dishId) {
    var ajaxUrl = 'restaurants/mock/menus/' + menuId + '/dishes/' + dishId;
    ajaxRequest(ajaxUrl, dishId);
}