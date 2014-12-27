var videoList = [
    { src0: "video/video-2.mp4", src1: "video/video-2.ogg", meta: ""},
    { src0: "video/z1.mp4", src1: "video/z1.ogg", meta: "video/z1.mp4.xml"},
    { src0: "video/z2.mp4", src1: "video/z2.ogg", meta: "video/z2.mp4.xml"},
    { src0: "video/z3.mp4", src1: "video/z3.ogg", meta: "video/z3.mp4.xml"}
];

function changeVideo(idx)
{
    var video = document.getElementsByTagName('video')[0];
    var sources = video.getElementsByTagName('source');
    sources[0].src = videoList[idx].src0;
    sources[1].src = videoList[idx].src1;
    video.load();
}


var videoEnded = function()
{
    changeVideo(0);
}

