$(document).ready(function() {
    setTextGreeting($(".wrapper .header .title"));
    setTextCurrentDate($(".wrapper .header h4"));
});

/**
 * put element type jquery selector 
 * up update it's text
 * Let text used to update is txt
 * if 0 <= current hour < 12, txt = 'Chào buổi sáng'
 * if 12 <= current hour < 18, txt = 'Chào buổi chiều'
 * if 18 <= current hour < 24, txt = 'Chào buổi tối' 
 */
function setTextGreeting(element) {
    var date = new Date();
    var hour = date.getHours();
    if (hour < 12) {
        element.text('Chào buổi sáng');
    } else if (hour < 18) {
        element.text('Chào buổi chiều');
    } else {
        element.text('Chào buổi tối');
    }
}

/**
 * Update text in element into current date
 * @param {Jquery selector} element 
 */
function setTextCurrentDate(element) {
    var date = new Date();
    var dayInWeek = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
    var result = dayInWeek[date.getDay()] + ", ngày " + date.getDate() + ", tháng " +
        date.getMonth() + ", " + date.getFullYear();
    element.text(result);
}