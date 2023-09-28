   const newStudentForm = document.getElementById('student-form');
    const studentName = document.getElementById('student-name');
    const studentLastName = document.getElementById('student-last-name');
    const studentEmail = document.getElementById('student-email');

    function validateField(inputField, errorId, validator) {
      const value = inputField.value.trim();
      const errorElement = document.getElementById(errorId);
      errorElement.innerHTML = '';

      if (!validator(value)) {
        const errorMessage = document.createElement('div');
        errorMessage.className = 'text-red-500 text-xs mt-1';
        errorMessage.textContent = 'Invalid input';
        errorElement.appendChild(errorMessage);
        return false; // Campo no válido
      }

      return true; // Campo válido
    }

    function validateName(studentName) {
      var regex = /^[A-Za-zÁáÉéÍíÓóÚúñÑ\s]+$/;
      return regex.test(studentName) && studentName.trim().length >= 2;
    }

    function validateLastName(studentLastName) {
      var regex = /^[A-Za-zÁáÉéÍíÓóÚúñÑ\s]+$/;
      return regex.test(studentLastName) && studentLastName.trim().length >= 2;
    }

    function validateEmail(studentEmail) {
      var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      return regex.test(studentEmail);
    }

    newStudentForm.addEventListener('submit', function (event) {
      const errors = [];

      if (!validateField(studentName, 'name-error', validateName)) {
        errors.push('Invalid name');
      }
      if (!validateField(studentLastName, 'last-name-error', validateLastName)) {
        errors.push('Invalid last name');
      }
      if (!validateField(studentEmail, 'email-error', validateEmail)) {
        errors.push('Invalid email');
      }

      const globalErrorElement = document.getElementById('global-error');
      globalErrorElement.innerHTML = '';

      if (errors.length > 0) {
        event.preventDefault(); // Evita que se envíe el formulario
        const errorMessage = document.createElement('div');
        errorMessage.className = 'text-red-500 text-xs mt-1';
        errorMessage.textContent = 'Please fix the following errors:';
        globalErrorElement.appendChild(errorMessage);

        errors.forEach(function (error) {
          const errorElement = document.createElement('div');
          errorElement.className = 'text-red-500 text-xs mt-1 ml-4';
          errorElement.textContent = error;
          globalErrorElement.appendChild(errorElement);
        });
      }
    });