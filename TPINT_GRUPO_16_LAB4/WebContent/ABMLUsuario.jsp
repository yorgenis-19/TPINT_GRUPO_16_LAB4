<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>ABML Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Estilos CSS en línea */
        .container {
            max-width: 800px;
            margin-top: 20px;
        }
        .header-title {
            text-align: center;
            margin-bottom: 20px;
        }
        .button-wrapper {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-bottom: 20px;
        }
        .abm-table {
            margin-top: 20px;
            width: 100%;
        }
        .table-actions {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="header-title">Administración de Usuarios</h1>
    
    <!-- Botón para agregar un nuevo usuario -->
    <div class="button-wrapper">
        <button class="btn btn-primary" onclick="window.location.href='UsuarioForm.jsp?action=add'">Agregar Usuario</button>
    </div>
    
    <!-- Tabla para listar usuarios -->
    <table class="table table-striped abm-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <!-- Fila de ejemplo de usuario -->
            <tr>
                <td>1</td>
                <td>Juan Pérez</td>
                <td>juan.perez@example.com</td>
                <td>
                    <div class="table-actions">
                        <button class="btn btn-warning btn-sm" onclick="window.location.href='UsuarioForm.jsp?action=edit&id=1'">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="confirm('¿Estás seguro de que deseas eliminar este usuario?')">Eliminar</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Maria Gómez</td>
                <td>maria.gomez@example.com</td>
                <td>
                    <div class="table-actions">
                        <button class="btn btn-warning btn-sm" onclick="window.location.href='UsuarioForm.jsp?action=edit&id=2'">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="confirm('¿Estás seguro de que deseas eliminar este usuario?')">Eliminar</button>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>

