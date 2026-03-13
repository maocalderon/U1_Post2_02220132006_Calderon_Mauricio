


// Token de estudiante (Requisito 3)

const TOKEN_02220132006 = '02220132006-2026A';

document.addEventListener('DOMContentLoaded', () => {
    // 1. Saludo y Anio
    const hora = new Date().getHours();
    const saludo = hora < 12 ? "Buenos dias" : (hora < 19 ? "Buenas tardes" : "Buenas noches");
    document.getElementById('titulo-hero').textContent = saludo + ", CodeReview+";
    document.getElementById('anio-actual').textContent = new Date().getFullYear();

    // 2. Filtros y Contador (Requisito 5.2)
    const botones = document.querySelectorAll('.filtro-btn');
    const tarjetas = document.querySelectorAll('.tarjeta');
    const contador = document.getElementById('contador-visibles');

    botones.forEach(btn => {
        btn.addEventListener('click', () => {
            const cat = btn.getAttribute('data-cat');
            let n = 0;
            tarjetas.forEach(t => {
                if (cat === 'Todos' || t.getAttribute('data-categoria') === cat) {
                    t.style.display = 'block'; n++;
                } else {
                    t.style.display = 'none';
                }
            });
            contador.textContent = "Se muestran: " + n + " tarjetas";
        });
    });

    // 3. Tema y Precios (Requisito 5.1 y 5.4)
    document.getElementById('boton-tema').addEventListener('click', () => document.body.classList.toggle('dark-mode'));

    document.getElementById('toggle-precio').addEventListener('change', (e) => {
        document.querySelectorAll('.precio').forEach(p => {
            const base = parseFloat(p.dataset.base);
            p.textContent = e.target.checked ? "$" + ((base * 12) * 0.8).toFixed(0) + "/año" : "$" + base + "/mes";
        });
    });

    // 4. Validacion (Requisito 5.6)
    document.getElementById('form-contacto').addEventListener('submit', (e) => {
        if (document.getElementById('mensaje').value.length < 20) {
            e.preventDefault();
            alert("El mensaje debe tener minimo 20 caracteres");
        }
    });
});