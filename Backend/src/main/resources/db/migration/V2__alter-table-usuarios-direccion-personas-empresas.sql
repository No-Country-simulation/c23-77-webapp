-- Alteraciones para la tabla usuarios
-- Eliminar la columna 'token'
ALTER TABLE usuarios
    DROP COLUMN token;

-- Asegurar la clave foránea 'usuario_id' en direcciones
ALTER TABLE direcciones
    ADD CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE;

-- Alteraciones para la tabla personas
-- Agregar la columna 'usuario_id'
ALTER TABLE personas
    ADD usuario_id INT NOT NULL;

-- Agregar la clave foránea para 'usuario_id' en personas
ALTER TABLE personas
    ADD CONSTRAINT fk_usuario_persona FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE;

-- Alteraciones para la tabla empresas
-- Agregar la columna 'usuario_id'
ALTER TABLE empresas
    ADD usuario_id INT NOT NULL;

-- Agregar la clave foránea para 'usuario_id' en empresas
ALTER TABLE empresas
    ADD CONSTRAINT fk_usuario_empresa FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE;

-- Alteraciones para la tabla historial_transacciones
-- Agregar la columna 'transferencia_id'
ALTER TABLE historial_transacciones
    ADD COLUMN transferencia_id INT;

-- Agregar la clave foránea para 'transferencia_id' en historial_transacciones
ALTER TABLE historial_transacciones
    ADD CONSTRAINT fk_transferencia_historial FOREIGN KEY (transferencia_id) REFERENCES transferencias (id) ON DELETE SET NULL;

-- Alteraciones para relaciones adicionales
-- Relación empresas_cuentas
ALTER TABLE empresas_cuentas
    ADD CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresas (id) ON DELETE CASCADE;

ALTER TABLE empresas_cuentas
    ADD CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuentas_bancarias (id) ON DELETE CASCADE;

-- Relación empresas_empleados
ALTER TABLE empresas_empleados
    ADD CONSTRAINT fk_empresa_empleado FOREIGN KEY (empresa_id) REFERENCES empresas (id) ON DELETE CASCADE;

ALTER TABLE empresas_empleados
    ADD CONSTRAINT fk_persona_empleado FOREIGN KEY (persona_id) REFERENCES personas (id) ON DELETE CASCADE;
