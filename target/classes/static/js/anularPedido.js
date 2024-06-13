function anularPedido(id) {

    var popupEstado = document.getElementById('popup-estado');

    popupEstado.style.display = 'flex';

    var aceptar = document.getElementById('aceptarEstado');
    var rechazar = document.getElementById('rechazarEstado');
  

    aceptar.addEventListener('click', function () {
        window.location.href= "/pedidos/anularPedido/" + id;
    });

    rechazar.addEventListener('click', function () {
        popupEstado.style.display = 'none';
    });
};