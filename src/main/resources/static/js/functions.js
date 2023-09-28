function filterStudents() {
  const searchText = document.getElementById('student-search').value.toLowerCase();
  const studentsTableRows = document.querySelectorAll('tbody tr');
  const studentCards = document.querySelectorAll('.student-card');

  studentsTableRows.forEach(row => {
    const name = row.querySelector('th').textContent.toLowerCase();
    const lastName = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
    const email = row.querySelector('td:nth-child(3)').textContent.toLowerCase();

    if (name.includes(searchText) || lastName.includes(searchText) || email.includes(searchText)) {
      row.style.display = '';
    } else {
      row.style.display = 'none';
    }
  });

  studentCards.forEach(card => {
    const name = card.querySelector('.student-name-card').textContent.toLowerCase();
    const lastName = card.querySelector('.student-last-name-card').textContent.toLowerCase();
    const email = card.querySelector('.student-email-card').textContent.toLowerCase();

    if (name.includes(searchText) || lastName.includes(searchText) || email.includes(searchText)) {
      card.style.display = '';
    } else {
      card.style.display = 'none';
    }
  });
}

document.getElementById('student-search').addEventListener('input', filterStudents);