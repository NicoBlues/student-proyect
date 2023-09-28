const notification = document.getElementById('toast-notification');

// Función para mostrar la notificación
function showNotification() {
  notification.classList.add('slide-in-up');
}

// Función para ocultar y eliminar la notificación
function hideAndRemoveNotification() {
  notification.classList.remove('slide-in-up');
  notification.classList.add('slide-out-down');

  // Espera a que termine la animación de salida y luego elimina el elemento
  notification.addEventListener('animationend', function() {
    notification.remove();
  });
}

// Llamada a la función para mostrar la notificación (puedes invocarla según tus necesidades)
showNotification();

// Llamada a la función para ocultar y eliminar la notificación después de 4 segundos
setTimeout(hideAndRemoveNotification, 4000); // Ocultar después de 4 segundos
