$(document).ready(function() {
    controlButtonShowHide();
});

/**
 * Decide whether the button is showed or hidden
 * depend on the parent media div's 
 * 'comment-hidden' class
 */
function controlButtonShowHide() {
    $(".comment .media").each(function(index, element) {
        // element == this
        if ($(this).hasClass("comment-hidden")) {
            $(this).find("a").each(function() {
                console.log("hidden");
                if ($(this).text() == 'Ẩn') $(this).addClass("hidden");
                else $(this).removeClass("hidden");
            });
        } else {
            $(this).find("a").each(function() {
                console.log("not hidden");
                if ($(this).text() == 'Hiện') $(this).addClass("hidden");
                else $(this).removeClass("hidden");
            });
        }
    });
}