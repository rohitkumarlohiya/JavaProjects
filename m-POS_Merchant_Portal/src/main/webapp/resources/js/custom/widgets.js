jQuery(document).ready(function() {

    //datepicker
    jQuery('#datepicker').datepicker();

    //show tabbed widget
    jQuery('#tabs').tabs();

    //accordion
    jQuery('#accordion').accordion();

    //content slider
    jQuery('#slidercontent').bxSlider({
        prevText: '',
        nextText: ''
    });

    //slim scroll
    jQuery('#scroll1').slimscroll({
        color: '#666',
        size: '10px',
        width: 'auto',
        height: '208px'
    });


});
