-- Crear tabla usuario
CREATE TABLE usuarios
(
    id           SERIAL PRIMARY KEY,
    email        VARCHAR(255) NOT NULL UNIQUE,
    contrasena   VARCHAR(255) NOT NULL,
    telefono     VARCHAR(20),
    tipo_usuario VARCHAR(50)  NOT NULL,
    token        VARCHAR(512),
    rol          VARCHAR(50)
);

-- Crear tabla direcciones
CREATE TABLE direcciones
(
    id               SERIAL PRIMARY KEY,
    calle            VARCHAR(255) NOT NULL,
    ciudad           VARCHAR(100) NOT NULL,
    estado_provincia VARCHAR(100),
    codigo_postal    VARCHAR(20),
    pais             VARCHAR(100) NOT NULL,
    usuario_id       INT UNIQUE REFERENCES usuarios (id)
);

-- Crear tabla empresas
CREATE TABLE empresas
(
    id     SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Crear tabla personas
CREATE TABLE personas
(
    id               SERIAL PRIMARY KEY,
    nombres          VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    sexo             VARCHAR(10),
    lugar_nacimiento VARCHAR(100),
    fecha_nacimiento DATE,
    dni              VARCHAR(20) UNIQUE
);

-- Crear tabla cuentas_bancarias
CREATE TABLE cuentas_bancarias
(
    id             SERIAL PRIMARY KEY,
    propietario_id INT            NOT NULL REFERENCES usuarios (id) ON DELETE CASCADE,
    moneda         VARCHAR(10)    NOT NULL,
    saldo          NUMERIC(15, 2) NOT NULL DEFAULT 0.0,
    tipo_cuenta    VARCHAR(50)    NOT NULL,
    fecha_creacion DATE                    DEFAULT CURRENT_DATE,
    banco_emisor   VARCHAR(255)
);

-- Crear tabla transferencias
CREATE TABLE transferencias
(
    id             SERIAL PRIMARY KEY,
    monto          NUMERIC(15, 2) NOT NULL,
    cuenta_origen  INT            NOT NULL REFERENCES cuentas_bancarias (id),
    cuenta_destino INT            NOT NULL REFERENCES cuentas_bancarias (id),
    fecha          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado         VARCHAR(50)    NOT NULL,
    notas          TEXT
);

-- Crear tabla historial_transacciones
CREATE TABLE historial_transacciones
(
    id               SERIAL PRIMARY KEY,
    tipo_transaccion VARCHAR(50)    NOT NULL,
    monto            NUMERIC(15, 2) NOT NULL,
    fecha            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cuenta_id        INT            NOT NULL REFERENCES cuentas_bancarias (id) ON DELETE CASCADE
);

-- Crear tabla notificaciones
CREATE TABLE notificaciones
(
    id             SERIAL PRIMARY KEY,
    usuario_id     INT         NOT NULL REFERENCES usuarios (id),
    tipo           VARCHAR(50) NOT NULL,
    mensaje        TEXT        NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    leido          BOOLEAN   DEFAULT FALSE
);

-- Crear relación entre empresas y cuentas bancarias (muchas cuentas por empresa)
CREATE TABLE empresas_cuentas
(
    empresa_id INT NOT NULL REFERENCES empresas (id),
    cuenta_id  INT NOT NULL REFERENCES cuentas_bancarias (id),
    PRIMARY KEY (empresa_id, cuenta_id)
);

-- Crear relación entre empresas y personas (empleados)
CREATE TABLE empresas_empleados
(
    empresa_id INT NOT NULL REFERENCES empresas (id),
    persona_id INT NOT NULL REFERENCES personas (id),
    PRIMARY KEY (empresa_id, persona_id)
);

-- Crear tabla archivos_subidos
CREATE TABLE archivos_subidos
(
    id             SERIAL PRIMARY KEY,
    usuario_id     INT          NOT NULL REFERENCES usuarios (id) ON DELETE CASCADE,
    nombre_archivo VARCHAR(255) NOT NULL,
    ruta_archivo   TEXT         NOT NULL,
    tipo_archivo   VARCHAR(50), -- ejemplo: 'imagen', 'pdf', 'documento', etc.
    fecha_subida   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
