$(document).ready(function() {
    autoGenIndexForTable();
});

/**
 * This function automatic find any table contain 
 * "#" in the first column of header
 * Example: 
 * <table>
 * <thead>
 * <tr>
 * <th>#</th>
 * </tr>
 * ...
 * </thead>
 * ...
 * </table>
 */
function autoGenIndexForTable() {
    $("table").each(function(index, element) {
        // element == this
        if ($(element).find("th:first-child").text().trim() == "#") {
            var count = 0;
            $(this).find("tbody").children("tr").each(function(index, element) {
                // element == this
                count++;
                $(this).children("td:first").text(count);
            });
        }
    });
}