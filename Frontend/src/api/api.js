/***********************************************
 * src/services/api.js
 ***********************************************/
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // Ajusta a tu entorno
  headers: {
    "Content-Type": "application/json",
  },
});

// Interceptor para inyectar el token en las peticiones
api.interceptors.request.use((config) => {
  // Evitar inyectar el token si la URL es /login
  if (config.url !== "/login") {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // Añade el token al encabezado
    }
  }
  return config;
});

export default api;

// Registrar un nuevo usuario
export const registrarUsuario = async (email, contrasena, telefono, rol) => {
  return api.post("/usuarios/registro", {
    email,
    contrasena,
    telefono,
    rol,
  });
};

// Registrar una nueva persona
export const registrarPersona = async (persona) => {
  return api.post("/personas", persona);
};

// Obtener información del usuario
export const obtenerInfoUsuario = async () => {
  const respuesta = await api.get("/usuarios/perfil");
  return respuesta.data;
};

// Actualizar información del usuario
export const actualizarNombreUsuario = async (nuevoNombreUsuario) => {
  return api.patch("/usuarios/perfil", {
    nombreUsuario: nuevoNombreUsuario,
  });
};
