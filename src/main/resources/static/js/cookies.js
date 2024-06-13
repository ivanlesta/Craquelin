window.addEventListener('load', function () {
    var popupContainer = document.getElementById('popup-container');

    popupContainer.style.display = 'flex';

    var cookiesBtns = document.querySelectorAll('.cookiesBtn');

    cookiesBtns.forEach(function (btn) {
        btn.addEventListener('click', function () {
            popupContainer.style.display = 'none';
        });
    });
});

