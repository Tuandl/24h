
var imgURLs = ["/24hWebSite/assets/img/bg-forum.jpg",
    "/24hWebSite/assets/img/bg-gui.jpg",
    "/24hWebSite/assets/img/bg-home-student-header.jpg",
    "/24hWebSite/assets/img/bg-license.jpg",
    "/24hWebSite/assets/img/bg-rank.jpg",
    "/24hWebSite/assets/img/bg.jpg",
    "/24hWebSite/assets/img/bg2.jpeg",
    "/24hWebSite/assets/img/bg3.jpeg",
    "/24hWebSite/assets/img/bg4.jpeg",
    "/24hWebSite/assets/img/bg5.jpeg",
    "/24hWebSite/assets/img/bg6.jpg",
    "/24hWebSite/assets/img/city.jpg",
    "/24hWebSite/assets/img/live-music.jpg"
];

$(document).ready(function() {
    $("img").each(function(index, element) {
        // element == this
        if ($(element).attr("alt") != "24h Logo") {
            var indexImg = Math.floor(Math.random() * imgURLs.length);
            // console.log(indexImg);
            $(element).attr("src", imgURLs[indexImg]);
        }
    });
});