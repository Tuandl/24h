$(document).ready(function() {
    var tmp = $("#rdJournalist").attr("checked");
    if (tmp == "checked") {
        $("#press-card-input").removeClass("hidden");
    } else {
        $("#press-card-input").addClass("hidden");
    }
    togglePresscardInput();
});

/**
 * Add an even which 
 * shows input press card number when role is "Nhà báo" 
 * hide input press card number when role is not "Nhà báo"
 */
function togglePresscardInput() {
    $("input[name='txtRole']").change(function(e) {
        var role = $(this).parent("label").text().trim();
        if (role == "Nhà báo") {
            $("#press-card-input").removeClass("hidden");
        } else {
            $("#press-card-input").addClass("hidden");
        }
    });
}