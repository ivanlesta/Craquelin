function validarFormularioContacto() {
    var email = document.getElementById('email');
    var motivo = document.getElementById('list1');
    var acepto = document.getElementById('acepto');

    // Limpiar mensajes de error anteriores
    document.getElementById('error-email').textContent = '';
    document.getElementById('error-motivo').textContent = '';
    document.getElementById('error-acepto').textContent = '';

    // Validar email
    var emailValid = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailValid.test(email.value)) {
        document.getElementById('error-email').textContent = 'Email no válido';
        email.style.border = '1px solid red';
    } else {
        email.style.border = '1px solid green';
    }

    // Validar motivo
    if (motivo.value === '0') {
        document.getElementById('error-motivo').textContent = 'Debe seleccionar un motivo';
        motivo.style.border = '1px solid red';
    } else {
        motivo.style.border = '1px solid green';
    }

    // Validar checkbox
    if (!acepto.checked) {
        document.getElementById('error-acepto').textContent = 'Debe aceptar las condiciones del servicio';
        acepto.parentNode.style.color = 'red';
    } else {
        acepto.parentNode.style.color = 'black';
    }

    // Si hay errores, evitar el envío del formulario
    if (document.getElementById('error-email').textContent || 
        document.getElementById('error-motivo').textContent || 
        document.getElementById('error-acepto').textContent) {
        return false;
    }

    return true;
}



