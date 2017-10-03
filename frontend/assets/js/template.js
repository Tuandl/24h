$(document).ready(function() {
    setTextCurrentDate($(".wrapper .header h4"));
    // $(window).resize(function() {
    while (checkLineBreakOfCategory($(".category-menu .navbar-header"),
            $("#category-navbar"))) {
        var countElement = $(".category-menu .nav.navbar-nav > li").length - 1;
        var $lastElement = $('.category-menu .nav.navbar-nav > li:nth-child(' + countElement + ')');
        var $dropdown = $(".category-menu .dropdown");
        putItemIntoDropdown($lastElement, $dropdown);
    }
    // });
});

/**
 * return true if two element stand in different line
 * @param {Object} element1 
 * @param {Object} element2 
 */
function checkLineBreakOfCategory(element1, element2) {
    var top1 = element1.position().top;
    var top2 = element2.position().top;
    if (top2 - top1 > 10) {
        return true;
    } else {
        return false;
    }
}

/**
 * Move element into dropdown
 * @param {*} element  
 * @param {*} dropdown
 */
function putItemIntoDropdown($element, $dropdown) {
    $dropdown = $dropdown.children("ul");
    $element.appendTo($dropdown);
}

/**
 * Update text in element into current date
 * @param {Jquery selector} element 
 */
function setTextCurrentDate(element) {
    var date = new Date();
    var dayInWeek = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
    var result = dayInWeek[date.getDay()] + ", ngày " + date.getDate() + ", tháng " +
        (date.getMonth() + 1) + ", " + date.getFullYear();
    element.text(result);
}