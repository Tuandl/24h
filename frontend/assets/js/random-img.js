var imgURLs = ["../assets/img/bg-forum.jpg",
    "../assets/img/bg-gui.jpg",
    "../assets/img/bg-home-student-header.jpg",
    "../assets/img/bg-license.jpg",
    "../assets/img/bg-rank.jpg",
    "../assets/img/bg.jpg",
    "../assets/img/bg2.jpeg",
    "../assets/img/bg3.jpeg",
    "../assets/img/bg4.jpeg",
    "../assets/img/bg5.jpeg",
    "../assets/img/bg6.jpg",
    "../assets/img/city.jpg",
    "../assets/img/live-music.jpg"
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