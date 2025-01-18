-- Alteraciones para la tabla usuarios
-- Eliminar la columna 'token'
ALTER TABLE usuarios
    DROP COLUMN tipo_usuario;