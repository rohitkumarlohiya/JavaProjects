jQuery(document).ready(function() {

    ///// AUTOGROW TEXTAREA /////
    jQuery('#statustext, #statusphoto').autogrow();

    ///// PREVIEW IMAGE /////
    jQuery('.updatecontent .photo a').colorbox();

    ///// ANIMATE IMAGE HOVER /////
    jQuery('.updatecontent .photo').hover(function() {
        jQuery(this).find('img').stop().animate({opacity: 0.75});
    }, function() {
        jQuery(this).find('img').stop().animate({opacity: 1});
    });

    ///// FORM TRANSFORMATION /////
    jQuery('input:file').uniform();

    ///// POST STATUS /////
    jQuery('#poststatus,#postphoto').submit(function() {
        var t = jQuery(this);
        var url = t.attr('action');
        var msg = t.find('textarea').val();
        jQuery.post(url, {message: msg}, function(data) {
            jQuery(data).insertBefore('.updatelist li:first-child');
            t.find('textarea').val('');
        });
        return false;
    });

});