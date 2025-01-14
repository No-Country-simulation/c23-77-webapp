-- Crear tabla Usuario
CREATE TABLE Usuario
(
    id           SERIAL PRIMARY KEY,
    email        VARCHAR(255) NOT NULL UNIQUE,
    contrasena   VARCHAR(255) NOT NULL,
    telefono     VARCHAR(20),
    tipo_usuario VARCHAR(50)  NOT NULL,
    token        VARCHAR(512),
    rol          VARCHAR(50)
);

-- Crear tabla Direccion
CREATE TABLE Direccion
(
    id               SERIAL PRIMARY KEY,
    calle            VARCHAR(255) NOT NULL,
    ciudad           VARCHAR(100) NOT NULL,
    estado_provincia VARCHAR(100),
    codigo_postal    VARCHAR(20),
    pais             VARCHAR(100) NOT NULL,
    usuario_id       INT UNIQUE REFERENCES Usuario (id)
);

-- Crear tabla Empresas
CREATE TABLE Empresas
(
    id     SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Crear tabla Personas
CREATE TABLE Personas
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

-- Crear tabla Cuenta Bancaria
CREATE TABLE Cuenta_Bancaria
(
    id             SERIAL PRIMARY KEY,
    propietario_id INT            NOT NULL,
    moneda         VARCHAR(10)    NOT NULL,
    saldo          NUMERIC(15, 2) NOT NULL DEFAULT 0.0,
    tipo_de_cuenta VARCHAR(50)    NOT NULL,
    fecha_creacion DATE                    DEFAULT CURRENT_DATE,
    banco_emisor   VARCHAR(255),
    CONSTRAINT propietario_fk FOREIGN KEY (propietario_id) REFERENCES Usuario (id)
        ON DELETE CASCADE
);

-- Crear tabla Transferencia
CREATE TABLE Transferencia
(
    id             SERIAL PRIMARY KEY,
    monto          NUMERIC(15, 2) NOT NULL,
    cuenta_origen  INT            NOT NULL REFERENCES Cuenta_Bancaria (id),
    cuenta_destino INT            NOT NULL REFERENCES Cuenta_Bancaria (id),
    fecha          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado         VARCHAR(50)    NOT NULL,
    notas          TEXT
);

-- Crear tabla Historial de Transacciones
CREATE TABLE Historial_Transacciones
(
    id               SERIAL PRIMARY KEY,
    tipo_transaccion VARCHAR(50)    NOT NULL,
    monto            NUMERIC(15, 2) NOT NULL,
    fecha            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cuenta_id        INT            NOT NULL REFERENCES Cuenta_Bancaria (id)
        ON DELETE CASCADE
);

-- Crear tabla Notificacion
CREATE TABLE Notificacion
(
    id             SERIAL PRIMARY KEY,
    usuario_id     INT         NOT NULL REFERENCES Usuario (id),
    tipo           VARCHAR(50) NOT NULL,
    mensaje        TEXT        NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    leido          BOOLEAN   DEFAULT FALSE
);

-- Crear relacion entre Empresas y Cuenta Bancaria (muchas cuentas por empresa)
CREATE TABLE Empresas_Cuentas
(
    empresa_id INT NOT NULL REFERENCES Empresas (id),
    cuenta_id  INT NOT NULL REFERENCES Cuenta_Bancaria (id),
    PRIMARY KEY (empresa_id, cuenta_id)
);

-- Crear relacion entre Empresas y Personas (empleados)
CREATE TABLE Empresas_Empleados
(
    empresa_id INT NOT NULL REFERENCES Empresas (id),
    persona_id INT NOT NULL REFERENCES Personas (id),
    PRIMARY KEY (empresa_id, persona_id)
);

-- Crear tabla Archivos_Subidos
CREATE TABLE Archivos_Subidos
(
    id             SERIAL PRIMARY KEY,
    usuario_id     INT          NOT NULL REFERENCES Usuario (id) ON DELETE CASCADE,
    nombre_archivo VARCHAR(255) NOT NULL,
    ruta_archivo   TEXT         NOT NULL,
    tipo_archivo   VARCHAR(50), -- Ejemplo: 'imagen', 'pdf', 'documento', etc.
    fecha_subida   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
