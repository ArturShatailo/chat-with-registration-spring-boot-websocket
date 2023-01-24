'use strict'

$('.scrollClass').on('click', function (event) {
    event.preventDefault()
    let scrollToSection = $(this).attr('href');
    if ($(scrollToSection).length !== 0) {

        console.log("ll")

        $('html, body').animate({
            scrollTop: $(scrollToSection).offset().top - 200
        }, 1000);
    }
});