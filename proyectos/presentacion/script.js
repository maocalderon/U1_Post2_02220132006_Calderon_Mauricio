// 1. Cambio de Tema (Modo claro/oscuro)
document.getElementById('theme-toggle').addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
});

// 2. Botón Leer más / Leer menos
const btnRead = document.getElementById('read-more');
const extraContent = document.getElementById('extra-content');

btnRead.addEventListener('click', () => {
    if (extraContent.classList.contains('hidden')) {
        extraContent.classList.remove('hidden');
        btnRead.textContent = "Leer menos";
    } else {
        extraContent.classList.add('hidden');
        btnRead.textContent = "Leer más";
    }
});

// 3. Filtro de Categorías e Interactivo
const filterBtns = document.querySelectorAll('.filter-btn');
const cards = document.querySelectorAll('.card');
const counter = document.getElementById('counter');

filterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        const cat = btn.getAttribute('data-cat');
        let count = 0;

        cards.forEach(card => {
            if (cat === 'all' || card.classList.contains(cat)) {
                card.style.display = 'block';
                count++;
            } else {
                card.style.display = 'none';
            }
        });
        counter.textContent = `Mostrando: ${count} tarjetas`;
    });
});

// 4. Scroll suave al inicio
document.getElementById('scroll-top').addEventListener('click', () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});