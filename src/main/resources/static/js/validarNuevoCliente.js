function validarNuevoCliente() {
    var email = document.getElementById("email");
    var password = document.getElementById("contraseña");
    var phone = document.getElementById("telefono");

    // Limpiar mensajes de error anteriores
    document.getElementById('error-email').textContent = '';
    document.getElementById('error-contraseña').textContent = '';
    document.getElementById('error-telefono').textContent = '';

    // Validar email
    var emailValid = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailValid.test(email.value)) {
        document.getElementById('error-email').textContent = 'El formato del email no es válido';
        email.style.border = '1px solid red';
    } else {
        email.style.border = '1px solid green';
    }

    // Validar contraseña
    var passValid = /^(?=.*\d).{4,}$/;
    if(!passValid.test(password.value)) {
        document.getElementById('error-contraseña').textContent = 'La contraseña debe tener al menos 4 caracteres y contener al menos un número';
        password.style.border = '1px solid red';
    } else {
        password.style.border = '1px solid green';
    }

    // Validar teléfono
    var phoneValid = /^\d{9}$/;
    if(!phoneValid.test(phone.value)) {
        document.getElementById('error-telefono').textContent = 'El teléfono debe tener 9 dígitos';
        phone.style.border = '1px solid red';
    } else {
        phone.style.border = '1px solid green';
    }


    // Si hay errores, evitar el envío del formulario
    if (document.getElementById('error-email').textContent || 
        document.getElementById('error-contraseña').textContent || 
        document.getElementById('error-telefono').textContent) {
        return false;
    }

    return true;
}
