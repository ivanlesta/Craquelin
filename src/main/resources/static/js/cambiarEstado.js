function cambiarEstado(id) {

    var popupEstado = document.getElementById('popup-estado');

    popupEstado.style.display = 'flex';

    var aceptar = document.getElementById('aceptarEstado');
    var rechazar = document.getElementById('rechazarEstado');
  

    aceptar.addEventListener('click', function () {
        window.location.href= "/pedidos/cambiarEstado/" + id;
    });

    rechazar.addEventListener('click', function () {
        popupEstado.style.display = 'none';
    });
};