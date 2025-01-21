import { createContext, useEffect, useState, useContext } from "react";
import PropTypes from "prop-types";
import api from "../../api/api";

// Creación del contexto
const AuthContext = createContext();

// Hook personalizado para consumir el contexto
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth debe ser utilizado dentro de un AuthProvider");
  }
  return context; // Devuelve el valor proporcionado en el Provider
};

// Proveedor del contexto
export const AuthProvider = ({ children }) => {
  const [isAuth, setIsAuth] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuth(!!token); // Establece autenticación si el token existe
    setLoading(false); // Detiene el estado de carga una vez que se verifica el token
  }, []);

  const login = async (email, password) => {
    try {
      const response = await api.post("/login", { email, password });
      const { token } = response.data;

      // Guarda el token en localStorage y establece el estado de autenticación
      localStorage.setItem("token", token);
      setIsAuth(true);
    } catch (error) {
      console.error("Error durante el inicio de sesión:", error);
      throw error;
    }
  };

  const logout = () => {
    // Elimina el token y restablece el estado de autenticación
    localStorage.removeItem("token");
    setIsAuth(false);

    // Redirige al usuario a la página de login
    window.location.href = "/login";
  };

  return (
    <AuthContext.Provider value={{ isAuth, setIsAuth, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

AuthProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export default AuthContext;
