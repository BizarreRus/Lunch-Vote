function deleteMenu(restaurantId, menuId) {
    var ajaxUrl = 'restaurants/' + restaurantId + '/menus/' + menuId;
    ajaxRequest(ajaxUrl, menuId);

}