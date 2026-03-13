from flask import Flask, render_template, request, redirect, url_for, session, send_file
import sqlite3
import os
from datetime import datetime
import pandas as pd
import io
import pytz # Para la hora de Colombia

app = Flask(__name__)
app.secret_key = 'llave_super_secreta_del_lago_2026'

# --- CONFIGURACIÓN DE SQLITE ---
ARCHIVO_DB = 'el_lago.db'

def obtener_conexion():
    conexion = sqlite3.connect(ARCHIVO_DB)
    conexion.row_factory = sqlite3.Row 
    return conexion

def iniciar_base_datos():
    conexion = obtener_conexion()
    cursor = conexion.cursor()
    cursor.execute('''CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)''')
    cursor.execute('''CREATE TABLE IF NOT EXISTS inventario_diario (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT NOT NULL, producto TEXT NOT NULL, hay INTEGER DEFAULT 0, ingreso INTEGER DEFAULT 0, venta INTEGER DEFAULT 0, obsequio INTEGER DEFAULT 0, piscina INTEGER DEFAULT 0, quedo INTEGER DEFAULT 0, UNIQUE(fecha, producto))''')
    cursor.execute('SELECT COUNT(*) FROM usuarios')
    if cursor.fetchone()[0] == 0:
        cursor.execute("INSERT INTO usuarios (username, password) VALUES ('admin', 'lago2026')")
        cursor.execute("INSERT INTO usuarios (username, password) VALUES ('caja', '1234')")
    conexion.commit()
    conexion.close()

iniciar_base_datos()

productos_base = ["Aguila - Light - Poker", "Club", "Stella Artois", "Corona Botella", "Sol", "Heineken", "Artesanal", "Ron", "Aguardiente", "Red Bull", "Agua Mineral", "Jugo Hit Caja", "Hatsu sabores - soda", "Hatsu Tee", "Soda Bretaña", "Gaseosa Mini", "Gaseosa P400", "Pony", "Chococono (Helado)", "Paleta Jet (Helado)"]

@app.route('/login', methods=['GET', 'POST'])
def login():
    error = None
    if request.method == 'POST':
        usuario, password = request.form['usuario'], request.form['password']
        conexion = obtener_conexion()
        u = conexion.execute("SELECT * FROM usuarios WHERE username = ? AND password = ?", (usuario, password)).fetchone()
        conexion.close()
        if u:
            session['usuario'] = u['username']
            return redirect(url_for('index'))
        error = "Usuario o contraseña incorrectos."
    return render_template('login.html', error=error)

@app.route('/logout')
def logout():
    session.pop('usuario', None)
    return redirect(url_for('login'))

@app.route('/')
def index():
    if 'usuario' not in session: return redirect(url_for('login'))
    
    # --- LÓGICA HORA COLOMBIANA ---
    zona_co = pytz.timezone('America/Bogota')
    hora_col = datetime.now(zona_co).hour
    
    if 5 <= hora_col < 12: saludo = "Buenos días"
    elif 12 <= hora_col < 18: saludo = "Buenas tardes"
    else: saludo = "Buenas noches"

    fecha_buscada = request.args.get('fecha') or datetime.now(zona_co).strftime("%Y-%m-%d")
    productos_pantalla = [{"nombre": n, "hay": 0, "ingreso": 0, "venta": 0, "obsequio": 0, "piscina": 0, "quedo": 0} for n in productos_base]

    conexion = obtener_conexion()
    filas = conexion.execute("SELECT * FROM inventario_diario WHERE fecha = ?", (fecha_buscada,)).fetchall()
    conexion.close()

    db_map = {f['producto']: f for f in filas}
    for p in productos_pantalla:
        if p['nombre'] in db_map:
            d = db_map[p['nombre']]
            p.update({"hay": d['hay'], "ingreso": d['ingreso'], "venta": d['venta'], "obsequio": d['obsequio'], "piscina": d['piscina'], "quedo": d['quedo']})

    return render_template('index.html', productos=productos_pantalla, fecha=fecha_buscada, usuario_actual=session['usuario'], saludo=saludo)

@app.route('/actualizar', methods=['POST'])
def actualizar():
    if 'usuario' not in session: return redirect(url_for('login'))
    f_form = request.form.get('fecha')
    conexion = obtener_conexion()
    for i, nombre in enumerate(productos_base):
        try:
            val = [int(request.form.get(f'{k}_{i}') or 0) for k in ['hay', 'ingreso', 'venta', 'obsequio', 'piscina']]
            q = val[0] + val[1] - val[2] - val[3] + val[4]
            conexion.execute("INSERT INTO inventario_diario (fecha, producto, hay, ingreso, venta, obsequio, piscina, quedo) VALUES (?,?,?,?,?,?,?,?) ON CONFLICT(fecha, producto) DO UPDATE SET hay=excluded.hay, ingreso=excluded.ingreso, venta=excluded.venta, obsequio=excluded.obsequio, piscina=excluded.piscina, quedo=excluded.quedo", (f_form, nombre, *val, q))
        except ValueError: continue
    conexion.commit()
    conexion.close()
    return redirect(url_for('index', fecha=f_form))

@app.route('/reporte_ventas/<periodo>')
def reporte_ventas(periodo):
    conexion = obtener_conexion()
    df = pd.read_sql_query("SELECT * FROM inventario_diario", conexion)
    conexion.close()
    if df.empty: return "Sin datos", 404
    df['fecha'] = pd.to_datetime(df['fecha'])
    reporte = df.groupby([df['fecha'].dt.to_period('W' if periodo=='semanal' else 'M'), 'producto'])['venta'].sum().reset_index()
    reporte['fecha'] = reporte['fecha'].astype(str)
    out = io.BytesIO()
    with pd.ExcelWriter(out, engine='openpyxl') as writer: reporte.to_excel(writer, index=False)
    out.seek(0)
    return send_file(out, download_name=f"Ventas_{periodo}.xlsx", as_attachment=True)

if __name__ == '__main__':
    app.run(debug=True)