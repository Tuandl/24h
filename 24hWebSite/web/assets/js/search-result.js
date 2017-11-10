/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function hiliter(word, element) {
    var rgxp = new RegExp(word, 'i');
    var repl = '<span class="highline">' + word + '</span>';
    element.html(element.html().replace(rgxp, repl));
}

$(document).ready(function () {
    var txtSearch = $('input[name="txtSearch"').val();
    $('.section.search-results .article-preview-title').each(function () {
//        if ($(this).hasClass('article-preview-title'))
            hiliter(txtSearch, $(this));
    });
})
