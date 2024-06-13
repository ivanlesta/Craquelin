let currentSlide = 0;
let intervalId; 

function moveSlide(direction) {
    const slides = document.querySelectorAll('.carousel-images img');
    const totalSlides = slides.length;
    currentSlide = (currentSlide + direction + totalSlides) % totalSlides;
    const offset = -currentSlide * 100;
    document.querySelector('.carousel-images').style.transform = `translateX(${offset}%)`;
}

function startAutoSlide() {
    intervalId = setInterval(function() {
        moveSlide(1); 
    }, 2500); 
}

function stopAutoSlide() {
    clearInterval(intervalId);
}

document.addEventListener('DOMContentLoaded', function() {
    startAutoSlide();
});

document.querySelector('.carousel').addEventListener('mouseenter', function() {
    stopAutoSlide();
});

document.querySelector('.carousel').addEventListener('mouseleave', function() {
    startAutoSlide();
});
