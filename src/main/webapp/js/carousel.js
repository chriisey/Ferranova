let slideIndex = 1;
//mostro la prima foto
showSlides(slideIndex);

//se clicco il pulsante successivo l'n passato è 1 e l'indice si incrementa, col precedente è -1 e l'indice decrementa
function plusSlides(n) {
    showSlides(slideIndex += n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");

    if (n > slides.length) {
        slideIndex = 1
    }

    if (n < 1) {
        slideIndex = slides.length
    }

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    slides[slideIndex-1].style.display = "block";
}